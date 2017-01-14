package com.codinggyd.github.miemiedev.mybatis.paginator;


import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codinggyd.exception.ServiceException;
import com.codinggyd.github.miemiedev.mybatis.paginator.dialect.Dialect;
import com.codinggyd.github.miemiedev.mybatis.paginator.dialect.SQLServer2005Dialect;
import com.codinggyd.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.codinggyd.github.miemiedev.mybatis.paginator.domain.PageList;
import com.codinggyd.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.codinggyd.github.miemiedev.mybatis.paginator.support.PropertiesHelper;
import com.codinggyd.github.miemiedev.mybatis.paginator.support.SQLHelp;
import com.codinggyd.log.Log;


/**
 * 为MyBatis提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * @author badqiu
 * @author miemiedev
 *
 */

@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor{
    private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

    static ExecutorService Pool;
    String dialectClass;
    boolean asyncTotalCount = false;
	
	public Object intercept(final Invocation invocation) throws Throwable {
		
        final Executor executor = (Executor) invocation.getTarget();
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
//        ms.isUseCache();
//        ms.getConfiguration().setCacheEnabled(false);
//        ms.isUseCache();
        
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
        
        if(rowBounds==null||rowBounds==RowBounds.DEFAULT){
        	long start = System.currentTimeMillis();
        	 Object result=invocation.proceed();
        	 long end = System.currentTimeMillis();
        	 if(end-start>2000){
        		 Log.error(logger, "MS_ID:[{}],执行时间:[{}]",ms.getId(), end-start);
        	 }
//        	 SimpleExecutor
        	 return result;
        }
        final PageBounds pageBounds = new PageBounds(rowBounds);
        if(pageBounds.getOffset() == RowBounds.NO_ROW_OFFSET
                && pageBounds.getLimit() == RowBounds.NO_ROW_LIMIT
                && pageBounds.getOrders().isEmpty()){
            return invocation.proceed();
        }

        final Dialect dialect=new  SQLServer2005Dialect(ms, parameter, pageBounds);;
//        try {
//            Class clazz = Class.forName(dialectClass);
//            dialect=
//            Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageBounds.class);
//            dialect = (Dialect)constructor.newInstance(new Object[]{ms, parameter, pageBounds});
//        } catch (Exception e) {
//            throw new ClassNotFoundException("Cannot create dialect instance: "+dialectClass,e);
//        }

        final BoundSql boundSql = ms.getBoundSql(parameter);

        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms,boundSql,dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
        queryArgs[ROWBOUNDS_INDEX] = RowBounds.DEFAULT;//new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);

        Boolean async = pageBounds.getAsyncTotalCount() == null ? asyncTotalCount : pageBounds.getAsyncTotalCount();
        Future<List> listFuture = call(new Callable<List>() {
            @SuppressWarnings("rawtypes")
			public List call() throws Exception {
            	try{
                return (List)invocation.proceed();
            	}catch(Exception e){
            		Log.error(logger, e, "7002,数据访问异常,id:[{}],参数:[{}]",ms.getId(),parameter);
            		throw new ServiceException("7002","数据访问异常");
            	}
            }
        }, async);
//        processMappedStatement(ms);

        if(pageBounds.isContainsTotalCount()){
            Callable<Paginator> countTask = new Callable() {
                public Object call() throws Exception {
                    Integer count;
                    
//                    Cache cache = ms.getCache();
//                    if(cache != null && ms.isUseCache() && ms.getConfiguration().isCacheEnabled()){
//                        CacheKey cacheKey = executor.createCacheKey(ms,parameter,new PageBounds(),copyFromBoundSql(ms,boundSql,dialect.getCountSQL(), boundSql.getParameterMappings(), boundSql.getParameterObject()));
//                        count = (Integer)cache.getObject(cacheKey);
//                        if(count == null){
//                            count = SQLHelp.getCount(ms,executor.getTransaction(),parameter,boundSql,dialect);
//                            cache.putObject(cacheKey, count);
//                        }
//                    }else{
                        count = SQLHelp.getCount(ms,executor.getTransaction(),parameter,boundSql,dialect);
//                    }
                    return new Paginator(pageBounds.getPage(), pageBounds.getLimit(), count);
                }
            };
            Future<Paginator> countFutrue = call(countTask, async);
            return new PageList(listFuture.get(),countFutrue.get());
        }

        return listFuture.get();
	}
    /**
     * 修改SqlSource
     *
     * @param ms
     * @param parser
     * @throws Throwable
     */
    public static MappedStatement processMappedStatement(MappedStatement ms) throws Throwable {
    	SqlSource sqlSource = ms.getSqlSource();
        MetaObject msObject = SystemMetaObject.forObject(ms);
        SqlSource tempSqlSource = sqlSource;
//        if (sqlSource instanceof OrderBySqlSource) {
//            tempSqlSource = ((OrderBySqlSource) tempSqlSource).getOriginal();
//        }
//        SqlSource pageSqlSource;
//        if (tempSqlSource instanceof StaticSqlSource) {
//            pageSqlSource = new PageStaticSqlSource((StaticSqlSource) tempSqlSource, parser);
//        } else if (tempSqlSource instanceof RawSqlSource) {
//            pageSqlSource = new PageRawSqlSource((RawSqlSource) tempSqlSource, parser);
//        } else if (tempSqlSource instanceof ProviderSqlSource) {
//            pageSqlSource = new PageProviderSqlSource((ProviderSqlSource) tempSqlSource, parser);
//        } else if (tempSqlSource instanceof DynamicSqlSource) {
//            pageSqlSource = new PageDynamicSqlSource((DynamicSqlSource) tempSqlSource, parser);
//        } else {
//            throw new RuntimeException("无法处理该类型[" + sqlSource.getClass() + "]的SqlSource");
//        }
//        msObject.setValue("sqlSource", pageSqlSource);
        //由于count查询需要修改返回值，因此这里要创建一个Count查询的MS
        return MSUtils.newCountMappedStatement(ms);
    }
    private <T> Future<T> call(Callable callable, boolean async){
        if(async){
             return Pool.submit(callable);
        }else{
            FutureTask<T> future = new FutureTask(callable);
            future.run();
            return future;
        }
    }

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql,
                                           String sql, List<ParameterMapping> parameterMappings, Object parameter){
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
			String sql, List<ParameterMapping> parameterMappings,Object parameter) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, parameterMappings, parameter);
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
		    String prop = mapping.getProperty();
		    if (boundSql.hasAdditionalParameter(prop)) {
		        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
		    }
		}
		return newBoundSql;
	}
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		builder.useCache(false);
		if(ms.getKeyProperties() != null && ms.getKeyProperties().length !=0){
            StringBuffer keyProperties = new StringBuffer();
            for(String keyProperty : ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
	    
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
//		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
		String dialectClass = propertiesHelper.getRequiredString("dialectClass");
		setDialectClass(dialectClass);

        setAsyncTotalCount(propertiesHelper.getBoolean("asyncTotalCount",false));

        setPoolMaxSize(propertiesHelper.getInt("poolMaxSize",0));

	}
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

    public void setDialectClass(String dialectClass) {
        logger.debug("dialectClass: {} ", dialectClass);
        this.dialectClass = dialectClass;
    }

    public void setAsyncTotalCount(boolean asyncTotalCount) {
        logger.debug("asyncTotalCount: {} ", asyncTotalCount);
        this.asyncTotalCount = asyncTotalCount;
    }

    public void setPoolMaxSize(int poolMaxSize) {

        if(poolMaxSize > 0){
            logger.debug("poolMaxSize: {} ", poolMaxSize);
            Pool = Executors.newFixedThreadPool(poolMaxSize);
        }else{
            Pool = Executors.newCachedThreadPool();
        }


    }
}
