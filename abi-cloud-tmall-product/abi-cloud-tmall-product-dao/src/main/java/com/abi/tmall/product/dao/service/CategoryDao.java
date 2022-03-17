package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Category;
import com.abi.tmall.product.dao.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: CategoryDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 商品分类
 */
@Repository
public class CategoryDao extends ServiceImpl<CategoryMapper, Category> {

    public List<Category> queryListByLevel(List<Integer> levelList) {
        List<Category> categorys = this.lambdaQuery()
                .isNotNull(Category::getLevel)
                .isNotNull(Category::getParentCode)
                .in(Category::getLevel, levelList)
                .list();
        return categorys;
    }

    public List<Category> queryListByCategoryCodes(List<Long> categoryCodes) {
        List<Category> categories = this.lambdaQuery()
                .in(Category::getCategoryCode, categoryCodes)
                .list();
        return categories;
    }

    public Category queryInfoByCategoryCode(Long categoryCode) {
        Category category = this.lambdaQuery()
                .eq(Category::getCategoryCode, categoryCode)
                .one();
        return category;
    }

    public Category queryInfoByCategoryNameAndParentCode(String categoryName, Long parentCode) {
        Category category = this.lambdaQuery()
                .eq(Category::getCategoryName, categoryName)
                .eq(Category::getParentCode, parentCode)
                .one();
        return category;
    }

    public boolean deleteByCategoryCodes(List<Long> categoryCodes) {
        if (CollectionUtils.isNotEmpty(categoryCodes)) {
            LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(Category::getCategoryCode, categoryCodes)
                    .set(Category::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
