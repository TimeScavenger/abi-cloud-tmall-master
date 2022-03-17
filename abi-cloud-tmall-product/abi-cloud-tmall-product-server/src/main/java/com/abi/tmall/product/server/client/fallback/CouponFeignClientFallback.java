package com.abi.tmall.product.server.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.server.client.CouponFeignClient;
import com.abi.tmall.product.server.client.request.coupon.MemberPriceReq;
import com.abi.tmall.product.server.client.request.coupon.SkuFullReductionAddReq;
import com.abi.tmall.product.server.client.request.coupon.SkuLadderAddReq;
import com.abi.tmall.product.server.client.request.coupon.SpuBoundsAddReq;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
            public ApiResponse<Boolean> saveMemberPrice(MemberPriceReq memberPriceReq) {
                log.error("CouponFeignClient saveMemberPrice feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveMemberPrice feign error");
            }
        };
    }
}
