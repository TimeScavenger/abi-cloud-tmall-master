package com.abi.tmall.product.server.service;

import com.abi.tmall.product.common.request.category.CategoryDelDto;
import com.abi.tmall.product.common.request.category.CategoryEditDto;
import com.abi.tmall.product.common.request.category.CategoryAddDto;
import com.abi.tmall.product.common.request.category.CategorySortDto;
import com.abi.tmall.product.common.response.category.CategoryInfoVo;
import com.abi.tmall.product.common.response.category.CategoryTreeVo;
import com.abi.tmall.product.dao.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 商品分类
 */
public interface CategoryService extends IService<Category> {

    List<CategoryTreeVo> queryCategoryListWithTree(Integer level);

    boolean saveCategory(CategoryAddDto categoryAddDto);

    boolean removeCategory(CategoryDelDto categoryDelDto);

    boolean modifyCategory(CategoryEditDto categoryEditDto);

    boolean modifyCategorySort(List<CategorySortDto> categorySortDtos);

    CategoryInfoVo findCategoryByCode(Long categoryCode);

    Long[] findCategoryPath(Long categoryCode);

}

