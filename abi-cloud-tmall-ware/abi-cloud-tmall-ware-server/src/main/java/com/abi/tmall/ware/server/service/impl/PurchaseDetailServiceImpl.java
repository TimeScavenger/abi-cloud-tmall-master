package com.abi.tmall.ware.server.service.impl;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.exception.BusinessException;
import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.base.foundation.snowflake.SnowflakeIdWorker;
import com.abi.tmall.ware.common.request.purchase.*;
import com.abi.tmall.ware.common.response.purchase.PurchaseDetailPageVo;
import com.abi.tmall.ware.dao.entity.Purchase;
import com.abi.tmall.ware.dao.entity.PurchaseDetail;
import com.abi.tmall.ware.dao.mapper.PurchaseDetailMapper;
import com.abi.tmall.ware.dao.service.PurchaseDao;
import com.abi.tmall.ware.dao.service.PurchaseDetailDao;
import com.abi.tmall.ware.server.client.ProductFeignClient;
import com.abi.tmall.ware.server.client.request.SkuListByNameReq;
import com.abi.tmall.ware.server.client.response.SkuListResp;
import com.abi.tmall.ware.server.enums.PurchaseDetailStatusEnum;
import com.abi.tmall.ware.server.enums.PurchaseStatusEnum;
import com.abi.tmall.ware.server.service.PurchaseDetailService;
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
 * @ClassName: PurchaseDetailServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 采购项
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements PurchaseDetailService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private PurchaseDetailDao purchaseDetailDao;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public PageResponse<PurchaseDetailPageVo> queryPurchaseDetailPageByCondition(PurchaseDetailPageDto dto) {
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
            ApiResponse<List<SkuListResp>> apiResponse = productFeignClient.querySkuListByName(skuListByNameReq);
            if (apiResponse != null && CollectionUtils.isNotEmpty(apiResponse.getData())) {
                skuCodeList = apiResponse.getData().stream().map(SkuListResp::getSkuCode).collect(Collectors.toList());
            }
        }
        Page<PurchaseDetail> page = purchaseDetailDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getWareCode(), dto.getStatus(), skuCodeList);

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
    public boolean addPurchaseDetail(PurchaseDetailAddDto dto) {
        PurchaseDetail purchaseDetail = new PurchaseDetail();
        BeanUtils.copyProperties(dto, purchaseDetail);
        purchaseDetail.setPurchaseDetailCode(snowflakeIdWorker.nextId());
        return purchaseDetailDao.save(purchaseDetail);
    }

    @Override
    public boolean removePurchaseDetail(PurchaseDetailDelDto dto) {
        // 1、TODO 拓展：检查当前删除的采购项, 是否被别的地方引用，例如采购单和采购项的关联关系
        // 2、逻辑删除
        return purchaseDetailDao.removeBatchByPurchaseDetailCodes(dto.getPurchaseDetailCodes());
    }

    @Override
    public boolean modifyPurchaseDetail(PurchaseDetailEditDto dto) {
        // 1、查询校验分类是否合法
        PurchaseDetail purchaseDetailOld = purchaseDetailDao.queryInfoByPurchaseDetailCode(dto.getPurchaseDetailCode());
        if (purchaseDetailOld != null) {
            if (!PurchaseDetailStatusEnum.CREATED.getCode().equals(purchaseDetailOld.getStatus())) {
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购项的状态不是新建状态，无法更改");
            }
            PurchaseDetail purchaseDetailNew = new PurchaseDetail();
            BeanUtils.copyProperties(dto, purchaseDetailNew);
            purchaseDetailNew.setId(purchaseDetailOld.getId());
            // 2、更新仓库对象数据
            purchaseDetailDao.updateById(purchaseDetailNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    @Override
    public PurchaseDetail findPurchaseDetailByCode(PurchaseDetailInfoDto dto) {
        // 1、查询数据
        PurchaseDetail purchaseDetail = purchaseDetailDao.queryInfoByPurchaseDetailCode(dto.getPurchaseDetailCode());
        // 2、返回数据
        if (purchaseDetail != null) {
            return purchaseDetail;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }

    @Override
    @Transactional
    public boolean mergePurchaseDetail(PurchaseDetailMergeDto dto) {
        // 1、根据采购项Code查询出采购项列表
        List<Long> purchaseDetailCodes = dto.getPurchaseDetailCodes();
        List<PurchaseDetail> purchaseDetails = purchaseDetailDao.queryListByPurchaseDetailCodes(purchaseDetailCodes);

        // 2、校验采购项的状态是否有正在采购、已完成采购、采购失败
        List<Long> purchaseDetailStatusCodes = purchaseDetails.stream()
                .filter(item -> PurchaseStatusEnum.RECEIVE.getCode().equals(item.getStatus()) ||
                        PurchaseStatusEnum.FINISH.getCode().equals(item.getStatus()) ||
                        PurchaseStatusEnum.HASERROR.getCode().equals(item.getStatus()))
                .map(PurchaseDetail::getPurchaseDetailCode)
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(purchaseDetailStatusCodes)) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购项中存在正在采购、已完成采购、采购失败的采购项，无法合并采购单");
        }

        // 3、校验采购项的仓库Code是否一致、校验采购单和采购项的仓库配置是否一致
        List<Long> wareCodes = purchaseDetails.stream()
                .map(PurchaseDetail::getWareCode)
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
            if (!wareCodes.get(0).equals(purchase.getWareCode())){
                throw new BusinessException(ResultCode.SERVER_ERROR.code(), "采购单 和 采购项仓库Code不一致，无法合并成采购单");
            }
        }

        // 5、修改采购明细的信息
        Long finalPurchaseCode = purchaseCode;
        purchaseDetails.forEach(purchaseDetail -> {
            purchaseDetail.setPurchaseCode(finalPurchaseCode);
            purchaseDetail.setStatus(PurchaseDetailStatusEnum.ASSIGNED.getCode());
        });
        purchaseDetailDao.updateBatchById(purchaseDetails);
        return true;
    }
}
