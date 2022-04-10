package com.abi.tmall.coupon.client.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.client.client.CouponFeignClient;
import com.abi.tmall.coupon.common.request.memberprice.MemberPriceAddReq;
import com.abi.tmall.coupon.common.request.skufullreduction.SkuFullReductionAddReq;
import com.abi.tmall.coupon.common.request.skuladder.SkuLadderAddReq;
import com.abi.tmall.coupon.common.request.spubounds.SpuBoundsAddReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 优惠 Feign接口降级
 *
 * @ClassName: CouponFeignClientFallback
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@Slf4j
@Component
public class CouponFeignClientFallback implements FallbackFactory<CouponFeignClient> {

    @Override
    public CouponFeignClient create(Throwable throwable) {
        return new CouponFeignClient() {
            @Override
            public ApiResponse<Boolean> saveSpuBounds(SpuBoundsAddReq spuBoundsAddReq) {
                log.error("CouponFeignClient saveSpuBounds feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveSpuBounds feign error");
            }

            @Override
            public ApiResponse<Boolean> saveSkuFullReduction(List<SkuFullReductionAddReq> skuFullReductionAddReqs) {
                log.error("CouponFeignClient saveSkuFullReduction feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveSkuFullReduction feign error");
            }

            @Override
            public ApiResponse<Boolean> saveSkuLadder(List<SkuLadderAddReq> skuLadderAddReqs) {
                log.error("CouponFeignClient saveSkuLadder feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveSkuLadder feign error");
            }

            @Override
            public ApiResponse<Boolean> saveMemberPrice(MemberPriceAddReq memberPriceAddReq) {
                log.error("CouponFeignClient saveMemberPrice feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveMemberPrice feign error");
            }
        };
    }
}
