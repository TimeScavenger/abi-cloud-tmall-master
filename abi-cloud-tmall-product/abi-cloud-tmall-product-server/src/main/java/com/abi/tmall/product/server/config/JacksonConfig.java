package com.abi.tmall.product.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson 配置文件
 *
 * @ClassName: JacksonConfig
 * @Author: illidan
 * @CreateDate: 2021/11/10
 * @Description: 统一注解，解决前后端交互Long类型精度丢失的问题
 */
@Configuration
public class JacksonConfig {

    /**
     * 对于前后台传参过程中的Long类型，JS内置有32位整数，而number类型的安全整数是53位。如果超过53位，则精度会丢失。
     * 如果后台传来一个64位的Long型整数，因为超过了53位，所以后台返回的值和前台获取的值会不一样。
     * 出现数据解析后不一致的问题。也就是超出位数的数据无法正确解析，而位数以内的数据可以正确解析。
     *
     * @param builder
     * @return
     */
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

}