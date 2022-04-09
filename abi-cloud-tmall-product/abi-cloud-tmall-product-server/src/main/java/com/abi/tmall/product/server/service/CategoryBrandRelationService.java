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

    List<CbRelationListByCategoryResp> queryCategoryListByBrandCode(CbRelationListByBrandReq cbRelationListByBrandReq);

    List<CbRelationListByBrandResp> queryBrandListByCategoryCode(CbRelationListByCategoryReq cbRelationListByCategoryReq);

    boolean saveBrandCategoryRelation(CbRelationAddReq cbRelationAddReq);

    boolean removeBrandCategoryRelation(CbRelationDelReq cbRelationDelReq);

}

