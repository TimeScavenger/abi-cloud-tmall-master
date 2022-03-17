package com.abi.tmall.ware.server.client.fallback;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.server.client.ProductFeignClient;
import com.abi.tmall.ware.server.client.request.SkuListByCodeReq;
import com.abi.tmall.ware.server.client.request.SkuListByNameReq;
import com.abi.tmall.ware.server.client.response.SkuListResp;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductFeignClientFallback implements FallbackFactory<ProductFeignClient> {

    @Override
    public ProductFeignClient create(Throwable throwable) {
        return new ProductFeignClient() {

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