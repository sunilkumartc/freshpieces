package com.meat.core;

import javax.annotation.Resource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@PropertySource(value={"classpath:/properties/platform.properties","classpath:/properties/SQLQueries.properties"})
public class DBConfig {

	@Resource
	private Environment environment;
	
	
	@Bean
	public DataSource setSellerDataSource(){
	     DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.driver"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.jdbcurl"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.user"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.password"));
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate setJdbcTemplate(){
		JdbcTemplate jdbcTemplate= new JdbcTemplate();
		jdbcTemplate.setDataSource(setSellerDataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(setSellerDataSource());
		return namedParameterJdbcTemplate;
	}
	
	
	
	@Bean
	public Gson getGson(){
		GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return gson;
	}
	
	
}
