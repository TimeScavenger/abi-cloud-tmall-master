package com.abi.tmall.product.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeReq;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.request.sku.SkuPageReq;
import com.abi.tmall.product.common.response.sku.SkuItemResp;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import com.abi.tmall.product.common.response.sku.SkuPageResp;
import com.abi.tmall.product.dao.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Sku 服务类
 *
 * @ClassName: SkuService
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
public interface SkuService extends IService<Sku> {

    PageResponse<SkuPageResp> querySkuPageByCondition(SkuPageReq skuPageReq);

    List<SkuListResp> querySkuListByCodes(SkuListByCodeReq skuListByCodeReq);

    List<SkuListResp> querySkuListByName(SkuListByNameReq skuListByNameReq);

    SkuItemResp querySkuItemBySkuCode(Long skuCode) throws ExecutionException, InterruptedException;

}