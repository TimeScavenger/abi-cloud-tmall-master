package com.abi.tmall.ware.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageResp;
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
 * 采购单 Console模块
 *
 * @ClassName: ConsolePurchaseController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Api(tags = "采购单 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/purchase")
public class ConsolePurchaseController {

    @Autowired
    PurchaseService purchaseService;

    /**
     * 查询 采购单分页列表
     *
     * @param purchasePageReq 查询条件
     * @return 采购单分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 采购单分页")
    public ApiResponse<PageResponse<PurchasePageResp>> queryPurchasePageByCondition(@RequestBody PurchasePageReq purchasePageReq) {
        return ApiResponse.result(purchaseService.queryPurchasePageByCondition(purchasePageReq));
    }

    /**
     * 查询 采购单列表
     *
     * @param purchaseListReq 查询条件
     * @return 采购单列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询 采购单列表")
    public ApiResponse<List<Purchase>> queryPurchaseListByCondition(@RequestBody PurchaseListReq purchaseListReq) {
        return ApiResponse.result(purchaseService.queryPurchaseListByCondition(purchaseListReq));
    }

    /**
     * 添加 采购单
     *
     * @param purchaseAddReq 采购单
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 采购单")
    public ApiResponse<Boolean> addPurchase(@RequestBody @Validated PurchaseAddReq purchaseAddReq) {
        return ApiResponse.result(purchaseService.addPurchase(purchaseAddReq));
    }

    /**
     * 修改 采购单
     *
     * @param purchaseEditReq 采购单
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 采购单")
    public ApiResponse<Boolean> modifyPurchase(@RequestBody @Validated PurchaseEditReq purchaseEditReq) {
        return ApiResponse.result(purchaseService.modifyPurchase(purchaseEditReq));
    }

    /**
     * 查询 采购单
     *
     * @param purchaseInfoReq 采购单Code
     * @return 采购单
     */
    @PostMapping("/info")
    @ApiOperation(value = "查询 采购单")
    public ApiResponse<Purchase> findPurchaseByCode(@RequestBody @Validated PurchaseInfoReq purchaseInfoReq) {
        return ApiResponse.result(purchaseService.findPurchaseByCode(purchaseInfoReq));
    }

    /**
     * 领取 采购单
     *
     * @param purchaseReceiveReq 采购单Code列表
     * @return 领取是否成功: true-成功, false-失败
     */
    @PostMapping("/receive")
    @ApiOperation(value = "领取 采购单")
    public ApiResponse<Boolean> receivePurchase(@RequestBody @Validated PurchaseReceiveReq purchaseReceiveReq) {
        return ApiResponse.result(purchaseService.receivePurchase(purchaseReceiveReq));
    }

    /**
     * 完成 采购单
     *
     * @param purchaseDoneReq 采购单Code + 采购项列表
     * @return 完成是否成功: true-成功, false-失败
     */
    @PostMapping("/done")
    @ApiOperation(value = "完成 采购单")
    public ApiResponse<Boolean> donePurchase(@RequestBody @Validated PurchaseDoneReq purchaseDoneReq) {
        return ApiResponse.result(purchaseService.donePurchase(purchaseDoneReq));
    }
}
