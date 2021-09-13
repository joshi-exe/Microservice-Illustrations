package com.yash.msdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TheGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheGatewayApplication.class, args);
	}

}
