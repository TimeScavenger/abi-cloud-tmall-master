package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeReq;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.request.sku.SkuPageReq;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import com.abi.tmall.product.common.response.sku.SkuPageResp;
import com.abi.tmall.product.server.service.SkuService;
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
 * Sku Console模块
 *
 * @ClassName: SkuController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "Sku Console模块")
@Slf4j
@RestController
@RequestMapping("/console/sku-info")
public class ConsoleSkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Sku分页")
    public ApiResponse<PageResponse<SkuPageResp>> querySkuPageByCondition(@RequestBody SkuPageReq skuPageReq) {
        return ApiResponse.result(skuService.querySkuPageByCondition(skuPageReq));
    }

    @PostMapping("/list/by/code")
    @ApiOperation(value = "查询 根据SkuCode查询Sku列表")
    public ApiResponse<List<SkuListResp>> querySkuListByCodes(@RequestBody SkuListByCodeReq skuListByCodeReq) {
        return ApiResponse.result(skuService.querySkuListByCodes(skuListByCodeReq));
    }

    @PostMapping("/list/by/name")
    @ApiOperation(value = "查询 根据Sku名字查询Sku列表")
    public ApiResponse<List<SkuListResp>> querySkuListByName(@RequestBody SkuListByNameReq skuListByNameReq) {
        return ApiResponse.result(skuService.querySkuListByName(skuListByNameReq));
    }

}
