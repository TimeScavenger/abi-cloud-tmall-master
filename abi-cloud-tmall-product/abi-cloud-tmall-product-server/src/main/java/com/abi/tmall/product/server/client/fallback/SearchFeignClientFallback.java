package com.abi.tmall.product.server.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.server.client.SearchFeignClient;
import com.abi.tmall.product.server.client.request.search.SkuSearchAddReq;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SearchFeignClientFallback implements FallbackFactory<SearchFeignClient> {

    @Override
    public SearchFeignClient create(Throwable throwable) {
        return new SearchFeignClient() {
            @Override
            public ApiResponse<Boolean> saveProductToEs(List<SkuSearchAddReq> skuSearchAddReqs) {
                log.error("SearchFeignClient saveProductToEs feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "saveProductToEs feign error");
            }
        };
    }

}
