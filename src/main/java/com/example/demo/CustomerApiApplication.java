package com.example.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class CustomerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

	
	@Bean
    public Executor taskExecutor() {		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();		
		executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);	    
	    executor.initialize();
		return executor;
	  }
}
