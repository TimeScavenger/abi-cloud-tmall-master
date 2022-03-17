package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.CategoryBrandRelation;
import com.abi.tmall.product.dao.mapper.CategoryBrandRelationMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: BrandCategoryRelationDao
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description: 商品分类-品牌关系
 */
@Repository
public class CategoryBrandRelationDao extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> {

    public List<CategoryBrandRelation> queryCategoryListByBrandCode(Long brandCode) {
        List<CategoryBrandRelation> categoryBrandRelations = this.lambdaQuery()
                .eq(CategoryBrandRelation::getBrandCode, brandCode)
                .list();
        return categoryBrandRelations;
    }

    public List<CategoryBrandRelation> queryBrandListByCategoryCode(Long categoryCode) {
        List<CategoryBrandRelation> categoryBrandRelations = this.lambdaQuery()
                .eq(CategoryBrandRelation::getCategoryCode, categoryCode)
                .list();
        return categoryBrandRelations;
    }

    public CategoryBrandRelation queryListByBrandCodeAndCategoryCode(Long brandCode, Long categoryCode) {
        CategoryBrandRelation categoryBrandRelation = this.lambdaQuery()
                .eq(CategoryBrandRelation::getBrandCode, brandCode)
                .eq(CategoryBrandRelation::getCategoryCode, categoryCode)
                .one();
        return categoryBrandRelation;

    }

    public boolean updateBrandNameByBrandCode(Long brandCode, String brandName) {
        LambdaUpdateWrapper<CategoryBrandRelation> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(CategoryBrandRelation::getBrandCode, brandCode)
                .set(CategoryBrandRelation::getBrandName, brandName);
        this.update(null, updateWrapper);
        return true;
    }

    public boolean updateCategoryNameByCategoryCode(Long categoryCode, String categoryName) {
        LambdaUpdateWrapper<CategoryBrandRelation> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(CategoryBrandRelation::getCategoryCode, categoryCode)
                .set(CategoryBrandRelation::getCategoryName, categoryName);
        this.update(null, updateWrapper);
        return true;
    }

}
