package com.abi.tmall.product.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.client.SkuFeignClient;
import com.abi.tmall.product.common.request.sku.SkuListByCodeReq;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SkuFeignClientFallback implements FallbackFactory<SkuFeignClient> {

    @Override
    public SkuFeignClient create(Throwable throwable) {
        return new SkuFeignClient() {
            @Override
            public ApiResponse<List<SkuListResp>> querySkuListByCodes(SkuListByCodeReq skuListByCodeReq) {
                log.error("ProductFeignClient querySkuListByCodes feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "querySkuListByCodes feign error");
            }

            @Override
            public ApiResponse<List<SkuListResp>> querySkuListByName(SkuListByNameReq skuListByNameReq) {
                log.error("ProductFeignClient querySkuListByName feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "querySkuListByName feign error");
            }
        };
    }

}