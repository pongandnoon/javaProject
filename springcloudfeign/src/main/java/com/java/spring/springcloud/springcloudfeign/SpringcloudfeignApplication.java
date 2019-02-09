package com.java.spring.springcloud.springcloudfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudfeignApplication.class, args);
    }

}

