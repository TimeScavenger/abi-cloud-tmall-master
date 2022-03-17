package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.sku.SkuListByCodeDto;
import com.abi.tmall.product.common.request.sku.SkuListByNameDto;
import com.abi.tmall.product.common.request.sku.SkuPageDto;
import com.abi.tmall.product.common.response.sku.SkuItemVo;
import com.abi.tmall.product.common.response.sku.SkuListVo;
import com.abi.tmall.product.common.response.sku.SkuPageVo;
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
 * @ClassName: SkuServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Sku信息
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
    public PageResponse<SkuPageVo> querySkuPageByCondition(SkuPageDto dto) {
        // 1、新建分页返回对象
        PageResponse<SkuPageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        Page<Sku> page = skuDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getCategoryCode(), dto.getBrandCode(), dto.getSkuName(), dto.getPriceMax(), dto.getPriceMin());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<SkuPageVo> pageVoList = page.getRecords().stream()
                    .map(sku -> {
                        SkuPageVo skuPageVo = new SkuPageVo();
                        BeanUtils.copyProperties(sku, skuPageVo);
                        return skuPageVo;
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
    public List<SkuListVo> querySkuListByCodes(SkuListByCodeDto dto) {
        // 根据SkuCode集合查询出对应的sku信息
        List<Sku> skus = skuDao.queryListBySkuCodes(dto.getSkuCodes());
        // 数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(skus)) {
            List<SkuListVo> skuListVos = skus.stream()
                    .map(sku -> {
                        SkuListVo skuListVo = new SkuListVo();
                        BeanUtils.copyProperties(sku, skuListVo);
                        return skuListVo;
                    })
                    .collect(Collectors.toList());
            return skuListVos;
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public List<SkuListVo> querySkuListByName(SkuListByNameDto dto) {
        // 根据SkuCode集合查询出对应的sku信息
        List<Sku> skus = skuDao.queryListBySkuName(dto.getSkuName());
        // 数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(skus)) {
            List<SkuListVo> skuListVos = skus.stream()
                    .map(sku -> {
                        SkuListVo skuListVo = new SkuListVo();
                        BeanUtils.copyProperties(sku, skuListVo);
                        return skuListVo;
                    })
                    .collect(Collectors.toList());
            return skuListVos;
        } else {
            return Lists.newArrayList();
        }
    }

    @Override
    public SkuItemVo querySkuItemBySkuCode(Long skuCode) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();
        // 1、Sku的基本信息 - pms_sku
        CompletableFuture<Sku> baseFuture = CompletableFuture.supplyAsync(() -> {
            Sku sku = skuDao.queryItemBySkuCode(skuCode);
            SkuItemVo.SkuBase skuBase = new SkuItemVo.SkuBase();
            BeanUtils.copyProperties(sku, skuBase);
            skuItemVo.setSkuBase(skuBase);
            return sku;
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 组装基本信息 ：【{}】", skuItemVo);

        // 2、Spu商品介绍图片 - pms_spu_img_desc
        CompletableFuture<Void> descFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuImgDesc> spuImgDescList = spuImgDescDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemVo.SpuImgDescVo> spuImgDescVoList = new ArrayList<>();
            for (SpuImgDesc spuImgDesc : spuImgDescList) {
                SkuItemVo.SpuImgDescVo spuImgDescVo = new SkuItemVo.SpuImgDescVo();
                BeanUtils.copyProperties(spuImgDesc, spuImgDescVo);
                spuImgDescVoList.add(spuImgDescVo);
            }
            skuItemVo.setSpuImgDescVoList(spuImgDescVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo Spu商品介绍图片信息 ：【{}】", skuItemVo);

        // 3、Spu商品详情图片 - pms_spu_img_detail
        CompletableFuture<Void> detailFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuImgDetail> spuImgDetailList = spuImgDetailDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemVo.SpuImgDetailVo> spuImgDetailVoList = new ArrayList<>();
            for (SpuImgDetail spuImgDetail : spuImgDetailList) {
                SkuItemVo.SpuImgDetailVo spuImgDetailVo = new SkuItemVo.SpuImgDetailVo();
                BeanUtils.copyProperties(spuImgDetail, spuImgDetailVo);
                spuImgDetailVoList.add(spuImgDetailVo);
            }
            skuItemVo.setSpuImgDetailVoList(spuImgDetailVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo Spu商品详情图片信息 ：【{}】", skuItemVo);

        // 4、获取Spu的销售属性组合 - pms_sku_sale_attribute_value
        CompletableFuture<Void> saleAttrFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SkuSaleAttributeValue> skuSaleAttributeValues = skuSaleAttributeValueDao.queryListBySkuCode(result.getSkuCode());
            List<SkuItemVo.SkuSaleAttributeValueVo> skuSaleAttributeValueVoList = new ArrayList<>();
            for (SkuSaleAttributeValue skuSaleAttributeValue : skuSaleAttributeValues) {
                SkuItemVo.SkuSaleAttributeValueVo skuSaleAttributeValueVo = new SkuItemVo.SkuSaleAttributeValueVo();
                BeanUtils.copyProperties(skuSaleAttributeValue, skuSaleAttributeValueVo);
                skuSaleAttributeValueVoList.add(skuSaleAttributeValueVo);
            }
            skuItemVo.setSkuSaleAttributeValueVoList(skuSaleAttributeValueVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 获取Spu的销售属性组合信息 ：【{}】", skuItemVo);

        // 5、获取Spu的分组规格参数信息组合 - pms_spu_base_attribute_value
        CompletableFuture<Void> baseAttrFuture = baseFuture.thenAcceptAsync((result) -> {
            List<SpuBaseAttributeValue> spuBaseAttributeValues = spuBaseAttributeValueDao.queryListBySpuCode(result.getSpuCode());
            List<SkuItemVo.SpuBaseAttributeValueVo> spuBaseAttributeValueVoList = new ArrayList<>();
            for (SpuBaseAttributeValue spuBaseAttributeValue : spuBaseAttributeValues) {
                SkuItemVo.SpuBaseAttributeValueVo spuBaseAttributeValueVo = new SkuItemVo.SpuBaseAttributeValueVo();
                BeanUtils.copyProperties(spuBaseAttributeValue, spuBaseAttributeValueVo);
                spuBaseAttributeValueVoList.add(spuBaseAttributeValueVo);
            }
            skuItemVo.setSpuBaseAttributeValueVoList(spuBaseAttributeValueVoList);
        }, executor);
        log.info(LOG_PRE + "SkuItemVo 获取Spu的分组规格参数信息组合信息 ：【{}】", skuItemVo);

        // 等到所有任务都完成
        CompletableFuture.allOf(descFuture, detailFuture, saleAttrFuture, baseAttrFuture).get();

        log.info(LOG_PRE + "SkuItemVo 综合组合信息 ：【{}】", skuItemVo);
        return skuItemVo;
    }

}

