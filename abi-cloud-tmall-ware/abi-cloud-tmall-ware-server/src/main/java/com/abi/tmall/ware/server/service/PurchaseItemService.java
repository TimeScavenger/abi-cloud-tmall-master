package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.item.*;
import com.abi.tmall.ware.common.response.purchase.item.PurchaseItemPageResp;
import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 采购项 服务实现类
 *
 * @ClassName: PurchaseDetailService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
public interface PurchaseItemService extends IService<PurchaseItem> {

    PageResponse<PurchaseItemPageResp> queryPurchaseDetailPageByCondition(PurchaseItemPageReq purchaseItemPageReq);

    boolean addPurchaseDetail(PurchaseItemAddReq purchaseItemAddReq);

    boolean removePurchaseDetail(PurchaseItemDelReq purchaseItemDelReq);

    boolean modifyPurchaseDetail(PurchaseItemEditReq purchaseItemEditReq);

    PurchaseItem findPurchaseDetailByCode(PurchaseItemInfoReq purchaseItemInfoReq);

    boolean mergePurchaseDetail(PurchaseItemMergeReq purchaseItemMergeReq);

}
