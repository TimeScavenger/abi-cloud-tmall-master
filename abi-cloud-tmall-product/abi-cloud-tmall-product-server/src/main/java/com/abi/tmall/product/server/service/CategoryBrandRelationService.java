package com.abi.tmall.product.server.service;

import com.abi.tmall.product.common.request.category.CbRelationAddReq;
import com.abi.tmall.product.common.request.category.CbRelationDelReq;
import com.abi.tmall.product.common.request.category.CbRelationListByBrandReq;
import com.abi.tmall.product.common.request.category.CbRelationListByCategoryReq;
import com.abi.tmall.product.common.response.category.CbRelationListByBrandResp;
import com.abi.tmall.product.common.response.category.CbRelationListByCategoryResp;
import com.abi.tmall.product.dao.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品分类-品牌关系 服务类
 *
 * @ClassName: CategoryBrandRelationService
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description:
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {

    /**
     * 查询 品牌关联的所有分类列表
     *
     * @param cbRelationListByBrandReq 品牌Code
     * @return 分类列表
     */
    List<CbRelationListByCategoryResp> queryCategoryListByBrandCode(CbRelationListByBrandReq cbRelationListByBrandReq);

    /**
     * 查询 分类关联的所有品牌列表
     *
     * @param cbRelationListByCategoryReq 分类Code
     * @return 品牌列表
     */
    List<CbRelationListByBrandResp> queryBrandListByCategoryCode(CbRelationListByCategoryReq cbRelationListByCategoryReq);

    /**
     * 添加 品牌和分类关系的对象
     *
     * @param cbRelationAddReq 品牌和分类关系
     * @return 添加是否成功: true-成功, false-失败
     */
    boolean saveBrandCategoryRelation(CbRelationAddReq cbRelationAddReq);

    /**
     * 删除 品牌和分类关系的对象
     *
     * @param cbRelationDelReq 品牌和分类关系
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeBrandCategoryRelation(CbRelationDelReq cbRelationDelReq);

}

