package com.abi.tmall.coupon.client.client;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.client.client.fallback.CouponFeignClientFallback;
import com.abi.tmall.coupon.common.request.memberprice.MemberPriceAddReq;
import com.abi.tmall.coupon.common.request.skufullreduction.SkuFullReductionAddReq;
import com.abi.tmall.coupon.common.request.skuladder.SkuLadderAddReq;
import com.abi.tmall.coupon.common.request.spubounds.SpuBoundsAddReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 优惠 Feign接口
 *
 * @ClassName: CouponFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@FeignClient(name = "abi-cloud-tmall-coupon", fallbackFactory = CouponFeignClientFallback.class)
public interface CouponFeignClient {

    /**
     * SpringCloud远程调用原理：CouponFeignService.saveSpuBounds(spuBoundsTo);
     * 1、@RequestBody将这个对象转为json。
     * 2、找到tmall-cloud-coupon服务，给/coupon/console/spubounds/save发送请求。将上一步转的json放在请求体位置，发送请求。
     * 3、对方服务收到请求。请求体里有json数据。(@RequestBody SpuBoundsEntity spuBounds)；将请求体的json转为SpuBoundsEntity；
     * 总结：只要json数据模型是兼容的。双方服务无需使用同一个模型。
     */
    @PostMapping("/console/spu-bounds/save")
    ApiResponse<Boolean> saveSpuBounds(@RequestBody SpuBoundsAddReq spuBoundsAddReq);

    @PostMapping("/console/sku-full-reduction/save/batch")
    ApiResponse<Boolean> saveSkuFullReduction(@RequestBody List<SkuFullReductionAddReq> skuFullReductionAddReqs);

    @PostMapping("/console/sku-ladder/save/batch")
    ApiResponse<Boolean> saveSkuLadder(@RequestBody List<SkuLadderAddReq> skuLadderAddReqs);

    @PostMapping("/console/member-price/save")
    ApiResponse<Boolean> saveMemberPrice(@RequestBody MemberPriceAddReq memberPriceAddReq);

}
