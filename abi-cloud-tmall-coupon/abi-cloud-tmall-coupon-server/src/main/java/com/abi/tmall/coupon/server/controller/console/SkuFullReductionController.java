package com.abi.tmall.coupon.server.controller.console;

import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.SkuFullReduction;
import com.abi.tmall.coupon.server.service.SkuFullReductionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: SkuFullReductionController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: 商品满减信息
 */
@Api(tags = "商品满减信息")
@Slf4j
@RestController
@RequestMapping("/console/sku-full-reduction")
public class SkuFullReductionController {

    @Autowired
    private SkuFullReductionService skuFullReductionService;

    /**
     * 保存 满减信息
     *
     * @param skuFullReduction
     * @return
     */
    @PostMapping("/save")
    public ApiResponse<Boolean> saveSkuFullReduction(@RequestBody SkuFullReduction skuFullReduction) {
        return ApiResponse.result(skuFullReductionService.save(skuFullReduction));
    }

    /**
     * 批量保存 满减信息
     *
     * @param skuFullReductions
     * @return
     */
    @PostMapping("/save/batch")
    public ApiResponse<Boolean> batchSaveSkuFullReduction(@RequestBody List<SkuFullReduction> skuFullReductions) {
        return ApiResponse.result(skuFullReductionService.saveBatch(skuFullReductions));
    }
}
