package com.abi.tmall.coupon.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.abi")
@EnableFeignClients(basePackages = "com.abi")  // 告诉Spring，远程调用的接口都在哪个包下
@EnableDiscoveryClient // 服务注册到注册中心
@EnableTransactionManagement
@SpringBootApplication
public class TmallCouponServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallCouponServerApplication.class, args);
        System.out.println("------------ abi-cloud-tmall-coupon 优惠服务 启动成功 ------------");
    }

}
