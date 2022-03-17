package com.abi.tmall.product.test;

import com.abi.tmall.product.dao.mapper.CategoryMapper;
import com.abi.tmall.product.server.TmallProductServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TmallProductServerApplication.class)
public class initMysqlDataTest {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 分类表：父分类id进行调整为父分类Code
     */
    @Test
    public void initCategoryDataTest() {
        for (long i = 1; i < 2000; i++) {
            categoryMapper.initCategoryDataTest(i, i);
        }
    }

}

