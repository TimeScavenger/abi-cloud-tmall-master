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

    PageResponse<WareSkuRelationPageResp> queryWareSkuRelationPageByCondition(WareSkuRelationPageReq warePageReq);

    void stockWareSkuRelation(Long skuCode, Long wareCode, Integer skuNum);

    List<WareSkuRelationStockResp> querySkuHasStock(WareStockReq wareStockReq);

}
