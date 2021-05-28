package com.imooc.springcloud;

import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class HystrixFallbackApplication {
    public static void main(String[] args) throws NoSuchMethodException {
        new SpringApplication(HystrixFallbackApplication.class).run(args);

        System.out.println(Feign.configKey(MyService.class, MyService.class.getMethod("retry", int.class)));
    }
}
