package com.imooc.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private IService iService;

    @GetMapping("sayHi")
    public String sayHi(){
        return iService.sayHi();
    }
}
