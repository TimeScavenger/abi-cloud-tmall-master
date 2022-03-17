package com.abi.tmall.ware.server.controller.console;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchaseDetailPageVo;
import com.abi.tmall.ware.dao.entity.PurchaseDetail;
import com.abi.tmall.ware.server.service.PurchaseDetailService;
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
 * @ClassName: PurchaseDetailController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 采购项
 */
@Api(tags = "采购项")
@Slf4j
@RestController
@RequestMapping("/console/purchase-detail")
public class PurchaseDetailController {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 采购项分页")
    public ApiResponse<PageResponse<PurchaseDetailPageVo>> queryPurchaseDetailPageByCondition(@RequestBody PurchaseDetailPageDto purchaseDetailPageDto) {
        return ApiResponse.result(purchaseDetailService.queryPurchaseDetailPageByCondition(purchaseDetailPageDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增 采购项")
    public ApiResponse<Boolean> addPurchaseDetail(@RequestBody @Validated PurchaseDetailAddDto purchaseDetailAddDto) {
        return ApiResponse.result(purchaseDetailService.addPurchaseDetail(purchaseDetailAddDto));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 采购项")
    public ApiResponse<Boolean> removePurchaseDetail(@RequestBody @Validated PurchaseDetailDelDto purchaseDetailDelDto) {
        return ApiResponse.result(purchaseDetailService.removePurchaseDetail(purchaseDetailDelDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 采购项")
    public ApiResponse<Boolean> modifyPurchaseDetail(@RequestBody @Validated PurchaseDetailEditDto purchaseDetailEditDto) {
        return ApiResponse.result(purchaseDetailService.modifyPurchaseDetail(purchaseDetailEditDto));
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询 采购项")
    public ApiResponse<PurchaseDetail> findPurchaseDetailByCode(@RequestBody @Validated PurchaseDetailInfoDto purchaseDetailInfoDto) {
        return ApiResponse.result(purchaseDetailService.findPurchaseDetailByCode(purchaseDetailInfoDto));
    }

    @PostMapping("/merge")
    @ApiOperation(value = "合并 采购项")
    public ApiResponse<Boolean> mergePurchaseDetail(@RequestBody @Validated PurchaseDetailMergeDto purchaseDetailMergeDto) {
        return ApiResponse.result(purchaseDetailService.mergePurchaseDetail(purchaseDetailMergeDto));
    }

}
