package com.arisha1999.springbootmicroservicefindhistorybydates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.arisha1999.springbootmicroservicefindhistorybydates")
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootMicroserviceFindHistoryByDatesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroserviceFindHistoryByDatesApplication.class, args);
    }
}
