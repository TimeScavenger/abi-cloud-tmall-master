package com.abi.tmall.ware.client.client.falback;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.ware.client.client.WareFeignClient;
import com.abi.tmall.ware.common.request.ware.WareStockReq;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationStockResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 仓库 Feign接口降级
 *
 * @ClassName: WareFeignClientFallback
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@Slf4j
@Component
public class WareFeignClientFallback implements FallbackFactory<WareFeignClient> {

    @Override
    public WareFeignClient create(Throwable throwable) {
        return new WareFeignClient() {
            @Override
            public ApiResponse<List<WareSkuRelationStockResp>> querySkuHasStock(WareStockReq wareStockReq) {
                log.error("WareFeignClient querySkuHasStock feign error", throwable);
                return ApiResponse.result(ResultCode.FEIGIN_ERROR.code(), "querySkuHasStock feign error");
            }
        };
    }

}
