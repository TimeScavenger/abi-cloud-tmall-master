package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageResp;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 采购单 服务类
 *
 * @ClassName: PurchaseService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
public interface PurchaseService extends IService<Purchase> {

    /**
     * 查询 采购单分页列表
     *
     * @param purchasePageReq 查询条件
     * @return 采购单分页列表
     */
    PageResponse<PurchasePageResp> queryPurchasePageByCondition(PurchasePageReq purchasePageReq);

    /**
     * 查询 采购单列表
     *
     * @param purchaseListReq 查询条件
     * @return 采购单列表
     */
    List<Purchase> queryPurchaseListByCondition(PurchaseListReq purchaseListReq);

    /**
     * 新增 采购单
     *
     * @param purchaseAddReq 采购单
     * @return 新增是否成功: true-成功, false-失败
     */
    boolean addPurchase(PurchaseAddReq purchaseAddReq);

    /**
     * 修改 采购单
     *
     * @param purchaseEditReq 采购单
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyPurchase(PurchaseEditReq purchaseEditReq);

    /**
     * 查询 采购单
     *
     * @param purchaseInfoReq 采购单Code
     * @return 采购单
     */
    Purchase findPurchaseByCode(PurchaseInfoReq purchaseInfoReq);

    /**
     * 领取 采购单
     *
     * @param purchaseReceiveReq 采购单Code列表
     * @return 领取是否成功: true-成功, false-失败
     */
    boolean receivePurchase(PurchaseReceiveReq purchaseReceiveReq);

    /**
     * 完成 采购单
     *
     * @param purchaseDoneReq 采购单Code + 采购项列表
     * @return 完成是否成功: true-成功, false-失败
     */
    boolean donePurchase(PurchaseDoneReq purchaseDoneReq);

}
