package com.abi.tmall.ware.server.service;

import com.abi.base.foundation.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageVo;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: PurchaseService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 采购单
 */
public interface PurchaseService extends IService<Purchase> {

    PageResponse<PurchasePageVo> queryPurchasePageByCondition(PurchasePageDto purchasePageDto);

    List<Purchase> queryPurchaseListByCondition(PurchaseListDto purchaseListDto);

    boolean addPurchase(PurchaseAddDto purchaseAddDto);

    boolean modifyPurchase(PurchaseEditDto purchaseEditDto);

    Purchase findPurchaseByCode(PurchaseInfoDto purchaseInfoDto);

    boolean receivePurchase(PurchaseReceiveDto purchaseReceiveDto);

    boolean donePurchase(PurchaseDoneDto purchaseDoneDto);

}
