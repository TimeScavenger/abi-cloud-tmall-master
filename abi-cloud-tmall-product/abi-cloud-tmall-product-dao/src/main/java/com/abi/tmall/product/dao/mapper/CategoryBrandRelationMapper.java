package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类-品牌关系 持久类
 *
 * @ClassName: BrandCategoryRelationMapper
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description:
 */
@Mapper
public interface CategoryBrandRelationMapper extends BaseMapper<CategoryBrandRelation> {

    /**
     * 查询 分类关联的所有品牌列表
     *
     * @param categoryId 分类id
     * @return 品牌id集合
     */
    List<Long> selectBrandListByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 查询 分类关联的所有品牌列表
     *
     * @param brandId 品牌id
     * @return 分类id集合
     */
    List<CategoryBrandRelation> selectCategoryListByBrandId(@Param("brandId") Long brandId);

}
