package com.imooc.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {

    @Resource
    private MyService myService;

    @Resource
    private RequestCacheService requestCacheService;

    @GetMapping("fallback")
    public String fallback() {
        return myService.error();
    }

    @GetMapping("timeout")
    public String timeout(Integer timeout) {
        return myService.retry(timeout);
    }

    @GetMapping("timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String timeout2(Integer timeout) {
        return myService.retry(timeout);
    }

    public String timeoutFallback(Integer timeout) {
        return "success";
    }

    @GetMapping("cache")
    public Friend cache(String name) {

        @Cleanup HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Friend friend;
        friend = requestCacheService.requestCache(name);
        System.out.println(friend);
        friend = requestCacheService.requestCache(name);
        return friend;

    }
}
