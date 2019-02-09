package com.java.spring.springcloud.serverCopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务提供方，同时也是一个Eureka Client
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudservercopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudservercopyApplication.class, args);
    }

}

