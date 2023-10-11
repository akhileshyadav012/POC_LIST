package com.apisecurity.APISecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityApplication.class, args);
	}

}
