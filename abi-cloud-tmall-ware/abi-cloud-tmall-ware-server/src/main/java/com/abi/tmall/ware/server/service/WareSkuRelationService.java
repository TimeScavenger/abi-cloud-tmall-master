package com.abi.tmall.ware.server.service;

import com.abi.base.foundation.page.PageResponse;
import com.abi.tmall.ware.common.request.ware.WareStockDto;
import com.abi.tmall.ware.common.request.waresku.WareSkuRelationPageDto;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationPageVo;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationStockVo;
import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: WareSkuRelationService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 库存信息
 */
public interface WareSkuRelationService extends IService<WareSkuRelation> {

    PageResponse<WareSkuRelationPageVo> queryWareSkuRelationPageByCondition(WareSkuRelationPageDto warePageReq);

    void stockWareSkuRelation(Long skuCode, Long wareCode, Integer skuNum);

    List<WareSkuRelationStockVo> querySkuHasStock(WareStockDto wareStockDto);

}
