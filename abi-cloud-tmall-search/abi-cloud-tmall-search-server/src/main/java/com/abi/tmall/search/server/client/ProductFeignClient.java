package com.abi.tmall.search.server.client;

import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.search.server.client.fallback.ProductFeignClientFallback;
import com.abi.tmall.search.server.client.response.attribute.AttributeInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "abi-cloud-tmall-product", fallbackFactory = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    @GetMapping("/product/console/attribute/find/{attributeCode}")
    public ApiResponse<AttributeInfoVo> findAttributeByCode(@PathVariable("attributeCode") Long attributeCode);

}
