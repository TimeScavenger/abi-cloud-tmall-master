package com.abi.tmall.coupon.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.SkuFullReduction;
import com.abi.tmall.coupon.server.service.SkuFullReductionService;
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
 * 商品满减信息 Console模块
 *
 * @ClassName: ConsoleSkuFullReductionController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "商品满减信息")
@Slf4j
@RestController
@RequestMapping("/console/sku-full-reduction")
public class ConsoleSkuFullReductionController {

    @Autowired
    private SkuFullReductionService skuFullReductionService;

    /**
     * 添加 满减信息
     *
     * @param skuFullReduction 满减信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 商品满减信息")
    public ApiResponse<Boolean> saveSkuFullReduction(@RequestBody SkuFullReduction skuFullReduction) {
        return ApiResponse.result(skuFullReductionService.save(skuFullReduction));
    }

    /**
     * 批量添加 商品满减信息
     *
     * @param skuFullReductions 满减信息 列表
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save/batch")
    @ApiOperation(value = "批量添加 商品满减信息")
    public ApiResponse<Boolean> batchSaveSkuFullReduction(@RequestBody List<SkuFullReduction> skuFullReductions) {
        return ApiResponse.result(skuFullReductionService.saveBatch(skuFullReductions));
    }
}
