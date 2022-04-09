package com.abi.tmall.product.server.service;

import com.abi.tmall.product.common.request.category.CategoryAddReq;
import com.abi.tmall.product.common.request.category.CategoryDelReq;
import com.abi.tmall.product.common.request.category.CategoryEditReq;
import com.abi.tmall.product.common.request.category.CategorySortReq;
import com.abi.tmall.product.common.response.category.CategoryInfoResp;
import com.abi.tmall.product.common.response.category.CategoryTreeResp;
import com.abi.tmall.product.dao.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品分类 服务类
 *
 * @ClassName: CategoryService
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description:
 */
public interface CategoryService extends IService<Category> {

    List<CategoryTreeResp> queryCategoryListWithTree(Integer level);

    boolean saveCategory(CategoryAddReq categoryAddReq);

    boolean removeCategory(CategoryDelReq categoryDelReq);

    boolean modifyCategory(CategoryEditReq categoryEditReq);

    boolean modifyCategorySort(List<CategorySortReq> categorySortReqs);

    CategoryInfoResp findCategoryByCode(Long categoryCode);

    Long[] findCategoryPath(Long categoryCode);

}

