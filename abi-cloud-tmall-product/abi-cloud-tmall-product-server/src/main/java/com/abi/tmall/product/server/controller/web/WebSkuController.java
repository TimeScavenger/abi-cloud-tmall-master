package com.abi.tmall.product.server.controller.web;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.response.sku.SkuItemResp;
import com.abi.tmall.product.server.service.SkuService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * Sku Web模块
 *
 * @ClassName: WebSkuController
 * @Author: illidan
 * @CreateDate: 2022/02/16
 * @Description:
 */
@Api(tags = "Sku Web模块")
@Slf4j
@RestController
@RequestMapping("/web/sku")
public class WebSkuController {

    @Resource
    private SkuService skuService;

    /**
     * 展示当前sku的详情
     *
     * @param skuCode
     * @return
     */
    @GetMapping("/{skuCode}.html")
    public ApiResponse<SkuItemResp> querySkuItemBySkuCode(@PathVariable("skuCode") Long skuCode) throws ExecutionException, InterruptedException {
        return ApiResponse.result(skuService.querySkuItemBySkuCode(skuCode));
    }
}
