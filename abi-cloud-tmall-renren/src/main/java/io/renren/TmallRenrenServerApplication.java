/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // 服务注册到注册中心
@SpringBootApplication
public class TmallRenrenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallRenrenServerApplication.class, args);
        System.out.println("------------ abi-cloud-tmall-renren 人人服务 启动成功 ------------");
    }

}
