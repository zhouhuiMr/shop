package com.Data.Source;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages= {"com.test"})
public class twoDataSourceConfig {
	
	@Bean(name="dataSourceTwo")
	@ConfigurationProperties(value="spring.datasource.druid.two")
	public DataSource dataSoucesTwo() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean
	public SqlSessionFactory SqlSessionFactoryTwo() throws Exception {
		SqlSessionFactoryBean factorybean = new SqlSessionFactoryBean();
		factorybean.setDataSource(dataSoucesTwo());
		return factorybean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate SqlSessionTempTwo() throws Exception {
		return new SqlSessionTemplate(SqlSessionFactoryTwo());
	}
}
