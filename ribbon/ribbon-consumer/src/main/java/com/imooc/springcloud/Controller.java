package com.imooc.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("sayHi")
    public String sayHi() {
        return restTemplate.getForObject("http://eureka-client/sayHi", String.class);
    }
}
