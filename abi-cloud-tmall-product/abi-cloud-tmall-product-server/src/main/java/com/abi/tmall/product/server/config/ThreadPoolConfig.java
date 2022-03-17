package com.abi.tmall.product.server.config;

import com.abi.tmall.product.server.properties.ThreadPoolConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadPoolConfig
 * @Author: illidan
 * @CreateDate: 2022/2/16
 * @Description: 线程池的配置
 */
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
@Configuration
public class ThreadPoolConfig {

    // 先有7个能直接得到执行，接下来的50个进入队列排队，再多开13个继续执行。现在70个被安排上了。剩下30个默认拒绝策略。
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool) {
        return new ThreadPoolExecutor(
                pool.getCoreSize(), // 核心线程数
                pool.getMaxSize(), // 最大线程数
                pool.getKeepAliveTime(),  // 存活时间
                TimeUnit.SECONDS, // 时间单位
                new LinkedBlockingDeque<>(100000), // 阻塞队列
                Executors.defaultThreadFactory(), // 线程的创建工厂
                new ThreadPoolExecutor.AbortPolicy() // 如果队列满了，按照我们指定的拒绝策略拒绝执行任务
        );
    }

}
