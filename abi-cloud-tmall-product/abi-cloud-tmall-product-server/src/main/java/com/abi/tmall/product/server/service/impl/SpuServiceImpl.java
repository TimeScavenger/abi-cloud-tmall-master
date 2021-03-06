package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.util.GenerateCodeUtils;
import com.abi.tmall.product.common.request.sku.SkuAddDto;
import com.abi.tmall.product.common.request.spu.SpuAddDto;
import com.abi.tmall.product.common.request.spu.SpuPageDto;
import com.abi.tmall.product.common.request.spu.SpuUpDto;
import com.abi.tmall.product.common.response.spu.SpuPageVo;
import com.abi.tmall.product.dao.entity.*;
import com.abi.tmall.product.dao.mapper.SpuMapper;
import com.abi.tmall.product.dao.service.*;
import com.abi.tmall.product.server.client.CouponFeignClient;
import com.abi.tmall.product.server.client.SearchFeignClient;
import com.abi.tmall.product.server.client.WareFeignClient;
import com.abi.tmall.product.server.client.request.coupon.SkuFullReductionAddReq;
import com.abi.tmall.product.server.client.request.coupon.SkuLadderAddReq;
import com.abi.tmall.product.server.client.request.coupon.SpuBoundsAddReq;
import com.abi.tmall.product.server.client.request.search.SkuSearchAddReq;
import com.abi.tmall.product.server.client.request.ware.WareStockDto;
import com.abi.tmall.product.server.client.response.ware.WareSkuRelationStockVo;
import com.abi.tmall.product.server.enums.AttributeSearchTypeEnum;
import com.abi.tmall.product.server.enums.ProductInitCodeEnum;
import com.abi.tmall.product.server.enums.SpuPublishTypeEnum;
import com.abi.tmall.product.server.service.SpuImgDetailService;
import com.abi.tmall.product.server.service.SpuService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.abi.infrastructure.core.constant.CommonConstants.LOG_PRE;

/**
 * @ClassName: SpuServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/06/10
 * @Description: Spu??????
 */
