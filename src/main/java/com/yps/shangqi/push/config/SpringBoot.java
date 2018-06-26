package com.yps.shangqi.push.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
    basePackages = "com.yps.shangqi.push"
)
@MapperScan(
    basePackages = "com.yps.shangqi.push.mapper",
    sqlSessionFactoryRef = "sqlSessionFactory"
)
public class SpringBoot {

  private static String MAPPER_LOCATION = "classpath:/mappers/**/*Mapper.xml";

  @Value("${datasource.url}")
  private String url;
  @Value("${datasource.username}")
  private String username;
  @Value("${datasource.password}")
  private String password;
  @Value("${datasource.driverClassName}")
  private String driverClassName;

  @Bean
  public DataSource dataSource() {
    DruidDataSource datasource = new DruidDataSource();
    datasource.setUrl(url);
    datasource.setUsername(username);
    datasource.setPassword(password);
    // common
    datasource.setDriverClassName(driverClassName);

    return datasource;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());
    try {
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
      return sqlSessionFactoryBean;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    //开启开启驼峰命名转换
    sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}
