package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类 持久类
 *
 * @ClassName: CategoryMapper
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description:
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * @param categoryIds 分类id集合
     * @return 分类集合
     */
    List<Category> selectCategoryListByIds(@Param("categoryIds") List<Long> categoryIds);

    /**
     * 父分类id进行调整为父分类Code
     *
     * @param id         自增Id
     * @param parentCode 分类父类Code
     */
    void initCategoryDataTest(@Param("id") long id, @Param("parentCode") long parentCode);

}
