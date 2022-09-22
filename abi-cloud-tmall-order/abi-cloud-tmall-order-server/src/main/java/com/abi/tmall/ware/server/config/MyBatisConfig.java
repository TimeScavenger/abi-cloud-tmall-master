package com.abi.tmall.ware.server.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * MyBatis分页 配置文件
 *
 * @ClassName: MyBatisConfig
 * @Author: illidan
 * @CreateDate: 2021/2/10
 * @Description:
 */
@EnableTransactionManagement
@MapperScan({"com.abi.tmall.ware.dao.mapper"})
@Configuration
public class MyBatisConfig {

    /**
     * 逻辑删除默认值
     * 设置逻辑删除标识的默认值。如果在配置文件里没有相关的项目时, 使用0作为默认值。
     * <tt>mybatis-plus.global-config.db-config.logic-not-delete-value</tt>
     *
     * @param defaultDeleteValue 插入时逻辑删除的默认值
     */
    @Value("${mybatis-plus.global-config.db-config.logic-not-delete-value:0}")
    private Integer defaultDeleteValue;

    /**
     * 启用MyBatis Plus分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 共通字段处理
     *
     * @return MetaObjectHandler
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }
        };
    }

}
