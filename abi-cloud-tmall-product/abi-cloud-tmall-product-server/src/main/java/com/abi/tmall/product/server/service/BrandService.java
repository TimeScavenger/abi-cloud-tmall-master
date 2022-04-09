package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoResp;
import com.abi.tmall.product.common.response.brand.BrandListResp;
import com.abi.tmall.product.common.response.brand.BrandPageResp;
import com.abi.tmall.product.dao.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品品牌 服务类
 *
 * @ClassName: BrandService
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
public interface BrandService extends IService<Brand> {

    PageResponse<BrandPageResp> queryBrandPageByCondition(BrandPageReq brandPageReq);

    List<BrandListResp> queryBrandListByCondition(BrandListReq brandListReq);

    List<BrandListResp> queryBrandListByCodes(List<Long> brandCodes);

    List<String> queryBrandNameListByCodes(List<Long> brandCodes);

    boolean saveBrand(BrandAddReq brandAddReq);

    boolean removeBrand(BrandDelReq brandCodes);

    boolean modifyBrand(BrandEditReq brandEditReq);

    boolean modifyBrandShowed(BrandShowedReq brandShowedReq);

    boolean modifyBrandEnabled(Long brandCode, Integer enabled);

    BrandInfoResp findBrandByCode(Long brandCode);

}

