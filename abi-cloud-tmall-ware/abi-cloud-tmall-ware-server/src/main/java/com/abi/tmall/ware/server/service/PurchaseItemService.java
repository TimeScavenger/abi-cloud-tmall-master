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

    /**
     * 查询 采购项分页列表
     *
     * @param purchaseItemPageReq 查询条件
     * @return 采购项分页列表
     */
    PageResponse<PurchaseItemPageResp> queryPurchaseDetailPageByCondition(PurchaseItemPageReq purchaseItemPageReq);

    /**
     * 新增 采购项
     *
     * @param purchaseItemAddReq 采购项
     * @return 新增是否成功: true-成功, false-失败
     */
    boolean addPurchaseDetail(PurchaseItemAddReq purchaseItemAddReq);

    /**
     * 删除 采购项
     *
     * @param purchaseItemDelReq 采购项Code列表
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removePurchaseDetail(PurchaseItemDelReq purchaseItemDelReq);

    /**
     * 修改 采购项
     *
     * @param purchaseItemEditReq 采购项
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyPurchaseDetail(PurchaseItemEditReq purchaseItemEditReq);

    /**
     * 查询 采购项
     *
     * @param purchaseItemInfoReq 采购项Code
     * @return 采购项
     */
    PurchaseItem findPurchaseDetailByCode(PurchaseItemInfoReq purchaseItemInfoReq);

    /**
     * 合并 采购项
     *
     * @param purchaseItemMergeReq 采购单Code+采购项Code集合
     * @return 合并是否成功: true-成功, false-失败
     */
    boolean mergePurchaseDetail(PurchaseItemMergeReq purchaseItemMergeReq);

}
