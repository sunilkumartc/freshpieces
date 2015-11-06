
package com.meat.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("com.meat")
public class MeatApp {
	
	public static void main(String[] args) {
		SpringApplication.run(MeatApp.class, args);	
		
	}
	
}
