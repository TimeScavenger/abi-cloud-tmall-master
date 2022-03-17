package com.abi.tmall.product.server.client.fallback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.server.client.WareFeignClient;
import com.abi.tmall.product.server.client.request.ware.WareStockDto;
import com.abi.tmall.product.server.client.response.ware.WareSkuRelationStockVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WareFeignClientFallback implements FallbackFactory<WareFeignClient> {

    @Override
    public WareFeignClient create(Throwable throwable) {
        return new WareFeignClient() {
            @Override
            public ApiResponse<List<WareSkuRelationStockVo>> querySkuHasStock(WareStockDto wareStockDto) {
                log.error("WareFeignClient querySkuHasStock feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "querySkuHasStock feign error");
            }
        };
    }

}
