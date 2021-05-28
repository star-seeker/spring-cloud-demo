package com.imooc.springcloud;

import com.imooc.springcloud.rules.MyRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "eureka-client", configuration = MyRule.class)
public class RibbonConfiguration {
}
