package com.abi.tmall.product.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.client.AttributeFeignClient;
import com.abi.tmall.product.common.response.attribute.AttributeInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 商品属性 Feign接口降级
 *
 * @ClassName: AttributeFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@Slf4j
@Component
public class AttributeFeignClientFallback implements FallbackFactory<AttributeFeignClient> {

    @Override
    public AttributeFeignClient create(Throwable throwable) {
        return new AttributeFeignClient() {
            @Override
            public ApiResponse<AttributeInfoResp> findAttributeByCode(Long attributeCode) {
                log.error("ProductFeignClient findAttributeByCode feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "findAttributeByCode feign error");
            }
        };
    }

}
