package com.abi.tmall.coupon.server.controller.console;

import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.SkuLadder;
import com.abi.tmall.coupon.server.service.SkuLadderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: SkuLadderController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: 商品折扣信息
 */
@Api(tags = "商品折扣信息")
@Slf4j
@RestController
@RequestMapping("/console/sku-ladder")
public class SkuLadderController {

    @Autowired
    private SkuLadderService skuLadderService;

    /**
     * 保存 折扣信息
     *
     * @param skuLadder
     * @return
     */
    @PostMapping("/save")
    public ApiResponse<Boolean> saveSkuLadder(@RequestBody SkuLadder skuLadder) {
        return ApiResponse.result(skuLadderService.save(skuLadder));
    }

    /**
     * 批量保存 折扣信息
     *
     * @param skuLadders
     * @return
     */
    @PostMapping("/save/batch")
    public ApiResponse<Boolean> batchSaveSkuLadder(@RequestBody List<SkuLadder> skuLadders) {
        return ApiResponse.result(skuLadderService.saveBatch(skuLadders));
    }
}
