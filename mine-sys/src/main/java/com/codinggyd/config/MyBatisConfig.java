package com.codinggyd.config;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

@Configuration
//加上这个注解，使得支持事务
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {
  @Autowired
  private DataSource dataSource;

  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
       return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactoryBean() {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(dataSource);
      
  	// 设置mybatis-sql映射文件路径
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources("classpath*:mapper/mybatis-*.xml");
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
			e1.printStackTrace();
		}
		SqlSessionFactory sessionFactory = null;
      try {
    	  sessionFactory = bean.getObject();
    	  //设置分页拦截器
    	// 配置分页插件
			Interceptor interceptor = new OffsetLimitInterceptor();
			Properties p = new Properties();
			p.setProperty("dialectClass", "com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
			p.setProperty("cacheEnabled", "false");
			p.setProperty("useCache", "false");
			p.setProperty("useCache", "false");
			interceptor.setProperties(p);
			sessionFactory.getConfiguration().setCacheEnabled(false);
			sessionFactory.getConfiguration().addInterceptor(interceptor);
      } catch (Exception e) {
          e.printStackTrace();
      }
	return sessionFactory;
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
      return new SqlSessionTemplate(sqlSessionFactory);
  }
  
  @Configuration
  public static class DataSourceConfig implements EnvironmentAware{

  		private RelaxedPropertyResolver propertyResolver;

  		@Override
  		public void setEnvironment(Environment env) {
  			this.propertyResolver = new RelaxedPropertyResolver(env);
  		}
  		
  	    public DataSource dbH2DataSource() {
  			try {
  				Map<String, Object> map = propertyResolver.getSubProperties("spring.datasource");
  				return DruidDataSourceFactory.createDataSource(map);
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  			return null;
  		}
  }
}