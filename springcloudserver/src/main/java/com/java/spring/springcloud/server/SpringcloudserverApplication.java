package com.java.spring.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供方，同时也是一个Eureka Client
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudserverApplication.class, args);
    }

}

