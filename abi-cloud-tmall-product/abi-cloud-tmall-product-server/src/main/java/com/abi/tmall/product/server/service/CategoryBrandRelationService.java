package com.abi.tmall.product.server.service;

import com.abi.tmall.product.common.request.category.CbRelationListByCategoryDto;
import com.abi.tmall.product.common.request.category.CbRelationListByBrandDto;
import com.abi.tmall.product.common.request.category.CbRelationAddDto;
import com.abi.tmall.product.common.request.category.CbRelationDelDto;
import com.abi.tmall.product.common.response.category.CbRelationListByBrandVo;
import com.abi.tmall.product.common.response.category.CbRelationListByCategoryVo;
import com.abi.tmall.product.dao.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: CategoryBrandRelationService
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description: 商品分类-品牌关系
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {

    List<CbRelationListByCategoryVo>queryCategoryListByBrandCode(CbRelationListByBrandDto cbRelationListByBrandDto);

    List<CbRelationListByBrandVo> queryBrandListByCategoryCode(CbRelationListByCategoryDto cbRelationListByCategoryDto);

    boolean saveBrandCategoryRelation(CbRelationAddDto cbRelationAddDto);

    boolean removeBrandCategoryRelation(CbRelationDelDto cbRelationDelDto);

}

