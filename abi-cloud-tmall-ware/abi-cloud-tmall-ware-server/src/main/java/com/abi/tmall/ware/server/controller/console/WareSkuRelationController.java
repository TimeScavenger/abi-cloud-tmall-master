package com.abi.tmall.ware.server.controller.console;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.ware.WareStockDto;
import com.abi.tmall.ware.common.request.waresku.WareSkuRelationPageDto;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationPageVo;
import com.abi.tmall.ware.common.response.waresku.WareSkuRelationStockVo;
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
 * @ClassName: WareSkuRelationController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 库存信息
 */
@Api(tags = "库存信息")
@Slf4j
@RestController
@RequestMapping("/console/ware-sku-relation")
public class WareSkuRelationController {

    @Autowired
    WareSkuRelationService wareSkuRelationService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Sku库存分页")
    public ApiResponse<PageResponse<WareSkuRelationPageVo>> queryWareSkuRelationPageByCondition(@RequestBody WareSkuRelationPageDto warePageReq) {
        return ApiResponse.result(wareSkuRelationService.queryWareSkuRelationPageByCondition(warePageReq));
    }

    @PostMapping(value = "/hasStock")
    @ApiOperation(value = "查询 Sku是否有库存")
    public ApiResponse<List<WareSkuRelationStockVo>> querySkuHasStock(@RequestBody WareStockDto wareStockDto) {
        return ApiResponse.result(wareSkuRelationService.querySkuHasStock(wareStockDto));
    }

}
