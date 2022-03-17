package com.abi.tmall.product.server.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @ClassName: RedissonConfig
 * @Author: illidan
 * @CreateDate: 2021/11/30
 * @Description: Redisson 配置文件
 */
@Configuration
public class RedissonConfig {

    /**
     * 所有对 Redisson 的使用都是通过 RedissonClient
     *
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        // 1、创建配置
        Config config = new Config();
        // Redis url should start with redis:// or rediss://
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123456");

        // 2、根据 Config 创建出 RedissonClient 实例
        return Redisson.create(config);
    }

}
