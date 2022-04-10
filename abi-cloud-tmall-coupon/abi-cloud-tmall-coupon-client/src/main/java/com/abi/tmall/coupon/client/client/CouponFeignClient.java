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
     * 添加 积分信息
     *
     * @param spuBoundsAddReq 积分信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/console/spu-bounds/save")
    ApiResponse<Boolean> saveSpuBounds(@RequestBody SpuBoundsAddReq spuBoundsAddReq);

    /**
     * 添加 满减信息
     *
     * @param skuFullReductionAddReqs 满减信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/console/sku-full-reduction/save/batch")
    ApiResponse<Boolean> saveSkuFullReduction(@RequestBody List<SkuFullReductionAddReq> skuFullReductionAddReqs);

    /**
     * 添加 商品折扣信息
     *
     * @param skuLadderAddReqs 折扣信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/console/sku-ladder/save/batch")
    ApiResponse<Boolean> saveSkuLadder(@RequestBody List<SkuLadderAddReq> skuLadderAddReqs);

    /**
     * 添加 商品会员价格
     *
     * @param memberPriceAddReq 会员价格
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/console/member-price/save")
    ApiResponse<Boolean> saveMemberPrice(@RequestBody MemberPriceAddReq memberPriceAddReq);

}
