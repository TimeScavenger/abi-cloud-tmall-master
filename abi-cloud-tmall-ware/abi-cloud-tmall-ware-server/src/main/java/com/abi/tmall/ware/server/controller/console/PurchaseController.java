package com.abi.tmall.ware.server.controller.console;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageVo;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.server.service.PurchaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: PurchaseController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 采购单
 */
@Api(tags = "采购单")
@Slf4j
@RestController
@RequestMapping("/console/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 采购单分页")
    public ApiResponse<PageResponse<PurchasePageVo>> queryPurchasePageByCondition(@RequestBody PurchasePageDto purchasePageDto) {
        return ApiResponse.result(purchaseService.queryPurchasePageByCondition(purchasePageDto));
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询 采购单列表")
    public ApiResponse<List<Purchase>> queryPurchaseListByCondition(@RequestBody PurchaseListDto purchaseListDto) {
        return ApiResponse.result(purchaseService.queryPurchaseListByCondition(purchaseListDto));
    }
    
    @PostMapping("/save")
    @ApiOperation(value = "新增 采购单")
    public ApiResponse<Boolean> addPurchase(@RequestBody @Validated PurchaseAddDto purchaseAddDto) {
        return ApiResponse.result(purchaseService.addPurchase(purchaseAddDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 采购单")
    public ApiResponse<Boolean> modifyPurchase(@RequestBody @Validated PurchaseEditDto purchaseEditDto) {
        return ApiResponse.result(purchaseService.modifyPurchase(purchaseEditDto));
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询 采购单")
    public ApiResponse<Purchase> findPurchaseByCode(@RequestBody @Validated PurchaseInfoDto purchaseInfoDto) {
        return ApiResponse.result(purchaseService.findPurchaseByCode(purchaseInfoDto));
    }

    @PostMapping("/receive")
    @ApiOperation(value = "领取 采购单")
    public ApiResponse<Boolean> receivePurchase(@RequestBody @Validated PurchaseReceiveDto purchaseReceiveDto) {
        return ApiResponse.result(purchaseService.receivePurchase(purchaseReceiveDto));
    }

    @PostMapping("/done")
    @ApiOperation(value = "完成 采购单")
    public ApiResponse<Boolean> donePurchase(@RequestBody @Validated PurchaseDoneDto purchaseDoneDto) {
        return ApiResponse.result(purchaseService.donePurchase(purchaseDoneDto));
    }
}
