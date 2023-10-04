package com.rion.rionojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rion.rionojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.rion")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.rion.rionojbackendserviceclient.service"})
public class RionojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RionojBackendUserServiceApplication.class, args);
    }

}
