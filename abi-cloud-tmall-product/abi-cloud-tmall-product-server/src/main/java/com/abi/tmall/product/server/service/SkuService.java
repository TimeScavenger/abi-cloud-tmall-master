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

    /**
     * 查询 Sku分页列表
     *
     * @param skuPageReq 查询条件
     * @return Sku分页列表
     */
    PageResponse<SkuPageResp> querySkuPageByCondition(SkuPageReq skuPageReq);

    /**
     * 查询 根据SkuCode查询Sku列表
     *
     * @param skuListByCodeReq SkuCode
     * @return Sku列表
     */
    List<SkuListResp> querySkuListByCodes(SkuListByCodeReq skuListByCodeReq);

    /**
     * 查询 根据Sku名字查询Sku列表
     *
     * @param skuListByNameReq Sku名字
     * @return Sku列表
     */
    List<SkuListResp> querySkuListByName(SkuListByNameReq skuListByNameReq);

    /**
     * 根据skuCode查询sku信息
     *
     * @param skuCode skuCode
     * @return Sku信息
     * @throws ExecutionException
     * @throws InterruptedException
     */
    SkuItemResp querySkuItemBySkuCode(Long skuCode) throws ExecutionException, InterruptedException;

}