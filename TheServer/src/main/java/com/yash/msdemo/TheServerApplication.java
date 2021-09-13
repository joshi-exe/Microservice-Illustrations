package com.yash.msdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TheServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheServerApplication.class, args);
	}

}