@Slf4j
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SpuDao spuDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private SpuImgDescDao spuImgDescDao;

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private SpuImgDetailService spuImgDetailService;

    @Autowired
    private SpuBaseAttributeValueDao spuBaseAttributeValueDao;

    @Autowired
    private CouponFeignClient couponFeignClient;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private SkuImgDao skuImgDao;

    @Autowired
    private SkuSaleAttributeValueDao skuSaleAttributeValueDao;

    @Autowired
    private WareFeignClient wareFeignClient;

    @Autowired
    private SearchFeignClient searchFeignService;

    @Override
    public PageResponse<SpuPageVo> querySpuPageByCondition(SpuPageDto dto) {
        // 1???????????????????????????
        PageResponse<SpuPageVo> pageResponse = new PageResponse<>();
        // 2??????????????????????????????????????????????????????????????????
        dto.checkParam();
        // 3???????????????
        Page<Spu> page = spuDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getCategoryCode(), dto.getBrandCode(), dto.getPublishStatus(), dto.getSpuName());
        // 4?????????????????????
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 4.1????????????SPU?????????????????????
            List<Long> categoryCodes = page.getRecords().stream()
                    .map(Spu::getCategoryCode)
                    .collect(Collectors.toList());
            List<Category> categories = categoryDao.queryListByCategoryCodes(categoryCodes);
            Map<Long, String> categoryCodeAndCategoryNameMap = categories.stream()
                    .collect(Collectors.toMap(Category::getCategoryCode, Category::getCategoryName));

            // 4.2????????????SPU?????????????????????
            List<Long> brandCodes = page.getRecords().stream()
                    .map(Spu::getBrandCode)
                    .collect(Collectors.toList());
            List<Brand> brands = brandDao.queryListByBrandCodes(brandCodes);
            Map<Long, String> brandCodeAndBrandNameMap = brands.stream()
                    .collect(Collectors.toMap(Brand::getBrandCode, Brand::getBrandName));

            // 4.3?????????????????????
            List<SpuPageVo> pageVoList = page.getRecords().stream()
                    .map(spu -> {
                        SpuPageVo spuPageVo = new SpuPageVo();
                        BeanUtils.copyProperties(spu, spuPageVo);
                        spuPageVo.setCategoryName(categoryCodeAndCategoryNameMap.get(spu.getCategoryCode()));
                        spuPageVo.setBrandName(brandCodeAndBrandNameMap.get(spu.getBrandCode()));
                        return spuPageVo;
                    })
                    .collect(Collectors.toList());

            // 4.4?????????????????????
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 5???????????????
        return pageResponse;

    }

    @Override
    @Transactional
    public boolean saveSpu(SpuAddDto dto) {
        // 1?????????SPU???????????? -> pms_spu_info
        Spu spu = new Spu();
        BeanUtils.copyProperties(dto, spu);
        spu.setSpuCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_SPU_INIT_CODE.getDesc()));
        spuDao.save(spu);
        // ??????spuCode??????????????????
        Long spuCode = spu.getSpuCode();

        // 2?????????Spu??????????????? -> pms_spu_img_desc
        List<String> spuDescImgs = dto.getSpuDescImgs();
        if (CollectionUtils.isNotEmpty(spuDescImgs)) {
            SpuImgDesc spuImgDesc = new SpuImgDesc();
            spuImgDesc.setSpuCode(spuCode);
            spuImgDesc.setImgUrl(String.join(",", spuDescImgs));
            spuImgDescDao.save(spuImgDesc);
        }

        // 3?????????Spu??????????????? -> pms_spu_img_detail
        List<String> spuDetailImgs = dto.getSpuDetailImgs();
        if (CollectionUtils.isNotEmpty(spuDetailImgs)) {
            spuImgDetailService.saveSpuDetailImgs(spuCode, spuDetailImgs);
        }

        // 4?????????Spu??????????????? -> pms_spu_base_attribute_value
        // ???????????????????????????????????????????????????Map??????
        List<Attribute> attributes = attributeDao.list();
        Map<Long, Attribute> idAttributeMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getAttributeCode, attribute -> attribute));
        // ????????????????????????????????????????????????
        List<SpuAddDto.SpuBaseAttribute> spuBaseAttributes = dto.getSpuBaseAttributes();
        if (CollectionUtils.isNotEmpty(spuBaseAttributes)) {
            List<SpuBaseAttributeValue> spuBaseAttributeValues = spuBaseAttributes.stream()
                    .map(item -> {
                        SpuBaseAttributeValue spuBaseAttributeValue = new SpuBaseAttributeValue();
                        spuBaseAttributeValue.setSpuCode(spuCode);
                        Attribute attribute = idAttributeMap.get(item.getAttributeCode());
                        spuBaseAttributeValue.setAttributeCode(attribute.getAttributeCode());
                        spuBaseAttributeValue.setAttributeName(attribute.getAttributeName());
                        spuBaseAttributeValue.setAttributeValue(item.getValueList());
                        spuBaseAttributeValue.setQuickShow(item.getQuickShow());
                        return spuBaseAttributeValue;
                    })
                    .collect(Collectors.toList());
            spuBaseAttributeValueDao.saveBatch(spuBaseAttributeValues);
        }

        // 5?????????Spu??????????????? -> sms_spu_bounds
        SpuAddDto.Bounds bounds = dto.getBounds();
        SpuBoundsAddReq spuBoundsAddReq = new SpuBoundsAddReq();
        BeanUtils.copyProperties(bounds, spuBoundsAddReq);
        spuBoundsAddReq.setSpuCode(spuCode);
        ApiResponse<Boolean> apiResult = couponFeignClient.saveSpuBounds(spuBoundsAddReq);
        if (ResultCode.SUCCESS.code() != apiResult.getCode()) {
            log.error(LOG_PRE + "????????????spu??????????????????");
            throw new BusinessException("????????????spu??????????????????");
        }

        // 6???????????????Spu???????????????Sku??????
        List<SkuAddDto> skuAddDtos = dto.getSkuAddDtos();
        if (CollectionUtils.isEmpty(skuAddDtos)) {
            log.error(LOG_PRE + "??????SKU????????????");
            throw new BusinessException("??????SKU????????????");
        }

        // ???????????????????????????
        List<Sku> skus = new ArrayList<>();
        List<SkuImg> skuImgs = new ArrayList<>();
        List<SkuSaleAttributeValue> skuSaleAttributeValues = new ArrayList<>();
        List<SkuFullReductionAddReq> skuFullReductionAddReqs = new ArrayList<>();
        List<SkuLadderAddReq> skuLadderAddReqs = new ArrayList<>();

        for (SkuAddDto skuAddDto : skuAddDtos) {
            // 6.1???Sku??????????????? -> pms_sku_info
            String defaultImg = "";
            for (SkuAddDto.Images image : skuAddDto.getSkuDetailImgs()) {
                if (image.getDefaultImg() == 1) {
                    defaultImg = image.getImgUrl();
                }
            }
            Sku sku = new Sku();
            BeanUtils.copyProperties(skuAddDto, sku);
            sku.setSpuCode(spu.getSpuCode());
            sku.setCategoryCode(spu.getCategoryCode());
            sku.setBrandCode(spu.getBrandCode());
            sku.setSkuSaleCount(0L);
            sku.setSkuDefaultImg(defaultImg);
            sku.setSkuCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_SKU_INIT_CODE.getDesc()));
            skus.add(sku);

            // 6.2???Sku??????????????? -> pms_sku_img
            Long skuCode = sku.getSkuCode();
            skuImgs = skuAddDto.getSkuDetailImgs().stream()
                    .map(img -> {
                        SkuImg skuImg = new SkuImg();
                        skuImg.setSkuCode(skuCode);
                        skuImg.setImgUrl(img.getImgUrl());
                        skuImg.setImgType(img.getDefaultImg());
                        return skuImg;
                    })
                    .filter(entity -> StringUtils.isNotBlank(entity.getImgUrl()))
                    .collect(Collectors.toList());

            // 6.3???Sku????????? -> pms_sku_sale_attribute_value
            List<SkuAddDto.Attribute> saleAttributeList = skuAddDto.getAttr();
            List<SkuSaleAttributeValue> saleAttributeValues = saleAttributeList.stream()
                    .map(attribute -> {
                        SkuSaleAttributeValue skuSaleAttributeValue = new SkuSaleAttributeValue();
                        skuSaleAttributeValue.setSkuCode(skuCode);
                        skuSaleAttributeValue.setAttributeCode(attribute.getAttributeCode());
                        skuSaleAttributeValue.setAttributeName(attribute.getAttributeName());
                        skuSaleAttributeValue.setAttributeValue(attribute.getAttrValue());
                        return skuSaleAttributeValue;
                    })
                    .collect(Collectors.toList());
            skuSaleAttributeValues.addAll(saleAttributeValues);

            // 6.4???Sku??????????????? -> sms_sku_full_reduction
            SkuFullReductionAddReq skuFullReductionAddReq = new SkuFullReductionAddReq();
            skuFullReductionAddReq.setSkuCode(skuCode);
            skuFullReductionAddReq.setFullPrice(skuAddDto.getFullPrice());
            skuFullReductionAddReq.setReducePrice(skuAddDto.getReducePrice());
            skuFullReductionAddReq.setAddOther(skuAddDto.getPriceStatus());
            skuFullReductionAddReqs.add(skuFullReductionAddReq);

            // 6.5???Sku??????????????? -> sms_sku_ladder
            SkuLadderAddReq skuLadderAddReq = new SkuLadderAddReq();
            skuLadderAddReq.setSkuCode(skuCode);
            skuLadderAddReq.setFullCount(skuAddDto.getFullCount());
            skuLadderAddReq.setDiscount(skuAddDto.getDiscount());
            skuLadderAddReq.setAddOther(skuAddDto.getCountStatus());
            skuLadderAddReqs.add(skuLadderAddReq);

            // 6.6???Sku????????????????????? -> sms_member_price
