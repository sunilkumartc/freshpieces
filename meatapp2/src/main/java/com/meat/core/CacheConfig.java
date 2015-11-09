package com.meat.core;



import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
 
@Configuration
@EnableCaching
@PropertySource("classpath:/properties/platform.properties")
public class CacheConfig {
 
 private @Value("${redis.host-name}") String redisHostName;
 private @Value("${redis.port}") int redisPort;
 
 @Bean
 public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
     return new PropertySourcesPlaceholderConfigurer();
 }
 
 @Bean
 JedisConnectionFactory jedisConnectionFactory() {
     JedisConnectionFactory factory = new JedisConnectionFactory();
     factory.setHostName(redisHostName);
     factory.setPort(redisPort);
     factory.setUsePool(true);
     return factory;
 }
 
 @Bean
 RedisTemplate<Object, Object> redisTemplate() {
     RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
     redisTemplate.setConnectionFactory(jedisConnectionFactory());
     return redisTemplate;
 }
 
 @Bean
 CacheManager cacheManager() {
	 String CACHE_NAME = "otp";
	 RedisTemplate< Object, Object> redisTemplate = redisTemplate();
 	 RedisCache redisCache = new RedisCache(CACHE_NAME, CACHE_NAME.concat(":").getBytes(), redisTemplate(), TimeUnit.SECONDS.toSeconds(60));
     return new RedisCacheManager(redisTemplate);
 }

 @Bean
 Cache cache(){
	 return cacheManager().getCache("otp");
 }
 
}