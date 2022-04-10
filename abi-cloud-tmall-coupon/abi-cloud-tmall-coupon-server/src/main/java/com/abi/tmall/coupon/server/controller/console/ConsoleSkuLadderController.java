package com.abi.tmall.coupon.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.SkuLadder;
import com.abi.tmall.coupon.server.service.SkuLadderService;
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
 * 商品折扣信息 Console模块
 *
 * @ClassName: ConsoleSkuLadderController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "商品折扣信息")
@Slf4j
@RestController
@RequestMapping("/console/sku-ladder")
public class ConsoleSkuLadderController {

    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 添加 商品折扣信息
     *
     * @param skuLadder 折扣信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 商品折扣信息")
    public ApiResponse<Boolean> saveSkuLadder(@RequestBody SkuLadder skuLadder) {
        return ApiResponse.result(skuLadderService.save(skuLadder));
    }

    /**
     * 批量添加 商品折扣信息
     *
     * @param skuLadders 折扣信息列表
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save/batch")
    @ApiOperation(value = "批量添加 商品折扣信息")
    public ApiResponse<Boolean> batchSaveSkuLadder(@RequestBody List<SkuLadder> skuLadders) {
        return ApiResponse.result(skuLadderService.saveBatch(skuLadders));
    }
}
