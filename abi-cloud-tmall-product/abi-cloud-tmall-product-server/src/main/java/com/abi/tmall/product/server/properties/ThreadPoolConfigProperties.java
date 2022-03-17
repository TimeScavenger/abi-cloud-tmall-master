package com.abi.tmall.product.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ThreadPoolConfigProperties
 * @Author: illidan
 * @CreateDate: 2022/2/16
 * @Description: 线程池的参数配置
 */
@ConfigurationProperties(prefix = "tmall.thread")
//@Component
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;

}
