package com.abi.tmall.ware.client.client;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.ware.client.client.falback.WareFeignClientFallback;
import com.abi.tmall.ware.common.request.ware.WareStockReq;
import com.abi.tmall.ware.common.response.ware.sku.WareSkuRelationStockResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 仓库 Feign接口
 *
 * @ClassName: WareFeignClient
 * @Author: illidan
 * @CreateDate: 2022/4/10
 * @Description:
 */
@FeignClient(name = "abi-cloud-tmall-ware", fallbackFactory = WareFeignClientFallback.class)
public interface WareFeignClient {

    /**
     * 查询 Sku是否有库存
     *
     * @param wareStockReq SkuCode列表
     * @return 商品库存数列表
     */
    @PostMapping(value = "/console/ware-sku-relation/hasStock")
    public ApiResponse<List<WareSkuRelationStockResp>> querySkuHasStock(@RequestBody WareStockReq wareStockReq);

}