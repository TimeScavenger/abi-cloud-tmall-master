package com.abi.tmall.search.server.client.fallback;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.search.server.client.ProductFeignClient;
import com.abi.tmall.search.server.client.response.attribute.AttributeInfoVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductFeignClientFallback implements FallbackFactory<ProductFeignClient> {

    @Override
    public ProductFeignClient create(Throwable throwable) {
        return new ProductFeignClient() {
            @Override
            public ApiResponse<AttributeInfoVo> findAttributeByCode(Long attributeCode) {
                log.error("ProductFeignClient findAttributeByCode feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "findAttributeByCode feign error");
            }
        };
    }

}
