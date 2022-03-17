package com.abi.tmall.product.server.client;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.server.client.fallback.CouponFeignClientFallback;
import com.abi.tmall.product.server.client.request.coupon.MemberPriceReq;
import com.abi.tmall.product.server.client.request.coupon.SkuFullReductionAddReq;
import com.abi.tmall.product.server.client.request.coupon.SkuLadderAddReq;
import com.abi.tmall.product.server.client.request.coupon.SpuBoundsAddReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "abi-cloud-tmall-coupon", fallbackFactory = CouponFeignClientFallback.class)
public interface CouponFeignClient {

    /**
     * SpringCloud远程调用原理：CouponFeignService.saveSpuBounds(spuBoundsTo);
     * 1、@RequestBody将这个对象转为json。
     * 2、找到tmall-cloud-coupon服务，给/coupon/console/spubounds/save发送请求。将上一步转的json放在请求体位置，发送请求。
     * 3、对方服务收到请求。请求体里有json数据。(@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 总结：只要json数据模型是兼容的。双方服务无需使用同一个to
     *
     * @param spuBoundsAddReq
     * @return
     */
    @PostMapping("/coupon/console/spu-bounds/save")
    ApiResponse<Boolean> saveSpuBounds(@RequestBody SpuBoundsAddReq spuBoundsAddReq);

    @PostMapping("/coupon/console/sku-full-reduction/save/batch")
    ApiResponse<Boolean> saveSkuFullReduction(@RequestBody List<SkuFullReductionAddReq> skuFullReductionAddReqs);

    @PostMapping("/coupon/console/sku-ladder/save/batch")
    ApiResponse<Boolean> saveSkuLadder(@RequestBody List<SkuLadderAddReq> skuLadderAddReqs);

    @PostMapping("/coupon/console/member-price/save")
    ApiResponse<Boolean> saveMemberPrice(@RequestBody MemberPriceReq memberPriceReq);

}
