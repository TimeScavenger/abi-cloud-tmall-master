package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.ware.WareStockReq;
import com.abi.tmall.ware.common.request.ware.sku.WareSkuRelationPageReq;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationPageResp;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationStockResp;
import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品库存 服务类
 *
 * @ClassName: WareSkuRelationService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
public interface WareSkuRelationService extends IService<WareSkuRelation> {

    /**
     * 查询 Sku库存分页列表
     *
     * @param warePageReq 查询条件
     * @return Sku库存分页列表
     */
    PageResponse<WareSkuRelationPageResp> queryWareSkuRelationPageByCondition(WareSkuRelationPageReq warePageReq);

    /**
     * 查询 Sku是否有库存
     *
     * @param wareStockReq SkuCode列表
     * @return 商品库存数列表
     */
    List<WareSkuRelationStockResp> querySkuHasStock(WareStockReq wareStockReq);

    /**
     * 添加商品库存
     *
     * @param skuCode  skuCode
     * @param wareCode 仓库Code
     * @param skuNum   sku数量
     */
    void stockWareSkuRelation(Long skuCode, Long wareCode, Integer skuNum);
}
