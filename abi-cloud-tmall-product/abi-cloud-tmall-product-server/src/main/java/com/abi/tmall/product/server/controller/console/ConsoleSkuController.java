package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeDto;
import com.abi.tmall.product.common.request.sku.SkuListByNameDto;
import com.abi.tmall.product.common.request.sku.SkuPageDto;
import com.abi.tmall.product.common.response.sku.SkuListVo;
import com.abi.tmall.product.common.response.sku.SkuPageVo;
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
 * @ClassName: SkuController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: Sku信息
 */
@Api(tags = "Sku信息模块")
@Slf4j
@RestController
@RequestMapping("/console/sku-info")
public class ConsoleSkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Sku分页")
    public ApiResponse<PageResponse<SkuPageVo>> querySkuPageByCondition(@RequestBody SkuPageDto skuPageDto) {
        return ApiResponse.result(skuService.querySkuPageByCondition(skuPageDto));
    }

    @PostMapping("/list/by/code")
    @ApiOperation(value = "查询 根据SkuCode查询Sku列表")
    public ApiResponse<List<SkuListVo>> querySkuListByCodes(@RequestBody SkuListByCodeDto skuListByCodeDto) {
        return ApiResponse.result(skuService.querySkuListByCodes(skuListByCodeDto));
    }

    @PostMapping("/list/by/name")
    @ApiOperation(value = "查询 根据Sku名字查询Sku列表")
    public ApiResponse<List<SkuListVo>> querySkuListByName(@RequestBody SkuListByNameDto skuListByNameDto) {
        return ApiResponse.result(skuService.querySkuListByName(skuListByNameDto));
    }

}
