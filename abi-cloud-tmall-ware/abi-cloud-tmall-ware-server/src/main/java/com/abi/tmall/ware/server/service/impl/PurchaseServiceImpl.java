package com.abi.tmall.ware.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.snowflake.SnowflakeIdWorker;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchasePageVo;
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

    @Override
    public PageResponse<PurchasePageVo> queryPurchasePageByCondition(PurchasePageReq dto) {
        // 1、新建用于返回的分页对象
        PageResponse<PurchasePageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        Page<Purchase> page = purchaseDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getStatus(), dto.getPurchaseName());

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
            List<PurchasePageVo> purchasePageVoList = page.getRecords().stream()
                    .map(purchase -> {
                        PurchasePageVo purchasePageVo = new PurchasePageVo();
                        BeanUtils.copyProperties(purchase, purchasePageVo);
                        if (MapUtils.isNotEmpty(purchaseCodeMap) && purchase.getPurchaseCode() != null) {
                            List<PurchaseItem> purchaseItemList = purchaseCodeMap.get(purchase.getPurchaseCode());
                            if (CollectionUtils.isNotEmpty(purchaseItemList)) {
                                purchasePageVo.setPurchaseDetailCount(purchaseItemList.size());
                            }
                        }
                        return purchasePageVo;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(purchasePageVoList);
        }
        // 5、返回数据
        return pageResponse;
    }

    @Override
    public List<Purchase> queryPurchaseListByCondition(PurchaseListReq dto) {
        // 1、查询列表信息
        List<Purchase> purchaseList = this.lambdaQuery()
                .eq(dto.getStatus() != null, Purchase::getStatus, dto.getStatus())
                .list();
        // 2、返回数据
        return purchaseList;
    }

    @Override
    public boolean addPurchase(PurchaseAddReq dto) {
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(dto, purchase);
        purchase.setPurchaseCode(snowflakeIdWorker.nextId());
        return purchaseDao.save(purchase);
    }

    @Override
    public boolean modifyPurchase(PurchaseEditReq dto) {
        // 1、查询校验分类是否合法
        Purchase purchaseOld = purchaseDao.queryInfoByPurchaseCode(dto.getPurchaseCode());
        if (purchaseOld != null) {
            if (!PurchaseStatusEnum.CREATED.getCode().equals(purchaseOld.getStatus()) &&
                    !PurchaseStatusEnum.ASSIGNED.getCode().equals(purchaseOld.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单 状态已领取、已完成，无法修改");
            }

            Purchase purchaseNew = new Purchase();
            BeanUtils.copyProperties(dto, purchaseNew);
            purchaseNew.setId(purchaseOld.getId());
            // 2、更新仓库对象数据
            purchaseDao.updateById(purchaseNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    @Override
    public Purchase findPurchaseByCode(PurchaseInfoReq dto) {
        return purchaseDao.queryInfoByPurchaseCode(dto.getPurchaseCode());
    }

    /**
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public boolean receivePurchase(PurchaseReceiveReq dto) {
        // 1、获取当前登录用户的Code
        Long currentCode = 2L;

        // 2、确认当前采购单是新建或者已分配状态，并且是分配给自己的
        List<Purchase> purchaseList = purchaseDao.queryListByPurchaseCodes(dto.getPurchaseCodes());
        purchaseList.forEach(item -> {
            if (!PurchaseStatusEnum.CREATED.getCode().equals(item.getStatus()) && !PurchaseStatusEnum.ASSIGNED.getCode().equals(item.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单的状态必须为新建或已分配状态");
            }
            if (!currentCode.equals(item.getAssigneeCode())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单的采购人必须为提交人");
            }
            item.setStatus(PurchaseStatusEnum.RECEIVE.getCode());
        });

        // 3、更新采购单的状态
        purchaseDao.updateBatchById(purchaseList);

        // 4、根据采购单的Code查询出采购项列表，更新采购项的状态
        List<Long> purchaseCodes = purchaseList.stream()
                .map(Purchase::getPurchaseCode)
                .collect(Collectors.toList());
        List<PurchaseItem> purchaseItemList = purchaseItemDao.queryListByPurchaseCodes(purchaseCodes);

        purchaseItemList.forEach(item -> item.setStatus(PurchaseItemStatusEnum.BUYING.getCode()));
        purchaseItemDao.updateBatchById(purchaseItemList);
        return true;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public boolean donePurchase(PurchaseDoneReq dto) {
        // 1、先处理采购项，全部采购项都完成，再去处理采购单
        Long purchaseCode = dto.getPurchaseCode();

        // 2、改变采购项的状态
        boolean flag = true;
        List<PurchaseDoneReq.PurchaseDetailDoneReq> purchaseDetailDoneReqList = dto.getPurchaseDetailDoneReqList();
        // 采购项列表
        List<PurchaseItem> allPurchaseItems = new ArrayList<>();
        // 采购项成功列表
        List<Long> successPurchaseDetailCodes = new ArrayList<>();
        for (PurchaseDoneReq.PurchaseDetailDoneReq item : purchaseDetailDoneReqList) {
            PurchaseItem purchaseItem = new PurchaseItem();
            // 判断采购项是否采购成功
            if (PurchaseItemStatusEnum.HASERROR.getCode().equals(item.getStatus())) {
                flag = false;
                purchaseItem.setStatus(item.getStatus());
            } else if (PurchaseItemStatusEnum.FINISH.getCode().equals(item.getStatus())) {
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
                // 判断仓库中是否有商品，没有商品新增，有商品在原有基础上追加商品数量
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
        purchase.setStatus(flag ? PurchaseStatusEnum.FINISH.getCode() : PurchaseStatusEnum.HASERROR.getCode());
        purchaseDao.updateInfoByPurchaseCode(purchase);

        return true;
    }

}
