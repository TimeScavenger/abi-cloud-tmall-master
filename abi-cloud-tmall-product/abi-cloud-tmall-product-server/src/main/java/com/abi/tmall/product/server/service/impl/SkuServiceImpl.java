package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeReq;
import com.abi.tmall.product.common.request.sku.SkuListByNameReq;
import com.abi.tmall.product.common.request.sku.SkuPageReq;
import com.abi.tmall.product.common.response.sku.SkuItemResp;
import com.abi.tmall.product.common.response.sku.SkuListResp;
import com.abi.tmall.product.common.response.sku.SkuPageResp;
import com.abi.tmall.product.dao.entity.*;
import com.abi.tmall.product.dao.mapper.SkuMapper;
import com.abi.tmall.product.dao.service.*;
import com.abi.tmall.product.server.service.SkuService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import static com.abi.infrastructure.core.constant.CommonConstants.LOG_PRE;

/**
 * Sku 服务实现类
 *
 * @ClassName: SkuServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description:
 */
@Slf4j
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private SpuImgDescDao spuImgDescDao;

    @Autowired
    private SpuImgDetailDao spuImgDetailDao;

    @Autowired
    private SkuSaleAttributeValueDao skuSaleAttributeValueDao;

    @Autowired
    private SpuBaseAttributeValueDao spuBaseAttributeValueDao;

    @Override
    public PageResponse<SkuPageResp> querySkuPageByCondition(SkuPageReq dto) {
        // 1、新建分页返回对象
        PageResponse<SkuPageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        Page<Sku> page = skuDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getCategoryCode(), dto.getBrandCode(), dto.getSkuName(), dto.getPriceMax(), dto.getPriceMin());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<SkuPageResp> pageVoList = page.getRecords().stream()
                    .map(sku -> {
                        SkuPageResp skuPageResp = new SkuPageResp();
                        BeanUtils.copyProperties(sku, skuPageResp);
                        return skuPageResp;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        return pageResponse;
    }

    @Override
    public List<SkuListResp> querySkuListByCodes(SkuListByCodeReq dto) {
        // 根据SkuCode集合查询出对应的sku信息
        List<Sku> skus = skuDao.queryListBySkuCodes(dto.getSkuCodes());
        // 数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(skus)) {
            List<SkuListResp> skuListResps = skus.stream()
                    .map(sku -> {
                        SkuListResp skuListResp = new SkuListResp();
                        BeanUtils.copyProperties(sku, skuListResp);
                        return skuListResp;
                    })
                    .collect(Collectors.toList());
            return skuListResps;
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public List<SkuListResp> querySkuListByName(SkuListByNameReq dto) {
        // 根据SkuCode集合查询出对应的sku信息
        List<Sku> skus = skuDao.queryListBySkuName(dto.getSkuName());
        // 数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(skus)) {
            List<SkuListResp> skuListResps = skus.stream()
                    .map(sku -> {
                        SkuListResp skuListResp = new SkuListResp();
                        BeanUtils.copyProperties(sku, skuListResp);
                        return skuListResp;
                    })
                    .collect(Collectors.toList());
            return skuListResps;
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public SkuItemResp querySkuItemBySkuCode(Long skuCode) throws ExecutionException, InterruptedException {
        SkuItemResp skuItemResp = new SkuItemResp();
        // 1、Sku的基本信息 - pms_sku
        CompletableFuture<Sku> baseFuture = CompletableFuture.supplyAsync(() -> {
            Sku sku = skuDao.queryItemBySkuCode(skuCode);
            SkuItemResp.SkuBase skuBase = new SkuItemResp.SkuBase();
            BeanUtils.copyProperties(sku, skuBase);
            skuItemResp.setSkuBase(skuBase);
            return sku;
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 组装基本信息 ：【{}】", skuItemResp);

        // 2、Spu商品介绍图片 - pms_spu_img_desc
        CompletableFuture<Void> descFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuImgDesc> spuImgDescList = spuImgDescDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemResp.SpuImgDescVo> spuImgDescVoList = new ArrayList<>();
            for (SpuImgDesc spuImgDesc : spuImgDescList) {
                SkuItemResp.SpuImgDescVo spuImgDescVo = new SkuItemResp.SpuImgDescVo();
                BeanUtils.copyProperties(spuImgDesc, spuImgDescVo);
                spuImgDescVoList.add(spuImgDescVo);
            }
            skuItemResp.setSpuImgDescVoList(spuImgDescVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo Spu商品介绍图片信息 ：【{}】", skuItemResp);

        // 3、Spu商品详情图片 - pms_spu_img_detail
        CompletableFuture<Void> detailFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuImgDetail> spuImgDetailList = spuImgDetailDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemResp.SpuImgDetailVo> spuImgDetailVoList = new ArrayList<>();
            for (SpuImgDetail spuImgDetail : spuImgDetailList) {
                SkuItemResp.SpuImgDetailVo spuImgDetailVo = new SkuItemResp.SpuImgDetailVo();
                BeanUtils.copyProperties(spuImgDetail, spuImgDetailVo);
                spuImgDetailVoList.add(spuImgDetailVo);
            }
            skuItemResp.setSpuImgDetailVoList(spuImgDetailVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo Spu商品详情图片信息 ：【{}】", skuItemResp);

        // 4、获取Spu的销售属性组合 - pms_sku_sale_attribute_value
        CompletableFuture<Void> saleAttrFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SkuSaleAttributeValue> skuSaleAttributeValues = skuSaleAttributeValueDao.queryListBySkuCode(result.getSkuCode());
            List<SkuItemResp.SkuSaleAttributeValueVo> skuSaleAttributeValueVoList = new ArrayList<>();
            for (SkuSaleAttributeValue skuSaleAttributeValue : skuSaleAttributeValues) {
                SkuItemResp.SkuSaleAttributeValueVo skuSaleAttributeValueVo = new SkuItemResp.SkuSaleAttributeValueVo();
                BeanUtils.copyProperties(skuSaleAttributeValue, skuSaleAttributeValueVo);
                skuSaleAttributeValueVoList.add(skuSaleAttributeValueVo);
            }
            skuItemResp.setSkuSaleAttributeValueVoList(skuSaleAttributeValueVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 获取Spu的销售属性组合信息 ：【{}】", skuItemResp);

        // 5、获取Spu的分组规格参数信息组合 - pms_spu_base_attribute_value
        CompletableFuture<Void> baseAttrFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuBaseAttributeValue> spuBaseAttributeValues = spuBaseAttributeValueDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemResp.SpuBaseAttributeValueVo> spuBaseAttributeValueVoList = new ArrayList<>();
            for (SpuBaseAttributeValue spuBaseAttributeValue : spuBaseAttributeValues) {
                SkuItemResp.SpuBaseAttributeValueVo spuBaseAttributeValueVo = new SkuItemResp.SpuBaseAttributeValueVo();
                BeanUtils.copyProperties(spuBaseAttributeValue, spuBaseAttributeValueVo);
                spuBaseAttributeValueVoList.add(spuBaseAttributeValueVo);
            }
            skuItemResp.setSpuBaseAttributeValueVoList(spuBaseAttributeValueVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 获取Spu的分组规格参数信息组合信息 ：【{}】", skuItemResp);

        // 等到所有任务都完成
        CompletableFuture.allOf(descFuture, detailFuture, saleAttrFuture, baseAttrFuture).get();

        log.info(LOG_PRE + "SkuItemVo 综合组合信息 ：【{}】", skuItemResp);
        return skuItemResp;
    }

}

