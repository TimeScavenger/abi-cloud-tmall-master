package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.purchasedetail.*;
import com.abi.tmall.ware.common.response.purchase.PurchaseDetailPageVo;
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

    PageResponse<PurchaseDetailPageVo> queryPurchaseDetailPageByCondition(PurchaseDetailPageReq purchaseDetailPageReq);

    boolean addPurchaseDetail(PurchaseDetailAddReq purchaseDetailAddReq);

    boolean removePurchaseDetail(PurchaseDetailDelReq purchaseDetailDelReq);

    boolean modifyPurchaseDetail(PurchaseDetailEditReq purchaseDetailEditReq);

    PurchaseItem findPurchaseDetailByCode(PurchaseDetailInfoReq purchaseDetailInfoReq);

    boolean mergePurchaseDetail(PurchaseDetailMergeReq purchaseDetailMergeReq);

}
