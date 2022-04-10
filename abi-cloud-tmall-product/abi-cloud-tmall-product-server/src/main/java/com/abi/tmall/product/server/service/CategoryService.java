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

    /**
     * 查询 分类以及子分类 以树形结构组装起来
     *
     * @param level 层级
     * @return 分类以及子分类树形结构
     */
    List<CategoryTreeResp> queryCategoryListWithTree(Integer level);

    /**
     * 添加 分类信息
     *
     * @param categoryAddReq 分类信息
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveCategory(CategoryAddReq categoryAddReq);

    /**
     * 删除 根据分类Codes删除分类信息
     *
     * @param categoryDelReq 分类Codes列表
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeCategory(CategoryDelReq categoryDelReq);

    /**
     * 修改 分类信息
     *
     * @param categoryEditReq 分类信息
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyCategory(CategoryEditReq categoryEditReq);

    /**
     * 修改 分类排序
     *
     * @param categorySortReqs 分类信息列表
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyCategorySort(List<CategorySortReq> categorySortReqs);

    /**
     * 查询 根据分类Code查询分类信息
     *
     * @param categoryCode 分类Code
     * @return 分类信息
     */
    CategoryInfoResp findCategoryByCode(Long categoryCode);

    /**
     * 查询 分类完整路径；[父/子/孙] [2, 25, 225]
     *
     * @param categoryCode 分类Code
     * @return 类全层次路径 [父/子/孙] [2, 25, 225]
     */
    Long[] findCategoryPath(Long categoryCode);

}

