package com.jyzt.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.jyzt.common.exception", "com.jyzt.es"})
@RestController
public class EsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsServerApplication.class, args);
    }

    @GetMapping("/test")
    public String test(){
        return "test es server";
    }

}