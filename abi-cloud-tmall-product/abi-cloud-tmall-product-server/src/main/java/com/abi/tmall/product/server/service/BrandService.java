package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoVo;
import com.abi.tmall.product.common.response.brand.BrandListVo;
import com.abi.tmall.product.common.response.brand.BrandPageVo;
import com.abi.tmall.product.dao.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: BrandService
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 商品品牌
 */
public interface BrandService extends IService<Brand> {

    PageResponse<BrandPageVo> queryBrandPageByCondition(BrandPageDto brandPageDto);

    List<BrandListVo> queryBrandListByCondition(BrandListDto brandListDto);

    List<BrandListVo> queryBrandListByCodes(List<Long> brandCodes);

    List<String> queryBrandNameListByCodes(List<Long> brandCodes);

    boolean saveBrand(BrandAddDto brandAddDto);

    boolean removeBrand(BrandDelDto brandCodes);

    boolean modifyBrand(BrandEditDto brandEditDto);

    boolean modifyBrandShowed(BrandShowedDto brandShowedDto);

    boolean modifyBrandEnabled(Long brandCode, Integer enabled);

    BrandInfoVo findBrandByCode(Long brandCode);

}

