package com.abi.tmall.ware.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.item.*;
import com.abi.tmall.ware.common.response.purchase.item.PurchaseItemPageResp;
import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.abi.tmall.ware.server.service.PurchaseItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 采购项 Console模块
 *
 * @ClassName: ConsolePurchaseItemController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Api(tags = "采购项 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/purchase-detail")
public class ConsolePurchaseItemController {

    @Autowired
    PurchaseItemService purchaseItemService;

    /**
     * 查询 采购项分页列表
     *
     * @param purchaseItemPageReq 查询条件
     * @return 采购项分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 采购项分页列表")
    public ApiResponse<PageResponse<PurchaseItemPageResp>> queryPurchaseDetailPageByCondition(@RequestBody PurchaseItemPageReq purchaseItemPageReq) {
        return ApiResponse.result(purchaseItemService.queryPurchaseDetailPageByCondition(purchaseItemPageReq));
    }

    /**
     * 添加 采购项
     *
     * @param purchaseItemAddReq 采购项
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 采购项")
    public ApiResponse<Boolean> addPurchaseDetail(@RequestBody @Validated PurchaseItemAddReq purchaseItemAddReq) {
        return ApiResponse.result(purchaseItemService.addPurchaseDetail(purchaseItemAddReq));
    }

    /**
     * 删除 采购项
     *
     * @param purchaseItemDelReq 采购项Code列表
     * @return 删除是否成功: true-成功, false-失败
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除 采购项")
    public ApiResponse<Boolean> removePurchaseDetail(@RequestBody @Validated PurchaseItemDelReq purchaseItemDelReq) {
        return ApiResponse.result(purchaseItemService.removePurchaseDetail(purchaseItemDelReq));
    }

    /**
     * 修改 采购项
     *
     * @param purchaseItemEditReq 采购项
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 采购项")
    public ApiResponse<Boolean> modifyPurchaseDetail(@RequestBody @Validated PurchaseItemEditReq purchaseItemEditReq) {
        return ApiResponse.result(purchaseItemService.modifyPurchaseDetail(purchaseItemEditReq));
    }

    /**
     * 查询 采购项
     *
     * @param purchaseItemInfoReq 采购项Code
     * @return 采购项
     */
    @PostMapping("/info")
    @ApiOperation(value = "查询 采购项")
    public ApiResponse<PurchaseItem> findPurchaseDetailByCode(@RequestBody @Validated PurchaseItemInfoReq purchaseItemInfoReq) {
        return ApiResponse.result(purchaseItemService.findPurchaseDetailByCode(purchaseItemInfoReq));
    }

    /**
     * 合并 采购项
     *
     * @param purchaseItemMergeReq 采购单Code+采购项Code集合
     * @return 合并是否成功: true-成功, false-失败
     */
    @PostMapping("/merge")
    @ApiOperation(value = "合并 采购项")
    public ApiResponse<Boolean> mergePurchaseDetail(@RequestBody @Validated PurchaseItemMergeReq purchaseItemMergeReq) {
        return ApiResponse.result(purchaseItemService.mergePurchaseDetail(purchaseItemMergeReq));
    }

}
