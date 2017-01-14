package com.codinggyd.dbconfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.codinggyd.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor2;
import com.codinggyd.log.Log;

/**
 * 获取第二个数据库的连接信息，在application.properties中配置，并指定特定的前缀
 *
 * @author 小翼
 * @version 1.0.0
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = false)
public class MyBatisConfiguration {
	private static final Logger logger = Log.get();

	@Autowired
	@Qualifier("myBatisSqlSessionFactory")
	SqlSessionFactory myBatisSqlSessionFactory;

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface Mapper {

	}

	@Bean(name = "mybatisSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate() {
		return new SqlSessionTemplate(myBatisSqlSessionFactory);
	}

	@Configuration
	public static class MyBatisDataSource implements EnvironmentAware {

		private RelaxedPropertyResolver propertyResolver;

		@Override
		public void setEnvironment(Environment env) {
			this.propertyResolver = new RelaxedPropertyResolver(env);
		}

		@Bean(name = "mybatisDataSource", initMethod = "init", destroyMethod = "close")
		@Qualifier("mybatisDataSource")
		public DataSource dbH2DataSource() {
			try {
				Map<String, Object> map = propertyResolver.getSubProperties("primary.datasource.");
				return DruidDataSourceFactory.createDataSource(map);
			} catch (Exception e) {
				Log.error(logger, e, "载入jdbcProperties失败");
			}
			return null;
		}
	}

	@Configuration
	public static class MyBatisDBConfig {
		@Autowired
		@Qualifier("mybatisDataSource")
		DataSource mybatisDataSource;

		@Bean(name = "myBatisSqlSessionFactory")
		// @Resource(name = "mybatisdataSource")
		public SqlSessionFactory sqlSessionFactory() {
			SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
			bean.setDataSource(mybatisDataSource);
			// 设置mybatis-sql映射文件路径
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = null;
			try {
				resources = resolver.getResources("classpath*:com/**/mybatis-*.xml");
				bean.setMapperLocations(resources);
				bean.setEnvironment("localCacheScope=STATEMENT");
				Properties sqlSessionFactoryProperties = new Properties();
				sqlSessionFactoryProperties.setProperty("cacheEnabled", "false");
				sqlSessionFactoryProperties.setProperty("isUseCache", "false");
				sqlSessionFactoryProperties.setProperty("localCacheScope", "STATEMENT");
				// sqlSessionFactoryProperties.setProperty("lazyLoadingEnabled",
				// "false");
				// sqlSessionFactoryProperties.setProperty("aggressiveLazyLoading",
				// "true");
				bean.setConfigurationProperties(sqlSessionFactoryProperties);
				bean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder() {

					public SqlSessionFactory build(org.apache.ibatis.session.Configuration config) {
						config.setCacheEnabled(false);
						config.setLocalCacheScope(LocalCacheScope.STATEMENT);
						return new DefaultSqlSessionFactory(config);
					}
				});
				bean.afterPropertiesSet();
			} catch (Exception e1) {
				Log.warn(logger, "配置文件加载异常,异常信息:{}", e1.getMessage());
			}

			SqlSessionFactory sqlSessionFactory = null;
			try {
				sqlSessionFactory = bean.getObject();
				// 配置分页插件
				Interceptor interceptor = new OffsetLimitInterceptor2();
				Properties p = new Properties();
				p.setProperty("dialectClass", "com.codinggyd.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
				p.setProperty("cacheEnabled", "false");
				p.setProperty("useCache", "false");
				p.setProperty("useCache", "false");
				interceptor.setProperties(p);
				sqlSessionFactory.getConfiguration().setCacheEnabled(false);
				sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
				// // 配置测试拦截器
				// if (isTest) {
				// Interceptor funTestInterceptor = new
				// MyBatisFuncTestInterceptor();
				// sqlSessionFactory.getConfiguration().addInterceptor(funTestInterceptor);
				//
				// }
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			return sqlSessionFactory;
		}

		@Bean(name = "myBatisTransactionManager")
		// @Resource(name = "mybatisdataSource")
		DataSourceTransactionManager myBatisTran() {
			org.springframework.jdbc.datasource.DataSourceTransactionManager tranMa = new DataSourceTransactionManager();
			tranMa.setDataSource(mybatisDataSource);
			return tranMa;
		}
	}

	@Configuration
	// @MapperScan(basePackages =
	// {"com.gildata.cloud.app.webapp.data.*.dao"},markerInterface=BaseMapper.class,
	// annotationClass = Mapper.class, sqlSessionFactoryRef =
	// "sqlSessionFactory") sqlSessionFactoryRef = "myBatisSqlSessionFactory",
	// markerInterface = BaseMapper.class,
	@MapperScan(basePackages = {"com.codinggyd" }, markerInterface = BaseMapper.class, sqlSessionTemplateRef = "mybatisSqlSessionTemplate")
	public static class MyBatisMapperConfig {
		// @Value("${}")
		// static String basePackages;
		// MapperScan注解不要和数据源定义的配置写在一起，（如MybatisH2Config配置上），
		// 否此会导致循环引用初始化bean问题．
		// 看来xml配置还是有优势的

		// 03-11 17:01:50.010 main WARN o.s.b.f.s.DefaultListableBeanFactory -
		// Bean creation
		// exception on FactoryBean type check:
		// org.springframework.beans.factory.BeanCreationException: Error
		// creating bean with name
		// 'userMapper' defined in file
		// [/home/cui/workspace/spring4-2015/target/classes/com/doctor/spring4/common/mapper/UserMapper.class]:
		// Cannot resolve reference to bean 'dbH2SqlSessionFactory' while
		// setting bean property
		// 'sqlSessionFactory'; nested exception is
		// org.springframework.beans.factory.BeanCurrentlyInCreationException:
		// Error creating bean
		// with name 'dbH2SqlSessionFactory': Requested bean is currently in
		// creation: Is there an
		// unresolvable circular reference?

	}
}
