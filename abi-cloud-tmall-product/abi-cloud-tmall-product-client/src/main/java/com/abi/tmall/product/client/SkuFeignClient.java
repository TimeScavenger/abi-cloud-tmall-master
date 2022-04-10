package com.abi.tmall.product.client;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.client.fallback.SkuFeignClientFallback;
import com.abi.tmall.product.common.request.sku.SkuListByCodeReq;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Sku Feign接口
 *
 * @ClassName: SkuFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@FeignClient(name = "abi-cloud-tmall-product", fallbackFactory = SkuFeignClientFallback.class)
public interface SkuFeignClient {

    /**
     * SpringCloud远程调用原理：CouponFeignService.saveSpuBounds(spuBoundsTo);
     * 1、@RequestBody将这个对象转为json。
     * 2、找到tmall-cloud-coupon服务，给/coupon/console/spubounds/save发送请求。将上一步转的json放在请求体位置，发送请求。
     * 3、对方服务收到请求。请求体里有json数据。(@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 总结：只要json数据模型是兼容的。双方服务无需使用同一个模型
     */

    /**
     * 根据SkuCode查询Sku列表
     *
     * @param skuListByCodeReq skuCode集合
     * @return
     */
    @PostMapping("/console/sku-info/list/by/code")
    public ApiResponse<List<SkuListResp>> querySkuListByCodes(@RequestBody SkuListByCodeReq skuListByCodeReq);

    /**
     * 根据Sku名字查询Sku列表
     *
     * @param skuListByNameReq sku名称集合
     * @return
     */
    @PostMapping("/console/sku-info/list/by/name")
    public ApiResponse<List<SkuListResp>> querySkuListByName(@RequestBody SkuListByNameReq skuListByNameReq);

}
