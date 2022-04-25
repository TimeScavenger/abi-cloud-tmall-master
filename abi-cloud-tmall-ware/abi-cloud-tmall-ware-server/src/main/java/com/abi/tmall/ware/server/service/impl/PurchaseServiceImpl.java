package com.abi.tmall.ware.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.snowflake.SnowflakeIdWorker;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageResp;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.abi.tmall.ware.dao.entity.WareSkuRelation;
import com.abi.tmall.ware.dao.mapper.PurchaseMapper;
import com.abi.tmall.ware.dao.service.PurchaseDao;
import com.abi.tmall.ware.dao.service.PurchaseItemDao;
import com.abi.tmall.ware.dao.service.WareSkuRelationDao;
import com.abi.tmall.ware.server.enums.PurchaseItemStatusEnum;
import com.abi.tmall.ware.server.enums.PurchaseStatusEnum;
import com.abi.tmall.ware.server.service.PurchaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 采购单 服务实现类
 *
 * @ClassName: PurchaseServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    @Autowired
    private WareSkuRelationDao wareSkuRelationDao;

    /**
     * 查询 采购单分页列表
     *
     * @param req 查询条件
     * @return 采购单分页列表
     */
    @Override
    public PageResponse<PurchasePageResp> queryPurchasePageByCondition(PurchasePageReq req) {
        // 1、新建用于返回的分页对象
        PageResponse<PurchasePageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        req.checkParam();
        // 3、分页查询
        Page<Purchase> page = purchaseDao.queryPageByCondition(req.getPageNo(), req.getPageSize(), req.getStatus(), req.getPurchaseName());

        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 4.1、过滤出采购单Code
            List<Long> purchaseCodes = page.getRecords().stream()
                    .map(Purchase::getPurchaseCode)
                    .collect(Collectors.toList());
            // 查询出采购单
            List<PurchaseItem> purchaseItems = purchaseItemDao.queryListByPurchaseCodes(purchaseCodes);
            Map<Long, List<PurchaseItem>> purchaseCodeMap = purchaseItems.stream()
                    .collect(Collectors.groupingBy(PurchaseItem::getPurchaseCode));

            // 数据进行转换
            List<PurchasePageResp> purchasePageRespList = page.getRecords().stream()
                    .map(purchase -> {
                        PurchasePageResp purchasePageResp = new PurchasePageResp();
                        BeanUtils.copyProperties(purchase, purchasePageResp);
                        if (MapUtils.isNotEmpty(purchaseCodeMap) && purchase.getPurchaseCode() != null) {
                            List<PurchaseItem> purchaseItemList = purchaseCodeMap.get(purchase.getPurchaseCode());
                            if (CollectionUtils.isNotEmpty(purchaseItemList)) {
                                purchasePageResp.setPurchaseDetailCount(purchaseItemList.size());
                            }
                        }
                        return purchasePageResp;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(purchasePageRespList);
        }
        // 5、返回数据
        return pageResponse;
    }

    /**
     * 查询 采购单列表
     *
     * @param req 查询条件
     * @return 采购单列表
     */
    @Override
    public List<Purchase> queryPurchaseListByCondition(PurchaseListReq req) {
        // 1、查询列表信息
        List<Purchase> purchaseList = this.lambdaQuery()
                .eq(req.getStatus() != null, Purchase::getStatus, req.getStatus())
                .list();
        // 2、返回数据
        return purchaseList;
    }

    /**
     * 添加 采购单
     *
     * @param req 采购单
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    public boolean addPurchase(PurchaseAddReq req) {
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(req, purchase);
        purchase.setPurchaseCode(snowflakeIdWorker.nextId());
        return purchaseDao.save(purchase);
    }

    /**
     * 修改 采购单
     *
     * @param req 采购单
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    public boolean modifyPurchase(PurchaseEditReq req) {
        // 1、查询校验分类是否合法
        Purchase purchaseOld = purchaseDao.queryInfoByPurchaseCode(req.getPurchaseCode());
        if (purchaseOld != null) {
            if (!PurchaseStatusEnum.CREATED.getValue().equals(purchaseOld.getStatus()) &&
                    !PurchaseStatusEnum.ASSIGNED.getValue().equals(purchaseOld.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单 状态已领取、已完成，无法修改");
            }

            Purchase purchaseNew = new Purchase();
            BeanUtils.copyProperties(req, purchaseNew);
            purchaseNew.setId(purchaseOld.getId());
            // 2、更新仓库对象数据
            purchaseDao.updateById(purchaseNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    /**
     * 查询 采购单
     *
     * @param req 采购单Code
     * @return 采购单
     */
    @Override
    public Purchase findPurchaseByCode(PurchaseInfoReq req) {
        return purchaseDao.queryInfoByPurchaseCode(req.getPurchaseCode());
    }

    /**
     * 领取 采购单
     *
     * @param req 采购单Code列表
     * @return 领取是否成功: true-成功, false-失败
     */
    @Override
    @Transactional
    public boolean receivePurchase(PurchaseReceiveReq req) {
        // 1、获取当前登录用户的Code
        Long currentCode = 2L;

        // 2、确认当前采购单是新建或者已分配状态，并且是分配给自己的
        List<Purchase> purchaseList = purchaseDao.queryListByPurchaseCodes(req.getPurchaseCodes());
        purchaseList.forEach(item -> {
            if (!PurchaseStatusEnum.CREATED.getValue().equals(item.getStatus()) && !PurchaseStatusEnum.ASSIGNED.getValue().equals(item.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单的状态必须为新建或已分配状态");
            }
            if (!currentCode.equals(item.getAssigneeCode())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单的采购人必须为提交人");
            }
            item.setStatus(PurchaseStatusEnum.RECEIVE.getValue());
        });

        // 3、更新采购单的状态
        purchaseDao.updateBatchById(purchaseList);

        // 4、根据采购单的Code查询出采购项列表，更新采购项的状态
        List<Long> purchaseCodes = purchaseList.stream()
                .map(Purchase::getPurchaseCode)
                .collect(Collectors.toList());
        List<PurchaseItem> purchaseItemList = purchaseItemDao.queryListByPurchaseCodes(purchaseCodes);

        purchaseItemList.forEach(item -> item.setStatus(PurchaseItemStatusEnum.BUYING.getValue()));
        purchaseItemDao.updateBatchById(purchaseItemList);
        return true;
    }

    /**
     * 完成 采购单
     *
     * @param req 采购单Code + 采购项列表
     * @return 完成是否成功: true-成功, false-失败
     */
    @Override
    @Transactional
    public boolean donePurchase(PurchaseDoneReq req) {
        // 1、先处理采购项，全部采购项都完成，再去处理采购单
        Long purchaseCode = req.getPurchaseCode();

        // 2、改变采购项的状态
        boolean flag = true;
        List<PurchaseDoneReq.PurchaseDetailDoneReq> purchaseDetailDoneReqList = req.getPurchaseDetailDoneReqList();
        // 采购项列表
        List<PurchaseItem> allPurchaseItems = new ArrayList<>();
        // 采购项成功列表
        List<Long> successPurchaseDetailCodes = new ArrayList<>();
        for (PurchaseDoneReq.PurchaseDetailDoneReq item : purchaseDetailDoneReqList) {
            PurchaseItem purchaseItem = new PurchaseItem();
            // 判断采购项是否采购成功
            if (PurchaseItemStatusEnum.HASERROR.getValue().equals(item.getStatus())) {
                flag = false;
                purchaseItem.setStatus(item.getStatus());
            } else if (PurchaseItemStatusEnum.FINISH.getValue().equals(item.getStatus())) {
                purchaseItem.setStatus(item.getStatus());
                successPurchaseDetailCodes.add(item.getPurchaseDetailCode());
            }
            purchaseItem.setPurchaseDetailCode(item.getPurchaseDetailCode());
            allPurchaseItems.add(purchaseItem);
        }
        if (CollectionUtils.isNotEmpty(allPurchaseItems)) {
            purchaseItemDao.updateBatchByPurchaseDetailCodes(allPurchaseItems);
        }

        // 3、将成功采购的进行入库
        if (CollectionUtils.isNotEmpty(successPurchaseDetailCodes)) {
            // 根据采购项Code查询出采购项信息
            List<PurchaseItem> successPurchaseItems = purchaseItemDao.queryListByPurchaseDetailCodes(successPurchaseDetailCodes);
            // 过滤出仓库Code列表
            List<Long> wareCodes = successPurchaseItems.stream()
                    .map(PurchaseItem::getWareCode)
                    .collect(Collectors.toList());

            // 根据仓库Code查询出该仓库下的所有商品信息
            List<WareSkuRelation> wareSkuRelations = wareSkuRelationDao.queryListByWareCodes(wareCodes);
            Map<Long, Integer> wareSkuCodeAndStockMap = wareSkuRelations.stream()
                    .collect(Collectors.toMap(key -> key.getWareCode() + key.getSkuCode(), WareSkuRelation::getStock, (v1, v2) -> v1));
            Map<Long, Long> wareSkuCodeAndIdMap = wareSkuRelations.stream()
                    .collect(Collectors.toMap(key -> key.getWareCode() + key.getSkuCode(), WareSkuRelation::getId, (v1, v2) -> v1));

            List<WareSkuRelation> wareSkuRelationAddList = new ArrayList<>();
            List<WareSkuRelation> wareSkuRelationUpdList = new ArrayList<>();
            for (PurchaseItem successPurchaseItem : successPurchaseItems) {
                // 判断仓库中是否有商品，没有商品添加，有商品在原有基础上追加商品数量
                Integer stockOld = wareSkuCodeAndStockMap.get(successPurchaseItem.getWareCode() + successPurchaseItem.getSkuCode());
                WareSkuRelation wareSkuRelation = new WareSkuRelation();
                if (stockOld == null) {
                    wareSkuRelation.setWareCode(successPurchaseItem.getWareCode());
                    wareSkuRelation.setSkuCode(successPurchaseItem.getSkuCode());
                    wareSkuRelation.setStock(successPurchaseItem.getSkuNum());
                    wareSkuRelationAddList.add(wareSkuRelation);
                } else {
                    wareSkuRelation.setId(wareSkuCodeAndIdMap.get(successPurchaseItem.getWareCode() + successPurchaseItem.getSkuCode()));
                    wareSkuRelation.setStock(stockOld + successPurchaseItem.getSkuNum());
                    wareSkuRelationUpdList.add(wareSkuRelation);
                }
            }
            // 判断数据不为空批量操作数据
            if (CollectionUtils.isNotEmpty(wareSkuRelationAddList)) {
                wareSkuRelationDao.saveBatch(wareSkuRelationAddList);
            }
            if (CollectionUtils.isNotEmpty(wareSkuRelationUpdList)) {
                wareSkuRelationDao.updateBatchById(wareSkuRelationUpdList);
            }
        }

        // 4、改变采购单状态
        Purchase purchase = new Purchase();
        purchase.setPurchaseCode(purchaseCode);
        purchase.setStatus(flag ? PurchaseStatusEnum.FINISH.getValue() : PurchaseStatusEnum.HASERROR.getValue());
        purchaseDao.updateInfoByPurchaseCode(purchase);

        return true;
    }

}
