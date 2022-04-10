package com.abi.tmall.product.client;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.client.fallback.AttributeFeignClientFallback;
import com.abi.tmall.product.common.response.attribute.AttributeInfoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品属性 Feign接口
 *
 * @ClassName: AttributeFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@FeignClient(name = "abi-cloud-tmall-product", fallbackFactory = AttributeFeignClientFallback.class)
public interface AttributeFeignClient {

    /**
     * 根据属性Code查询属性的信息
     *
     * @param attributeCode 商品属性Code
     * @return
     */
    @GetMapping("/product/console/attribute/find/{attributeCode}")
    public ApiResponse<AttributeInfoResp> findAttributeByCode(@PathVariable("attributeCode") Long attributeCode);

}
