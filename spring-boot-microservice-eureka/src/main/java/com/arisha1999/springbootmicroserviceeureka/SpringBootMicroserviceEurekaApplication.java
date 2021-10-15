package com.arisha1999.springbootmicroserviceeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootMicroserviceEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroserviceEurekaApplication.class, args);
	}
}