//        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();
//        List<MemberPriceEntity> collect = memberPrice.stream().map(item -> {
//            MemberPriceEntity priceEntity = new MemberPriceEntity();
//            priceEntity.setSkuCode(reductionTo.getSkuCode());
//            priceEntity.setMemberLevelCode(skuAddDto.getCode());
//            priceEntity.setMemberLevelName(skuAddDto.getName());
//            priceEntity.setMemberPrice(skuAddDto.getPrice());
//            priceEntity.setAddOther(1);
//            return priceEntity;
//        }).filter(item->{
//            return skuAddDto.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
//        }).collect(Collectors.toList());
//        couponFeignClient.saveBatch(collect);

        }

        if (CollectionUtils.isNotEmpty(skus)) {
            skuDao.saveBatch(skus);
        }
        if (CollectionUtils.isNotEmpty(skuImgs)) {
            skuImgDao.saveBatch(skuImgs);
        }
        if (CollectionUtils.isNotEmpty(skuSaleAttributeValues)) {
            skuSaleAttributeValueDao.saveBatch(skuSaleAttributeValues);
        }
        if (CollectionUtils.isNotEmpty(skuFullReductionAddReqs)) {
            ApiResponse<Boolean> apiResult1 = couponFeignClient.saveSkuFullReduction(skuFullReductionAddReqs);
            if (ResultCode.SUCCESS.code() != apiResult1.getCode()) {
                log.error(LOG_PRE + "????????????sku??????????????????");
                throw new BusinessException("????????????sku??????????????????");
            }
        }
        if (CollectionUtils.isNotEmpty(skuLadderAddReqs)) {
            ApiResponse<Boolean> apiResult2 = couponFeignClient.saveSkuLadder(skuLadderAddReqs);
            if (ResultCode.SUCCESS.code() != apiResult2.getCode()) {
                log.error(LOG_PRE + "????????????sku??????????????????");
                throw new BusinessException("????????????sku??????????????????");
            }
        }
        return true;
    }

    // @GlobalTransactional(rollbackFor = Exception.class)
    // @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upSpu(SpuUpDto dto) {
        // 1????????????????????????SpuCode
        Long spuCode = dto.getSpuCode();
        Spu spu = spuDao.queryInfoBySpuCode(spuCode);
        if (Objects.isNull(spu)) {
            throw new BusinessException("??????SpuCode???????????????Spu??????");
        } else if (SpuPublishTypeEnum.SPU_UP.getCode().equals(spu.getPublishStatus())) {
            throw new BusinessException("??????Spu?????????????????????????????????");
        }

        // 2??????????????????spuCode???????????????sku??????
        List<Sku> skus = skuDao.queryListBySpuCode(spuCode);
        List<Long> skuCodes = skus.stream().map(Sku::getSkuCode).collect(Collectors.toList());

        // 3????????????????????????
        Map<Long, String> categoryCodeAndNameMap = categoryDao.list().stream()
                .collect(Collectors.toMap(Category::getCategoryCode, Category::getCategoryName));

        // 4????????????????????????
        Map<Long, Brand> brandCodeAndEntityMap = brandDao.list().stream()
                .collect(Collectors.toMap(Brand::getBrandCode, Function.identity()));

        // 5.1????????????SpuCode???????????????????????????????????????????????????
        List<SpuBaseAttributeValue> spuBaseAttributeValues = spuBaseAttributeValueDao.queryListBySpuCode(spuCode);
        List<Long> attributeCodes = spuBaseAttributeValues.stream()
                .map(SpuBaseAttributeValue::getAttributeCode)
                .collect(Collectors.toList());

        // 5.2?????????SpuCode???????????????????????????Code?????????????????????????????????????????????
        List<Attribute> attributes = attributeDao.queryListByAttributeCodes(attributeCodes, AttributeSearchTypeEnum.RETRIEVABLE.getCode());
        // ????????????????????????Code???????????????
        List<Long> searchAttributeCodes = attributes.stream().map(Attribute::getAttributeCode).distinct().collect(Collectors.toList());
        // ????????????????????????????????????Code?????????????????????
        List<SkuSearchAddReq.Attribute> attrsList = spuBaseAttributeValues.stream()
                .filter(item -> searchAttributeCodes.contains(item.getAttributeCode()))
                .map(item -> {
                    SkuSearchAddReq.Attribute attribute = new SkuSearchAddReq.Attribute();
                    attribute.setAttrCode(item.getAttributeCode());
                    attribute.setAttrName(item.getAttributeName());
                    attribute.setAttrValue(item.getAttributeValue());
                    return attribute;
                }).collect(Collectors.toList());

        // 6?????????????????????????????????????????????????????????
        Map<Long, Boolean> stockMap = new HashMap<>();
        try {
            WareStockDto wareStockDto = new WareStockDto();
            wareStockDto.setSkuCodes(skuCodes);
            ApiResponse<List<WareSkuRelationStockVo>> apiResponse = wareFeignClient.querySkuHasStock(wareStockDto);
            stockMap = apiResponse.getData().stream().collect(Collectors.toMap(WareSkuRelationStockVo::getSkuCode, WareSkuRelationStockVo::getHasStock));
        } catch (Exception e) {
            log.error("?????????????????????????????????{}", e);
        }

        // 7???????????????sku?????????
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuSearchAddReq> collect = skus.stream()
                .map(sku -> {
                    // ?????????????????????
                    SkuSearchAddReq esModel = new SkuSearchAddReq();
                    BeanUtils.copyProperties(sku, esModel);
                    // ??????Sku??????
                    esModel.setSkuPrice(sku.getSkuPrice());
                    // ??????Sku??????
                    esModel.setSkuDefaultImg(sku.getSkuDefaultImg());
                    // ???????????????0
                    esModel.setHotScore(0L);
                    // ????????????
                    esModel.setCategoryCode(sku.getCategoryCode());
                    if (sku.getCategoryCode() != null && MapUtils.isNotEmpty(categoryCodeAndNameMap)) {
                        esModel.setCategoryName(categoryCodeAndNameMap.get(sku.getCategoryCode()));
                    }
                    // ????????????
                    esModel.setBrandCode(sku.getBrandCode());
                    if (sku.getBrandCode() != null && MapUtils.isNotEmpty(brandCodeAndEntityMap)) {
                        Brand brand = brandCodeAndEntityMap.get(sku.getBrandCode());
                        if (brand != null) {
                            esModel.setBrandName(brand.getBrandName());
                            esModel.setLogo(brand.getBrandName());
                        }
                    }
                    // ??????????????????
                    esModel.setAttributes(attrsList);
                    // ??????Sku????????????
                    if (MapUtils.isEmpty(finalStockMap)) {
                        esModel.setHasStock(true);
                    } else {
                        esModel.setHasStock(finalStockMap.get(sku.getSkuCode()));
                    }
                    return esModel;
                })
                .collect(Collectors.toList());
        // 8??????????????????es????????????
        ApiResponse<Boolean> apiResponse = searchFeignService.saveProductToEs(collect);
        if (ResultCode.SUCCESS.code() == apiResponse.getCode()) {
            // ??????????????????
            // 8.1???????????????spu?????????
            spuDao.updateSpuPublishStatus(spuCode, SpuPublishTypeEnum.SPU_UP.getCode());
        } else {
            // ??????????????????
            // TODO 8.2?????????????????????????????????:????????????
            /**
             * 1???????????????????????????????????????json???
             *    RequestTemplate template = buildTemplateFromArgs.create(avgv);
             * 2??????????????????????????????????????????????????????????????????
             *    executeAndDecode(template);
             * 3?????????????????????????????????
             *    while(true) {
             *      try {
             *          executeAndDecode(template);
             *      } catch () {
             *          try{
             *              retryer.contnueOrPropagate(e);
             *          } catch {
             *              throw ex;
             *          }
             *      }
             *    }
             */
        }
        return true;
    }

}
