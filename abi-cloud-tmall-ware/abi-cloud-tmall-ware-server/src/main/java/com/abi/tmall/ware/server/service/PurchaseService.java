package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageVo;
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

    PageResponse<PurchasePageVo> queryPurchasePageByCondition(PurchasePageReq purchasePageReq);

    List<Purchase> queryPurchaseListByCondition(PurchaseListReq purchaseListReq);

    boolean addPurchase(PurchaseAddReq purchaseAddReq);

    boolean modifyPurchase(PurchaseEditReq purchaseEditReq);

    Purchase findPurchaseByCode(PurchaseInfoReq purchaseInfoReq);

    boolean receivePurchase(PurchaseReceiveReq purchaseReceiveReq);

    boolean donePurchase(PurchaseDoneReq purchaseDoneReq);

}
