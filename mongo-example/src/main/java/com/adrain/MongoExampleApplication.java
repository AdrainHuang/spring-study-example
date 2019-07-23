package com.adrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MongoExampleApplication {

	private static ApplicationContext applicationContext = null;
	
	public static void main(String[] args) {
		 applicationContext = SpringApplication.run(MongoExampleApplication.class, args);
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

}
