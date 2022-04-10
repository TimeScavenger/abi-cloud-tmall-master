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

    @PostMapping("/page")
    @ApiOperation(value = "查询 采购项分页")
    public ApiResponse<PageResponse<PurchaseItemPageResp>> queryPurchaseDetailPageByCondition(@RequestBody PurchaseItemPageReq purchaseItemPageReq) {
        return ApiResponse.result(purchaseItemService.queryPurchaseDetailPageByCondition(purchaseItemPageReq));
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增 采购项")
    public ApiResponse<Boolean> addPurchaseDetail(@RequestBody @Validated PurchaseItemAddReq purchaseItemAddReq) {
        return ApiResponse.result(purchaseItemService.addPurchaseDetail(purchaseItemAddReq));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 采购项")
    public ApiResponse<Boolean> removePurchaseDetail(@RequestBody @Validated PurchaseItemDelReq purchaseItemDelReq) {
        return ApiResponse.result(purchaseItemService.removePurchaseDetail(purchaseItemDelReq));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 采购项")
    public ApiResponse<Boolean> modifyPurchaseDetail(@RequestBody @Validated PurchaseItemEditReq purchaseItemEditReq) {
        return ApiResponse.result(purchaseItemService.modifyPurchaseDetail(purchaseItemEditReq));
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询 采购项")
    public ApiResponse<PurchaseItem> findPurchaseDetailByCode(@RequestBody @Validated PurchaseItemInfoReq purchaseItemInfoReq) {
        return ApiResponse.result(purchaseItemService.findPurchaseDetailByCode(purchaseItemInfoReq));
    }

    @PostMapping("/merge")
    @ApiOperation(value = "合并 采购项")
    public ApiResponse<Boolean> mergePurchaseDetail(@RequestBody @Validated PurchaseItemMergeReq purchaseItemMergeReq) {
        return ApiResponse.result(purchaseItemService.mergePurchaseDetail(purchaseItemMergeReq));
    }

}
