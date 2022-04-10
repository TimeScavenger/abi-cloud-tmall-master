package com.abi.tmall.ware.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.ware.WareStockReq;
import com.abi.tmall.ware.common.request.ware.sku.WareSkuRelationPageReq;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationPageResp;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationStockResp;
import com.abi.tmall.ware.server.service.WareSkuRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品库存 Console模块
 *
 * @ClassName: ConsoleWareSkuRelationController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Api(tags = "商品库存 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/ware-sku-relation")
public class ConsoleWareSkuRelationController {

    @Autowired
    WareSkuRelationService wareSkuRelationService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Sku库存分页")
    public ApiResponse<PageResponse<WareSkuRelationPageResp>> queryWareSkuRelationPageByCondition(@RequestBody WareSkuRelationPageReq warePageReq) {
        return ApiResponse.result(wareSkuRelationService.queryWareSkuRelationPageByCondition(warePageReq));
    }

    @PostMapping(value = "/hasStock")
    @ApiOperation(value = "查询 Sku是否有库存")
    public ApiResponse<List<WareSkuRelationStockResp>> querySkuHasStock(@RequestBody WareStockReq wareStockReq) {
        return ApiResponse.result(wareSkuRelationService.querySkuHasStock(wareStockReq));
    }

}
