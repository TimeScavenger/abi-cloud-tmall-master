package com.abi.tmall.ware.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.snowflake.SnowflakeIdWorker;
import com.abi.tmall.product.client.SkuFeignClient;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import com.abi.tmall.ware.common.request.purchasedetail.*;
import com.abi.tmall.ware.common.response.purchase.PurchaseDetailPageVo;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.dao.entity.PurchaseItem;
import com.abi.tmall.ware.dao.mapper.PurchaseItemMapper;
import com.abi.tmall.ware.dao.service.PurchaseDao;
import com.abi.tmall.ware.dao.service.PurchaseItemDao;
import com.abi.tmall.ware.server.enums.PurchaseItemStatusEnum;
import com.abi.tmall.ware.server.enums.PurchaseStatusEnum;
import com.abi.tmall.ware.server.service.PurchaseItemService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购项 服务实现类
 *
 * @ClassName: PurchaseDetailServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Service
public class PurchaseItemServiceImpl extends ServiceImpl<PurchaseItemMapper, PurchaseItem> implements PurchaseItemService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    @Autowired
    private SkuFeignClient skuFeignClient;

    @Override
    public PageResponse<PurchaseDetailPageVo> queryPurchaseDetailPageByCondition(PurchaseDetailPageReq dto) {
        // 1、新建用于返回的分页对象
        PageResponse<PurchaseDetailPageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        // 调用商品服务 传入Sku的名字模糊查询出符合条件的SkuCode集合
        List<Long> skuCodeList = new ArrayList<>();
        if (StringUtils.isNotBlank(dto.getSkuName())) {
            SkuListByNameReq skuListByNameReq = new SkuListByNameReq();
            skuListByNameReq.setSkuName(dto.getSkuName());
            ApiResponse<List<SkuListResp>> apiResponse = skuFeignClient.querySkuListByName(skuListByNameReq);
            if (apiResponse != null && CollectionUtils.isNotEmpty(apiResponse.getData())) {
                skuCodeList = apiResponse.getData().stream().map(SkuListResp::getSkuCode).collect(Collectors.toList());
            }
        }
        Page<PurchaseItem> page = purchaseItemDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getWareCode(), dto.getStatus(), skuCodeList);

        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<PurchaseDetailPageVo> purchaseDetailPageVoList = page.getRecords().stream()
                    .map(purchaseDetail -> {
                        PurchaseDetailPageVo purchaseDetailPageVo = new PurchaseDetailPageVo();
                        BeanUtils.copyProperties(purchaseDetail, purchaseDetailPageVo);
                        return purchaseDetailPageVo;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(purchaseDetailPageVoList);
        }

        // 5、返回数据
        return pageResponse;
    }

    @Override
    public boolean addPurchaseDetail(PurchaseDetailAddReq dto) {
        PurchaseItem purchaseItem = new PurchaseItem();
        BeanUtils.copyProperties(dto, purchaseItem);
        purchaseItem.setPurchaseDetailCode(snowflakeIdWorker.nextId());
        return purchaseItemDao.save(purchaseItem);
    }

    @Override
    public boolean removePurchaseDetail(PurchaseDetailDelReq dto) {
        // 1、TODO 拓展：检查当前删除的采购项, 是否被别的地方引用，例如采购单和采购项的关联关系
        // 2、逻辑删除
        return purchaseItemDao.removeBatchByPurchaseDetailCodes(dto.getPurchaseDetailCodes());
    }

    @Override
    public boolean modifyPurchaseDetail(PurchaseDetailEditReq dto) {
        // 1、查询校验分类是否合法
        PurchaseItem purchaseItemOld = purchaseItemDao.queryInfoByPurchaseDetailCode(dto.getPurchaseDetailCode());
        if (purchaseItemOld != null) {
            if (!PurchaseItemStatusEnum.CREATED.getCode().equals(purchaseItemOld.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购项的状态不是新建状态，无法更改");
            }
            PurchaseItem purchaseItemNew = new PurchaseItem();
            BeanUtils.copyProperties(dto, purchaseItemNew);
            purchaseItemNew.setId(purchaseItemOld.getId());
            // 2、更新仓库对象数据
            purchaseItemDao.updateById(purchaseItemNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    @Override
    public PurchaseItem findPurchaseDetailByCode(PurchaseDetailInfoReq dto) {
        // 1、查询数据
        PurchaseItem purchaseItem = purchaseItemDao.queryInfoByPurchaseDetailCode(dto.getPurchaseDetailCode());
        // 2、返回数据
        if (purchaseItem != null) {
            return purchaseItem;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }

    @Override
    @Transactional
    public boolean mergePurchaseDetail(PurchaseDetailMergeReq dto) {
        // 1、根据采购项Code查询出采购项列表
        List<Long> purchaseDetailCodes = dto.getPurchaseDetailCodes();
        List<PurchaseItem> purchaseItems = purchaseItemDao.queryListByPurchaseDetailCodes(purchaseDetailCodes);

        // 2、校验采购项的状态是否有正在采购、已完成采购、采购失败
        List<Long> purchaseDetailStatusCodes = purchaseItems.stream()
                .filter(item -> PurchaseStatusEnum.RECEIVE.getCode().equals(item.getStatus()) ||
                        PurchaseStatusEnum.FINISH.getCode().equals(item.getStatus()) ||
                        PurchaseStatusEnum.HASERROR.getCode().equals(item.getStatus()))
                .map(PurchaseItem::getPurchaseDetailCode)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(purchaseDetailStatusCodes)) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购项中存在正在采购、已完成采购、采购失败的采购项，无法合并采购单");
        }

        // 3、校验采购项的仓库Code是否一致、校验采购单和采购项的仓库配置是否一致
        List<Long> wareCodes = purchaseItems.stream()
                .map(PurchaseItem::getWareCode)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(wareCodes) && wareCodes.size() > 1) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购项仓库Code不一致，无法合并成采购单");
        }

        // 4、获取采购单Code，判断传入的采购单Code是否为空
        Long purchaseCode = dto.getPurchaseCode();
        if (purchaseCode == null) {
            // 如果没有指定采购单，新建一个采购单信息
            Purchase purchase = new Purchase();
            purchase.setPurchaseName(LocalDate.now().getYear() + "年采购单");
            purchase.setPurchaseCode(snowflakeIdWorker.nextId());
            purchase.setStatus(PurchaseStatusEnum.CREATED.getCode());
            purchase.setWareCode(wareCodes.get(0));
            purchase.setPriority(5);
            purchaseDao.save(purchase);
            purchaseCode = purchase.getPurchaseCode();
        } else {
            Purchase purchase = purchaseDao.queryInfoByPurchaseCode(dto.getPurchaseCode());
            if (!wareCodes.get(0).equals(purchase.getWareCode())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单 和 采购项仓库Code不一致，无法合并成采购单");
            }
        }

        // 5、修改采购明细的信息
        Long finalPurchaseCode = purchaseCode;
        purchaseItems.forEach(purchaseDetail -> {
            purchaseDetail.setPurchaseCode(finalPurchaseCode);
            purchaseDetail.setStatus(PurchaseItemStatusEnum.ASSIGNED.getCode());
        });
        purchaseItemDao.updateBatchById(purchaseItems);
        return true;
    }
}
