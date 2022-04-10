package com.abi.tmall.search.server.controller.console;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.search.common.request.SkuEsAddReq;
import com.abi.tmall.search.common.request.SkuEsSearchReq;
import com.abi.tmall.search.common.response.SkuEsSearchResp;
import com.abi.tmall.search.server.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 商品模块
 *
 * @ClassName: ProductController
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Api(tags = "商品模块")
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 搜索 商品信息
     *
     * @param skuEsSearchReq 搜索条件
     * @return 搜索结果
     */
    @PostMapping("/search")
    @ApiOperation(value = "搜索 商品信息")
    public ApiResponse<SkuEsSearchResp> searchProductFromEs(@RequestBody SkuEsSearchReq skuEsSearchReq) {
        return ApiResponse.result(productService.searchProductFromEs(skuEsSearchReq));
    }

    /**
     * 添加 商品信息
     *
     * @param skuEsAddReqs 商品信息
     * @return 添加是否成功: true-成功, false-失败
     * @throws IOException
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 商品信息")
    public ApiResponse<Boolean> saveProductToEs(@RequestBody @Validated List<SkuEsAddReq> skuEsAddReqs) throws IOException {
        boolean status = false;
        try {
            // 返回true表示有商品上架失败，返回false表示没有失败
            status = productService.saveProductToEs(skuEsAddReqs);
        } catch (IOException e) {
            log.error("ElasticSaveController - 商品上架错误: ", e);
            return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), ResultCode.FEIGIN_ERROR.message());
        }

        if (status) {
            return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), ResultCode.FEIGIN_ERROR.message());
        } else {
            return ApiResponse.result(false);
        }
    }

}
