package com.abi.tmall.ware.server.client;

import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.server.client.fallback.ProductFeignClientFallback;
import com.abi.tmall.ware.server.client.request.SkuListByCodeReq;
import com.abi.tmall.ware.server.client.request.SkuListByNameReq;
import com.abi.tmall.ware.server.client.response.SkuListResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "abi-cloud-tmall-product", fallbackFactory = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    /**
     * SpringCloud远程调用原理：CouponFeignService.saveSpuBounds(spuBoundsTo);
     * 1、@RequestBody将这个对象转为json。
     * 2、找到tmall-cloud-coupon服务，给/coupon/console/spubounds/save发送请求。将上一步转的json放在请求体位置，发送请求。
     * 3、对方服务收到请求。请求体里有json数据。(@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 总结：只要json数据模型是兼容的。双方服务无需使用同一个to
     */
    @PostMapping("/product/console/sku-info/list/by/code")
    public ApiResponse<List<SkuListResp>> querySkuListByCodes(@RequestBody SkuListByCodeReq skuListByCodeReq);

    @PostMapping("/product/console/sku-info/list/by/name")
    public ApiResponse<List<SkuListResp>> querySkuListByName(@RequestBody SkuListByNameReq skuListByNameReq);

}
