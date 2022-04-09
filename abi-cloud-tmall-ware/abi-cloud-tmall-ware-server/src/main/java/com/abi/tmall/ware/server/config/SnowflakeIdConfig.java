package com.abi.tmall.ware.server.config;

import com.abi.infrastructure.web.snowflake.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Snowflake 配置文件
 *
 * @ClassName: SnowflakeIdConfig
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Configuration
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(0, 0);
    }

}
