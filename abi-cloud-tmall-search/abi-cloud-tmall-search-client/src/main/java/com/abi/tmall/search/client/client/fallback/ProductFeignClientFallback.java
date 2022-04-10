package com.abi.tmall.search.client.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.search.client.client.ProductFeignClient;
import com.abi.tmall.search.common.request.SkuEsAddReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductFeignClientFallback implements FallbackFactory<ProductFeignClient> {

    @Override
    public ProductFeignClient create(Throwable throwable) {
        return new ProductFeignClient() {
            @Override
            public ApiResponse<Boolean> saveProductToEs(List<SkuEsAddReq> skuEsAddReqList) {
                log.error("SearchFeignClient saveProductToEs feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveProductToEs feign error");
            }
        };
    }

}
