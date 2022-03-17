package com.abi.tmall.ware.server.service;

import com.abi.base.foundation.page.PageResponse;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchaseDetailPageVo;
import com.abi.tmall.ware.dao.entity.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName: PurchaseDetailService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 采购项
 */
public interface PurchaseDetailService extends IService<PurchaseDetail> {

    PageResponse<PurchaseDetailPageVo> queryPurchaseDetailPageByCondition(PurchaseDetailPageDto purchaseDetailPageDto);

    boolean addPurchaseDetail(PurchaseDetailAddDto purchaseDetailAddDto);

    boolean removePurchaseDetail(PurchaseDetailDelDto purchaseDetailDelDto);

    boolean modifyPurchaseDetail(PurchaseDetailEditDto purchaseDetailEditDto);

    PurchaseDetail findPurchaseDetailByCode(PurchaseDetailInfoDto purchaseDetailInfoDto);

    boolean mergePurchaseDetail(PurchaseDetailMergeDto purchaseDetailMergeDto);

}
