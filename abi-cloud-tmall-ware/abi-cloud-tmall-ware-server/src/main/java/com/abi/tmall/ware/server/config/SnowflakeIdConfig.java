package com.abi.tmall.ware.server.config;

import com.abi.base.foundation.snowflake.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: SnowflakeIdConfig
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 雪花算法配置文件
 */
@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }

}
