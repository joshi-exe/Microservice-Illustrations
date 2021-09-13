package com.yash.msdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TheDataStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheDataStoreApplication.class, args);
	}

}
