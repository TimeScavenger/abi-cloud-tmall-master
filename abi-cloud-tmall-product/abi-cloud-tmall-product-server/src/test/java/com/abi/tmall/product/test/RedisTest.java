package com.abi.tmall.product.test;

import com.abi.tmall.product.dao.mapper.CategoryMapper;
import com.abi.tmall.product.server.TmallProductServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TmallProductServerApplication.class)
public class RedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 分类表：父分类id进行调整为父分类Code
     */
    @Test
    public void redisTest() {
        // 连接Redis
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 保存
        ops.set("hello", "world_" + UUID.randomUUID().toString());

        // 查询
        String hello = ops.get("hello");
        System.out.println("Redis保存的数据：" + hello);
    }

}

