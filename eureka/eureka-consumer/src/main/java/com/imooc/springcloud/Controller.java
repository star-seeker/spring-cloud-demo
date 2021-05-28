package com.imooc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class Controller {

    @Resource
    private LoadBalancerClient client;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("hello")
    public String hello() {
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null) {
            return "No available instances";
        }

        String url = String.format("http://%s:%s/sayHi", instance.getHost(), instance.getPort());
        log.info("url is {}", url);

        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("hello")
    public Friend helloPost() {
        ServiceInstance instance = client.choose("eureka-client");
        if (instance == null) {
            return null;
        }

        String url = String.format("http://%s:%s/sayHi", instance.getHost(), instance.getPort());
        log.info("url is {}", url);

        Friend friend = new Friend();
        friend.setName("Eureka Consumer");

        return restTemplate.postForObject(url, friend, Friend.class);
    }
}
