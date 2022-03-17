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
 * @Description: Spu信息
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
        // 1、新建分页返回对象
        PageResponse<SpuPageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        Page<Spu> page = spuDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getCategoryCode(), dto.getBrandCode(), dto.getPublishStatus(), dto.getSpuName());
        // 4、数据进行转换
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 4.1、查询出SPU关联的分类信息
            List<Long> categoryCodes = page.getRecords().stream()
                    .map(Spu::getCategoryCode)
                    .collect(Collectors.toList());
            List<Category> categories = categoryDao.queryListByCategoryCodes(categoryCodes);
            Map<Long, String> categoryCodeAndCategoryNameMap = categories.stream()
                    .collect(Collectors.toMap(Category::getCategoryCode, Category::getCategoryName));

            // 4.2、查询出SPU关联的品牌信息
            List<Long> brandCodes = page.getRecords().stream()
                    .map(Spu::getBrandCode)
                    .collect(Collectors.toList());
            List<Brand> brands = brandDao.queryListByBrandCodes(brandCodes);
            Map<Long, String> brandCodeAndBrandNameMap = brands.stream()
                    .collect(Collectors.toMap(Brand::getBrandCode, Brand::getBrandName));

            // 4.3、数据进行转换
            List<SpuPageVo> pageVoList = page.getRecords().stream()
                    .map(spu -> {
                        SpuPageVo spuPageVo = new SpuPageVo();
                        BeanUtils.copyProperties(spu, spuPageVo);
                        spuPageVo.setCategoryName(categoryCodeAndCategoryNameMap.get(spu.getCategoryCode()));
                        spuPageVo.setBrandName(brandCodeAndBrandNameMap.get(spu.getBrandCode()));
                        return spuPageVo;
                    })
                    .collect(Collectors.toList());

            // 4.4、组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 5、返回数据
        return pageResponse;

    }

    @Override
    @Transactional
    public boolean saveSpu(SpuAddDto dto) {
        // 1、保存SPU基本信息 -> pms_spu_info
        Spu spu = new Spu();
        BeanUtils.copyProperties(dto, spu);
        spu.setSpuCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_SPU_INIT_CODE.getDesc()));
        spuDao.save(spu);
        // 提取spuCode作为公共参数
        Long spuCode = spu.getSpuCode();

        // 2、保存Spu的描述图片 -> pms_spu_img_desc
        List<String> spuDescImgs = dto.getSpuDescImgs();
        if (CollectionUtils.isNotEmpty(spuDescImgs)) {
            SpuImgDesc spuImgDesc = new SpuImgDesc();
            spuImgDesc.setSpuCode(spuCode);
            spuImgDesc.setImgUrl(String.join(",", spuDescImgs));
            spuImgDescDao.save(spuImgDesc);
        }

        // 3、保存Spu的详情图片 -> pms_spu_img_detail
        List<String> spuDetailImgs = dto.getSpuDetailImgs();
        if (CollectionUtils.isNotEmpty(spuDetailImgs)) {
            spuImgDetailService.saveSpuDetailImgs(spuCode, spuDetailImgs);
        }

        // 4、保存Spu的规格参数 -> pms_spu_base_attribute_value
        // 查询出所有的属性相关的信息，转换成Map格式
        List<Attribute> attributes = attributeDao.list();
        Map<Long, Attribute> idAttributeMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getAttributeCode, attribute -> attribute));
        // 对前端传输过来的相关属性进行处理
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

        // 5、保存Spu的积分信息 -> sms_spu_bounds
        SpuAddDto.Bounds bounds = dto.getBounds();
        SpuBoundsAddReq spuBoundsAddReq = new SpuBoundsAddReq();
        BeanUtils.copyProperties(bounds, spuBoundsAddReq);
        spuBoundsAddReq.setSpuCode(spuCode);
        ApiResponse<Boolean> apiResult = couponFeignClient.saveSpuBounds(spuBoundsAddReq);
        if (ResultCode.SUCCESS.code() != apiResult.getCode()) {
            log.error(LOG_PRE + "远程保存spu积分信息失败");
            throw new BusinessException("远程保存spu积分信息失败");
        }

        // 6、保存当前Spu对应的所有Sku信息
        List<SkuAddDto> skuAddDtos = dto.getSkuAddDtos();
        if (CollectionUtils.isEmpty(skuAddDtos)) {
            log.error(LOG_PRE + "获取SKU信息异常");
            throw new BusinessException("获取SKU信息异常");
        }

        // 新建存储的集合对象
        List<Sku> skus = new ArrayList<>();
        List<SkuImg> skuImgs = new ArrayList<>();
        List<SkuSaleAttributeValue> skuSaleAttributeValues = new ArrayList<>();
        List<SkuFullReductionAddReq> skuFullReductionAddReqs = new ArrayList<>();
        List<SkuLadderAddReq> skuLadderAddReqs = new ArrayList<>();

        for (SkuAddDto skuAddDto : skuAddDtos) {
            // 6.1、Sku的基本信息 -> pms_sku_info
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

            // 6.2、Sku的图片信息 -> pms_sku_img
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

            // 6.3、Sku的属性 -> pms_sku_sale_attribute_value
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

            // 6.4、Sku的满减信息 -> sms_sku_full_reduction
            SkuFullReductionAddReq skuFullReductionAddReq = new SkuFullReductionAddReq();
            skuFullReductionAddReq.setSkuCode(skuCode);
            skuFullReductionAddReq.setFullPrice(skuAddDto.getFullPrice());
            skuFullReductionAddReq.setReducePrice(skuAddDto.getReducePrice());
            skuFullReductionAddReq.setAddOther(skuAddDto.getPriceStatus());
            skuFullReductionAddReqs.add(skuFullReductionAddReq);

            // 6.5、Sku的折扣信息 -> sms_sku_ladder
            SkuLadderAddReq skuLadderAddReq = new SkuLadderAddReq();
            skuLadderAddReq.setSkuCode(skuCode);
            skuLadderAddReq.setFullCount(skuAddDto.getFullCount());
            skuLadderAddReq.setDiscount(skuAddDto.getDiscount());
            skuLadderAddReq.setAddOther(skuAddDto.getCountStatus());
            skuLadderAddReqs.add(skuLadderAddReq);

            // 6.6、Sku的会员价格信息 -> sms_member_price
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
                log.error(LOG_PRE + "远程保存sku满减信息失败");
                throw new BusinessException("远程保存sku满减信息失败");
            }
        }
        if (CollectionUtils.isNotEmpty(skuLadderAddReqs)) {
            ApiResponse<Boolean> apiResult2 = couponFeignClient.saveSkuLadder(skuLadderAddReqs);
            if (ResultCode.SUCCESS.code() != apiResult2.getCode()) {
                log.error(LOG_PRE + "远程保存sku折扣信息失败");
                throw new BusinessException("远程保存sku折扣信息失败");
            }
        }
        return true;
    }

    // @GlobalTransactional(rollbackFor = Exception.class)
    // @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean upSpu(SpuUpDto dto) {
        // 1、获取当前提交的SpuCode
        Long spuCode = dto.getSpuCode();
        Spu spu = spuDao.queryInfoBySpuCode(spuCode);
        if (Objects.isNull(spu)) {
            throw new BusinessException("根据SpuCode无法查询到Spu信息");
        } else if (SpuPublishTypeEnum.SPU_UP.getCode().equals(spu.getPublishStatus())) {
            throw new BusinessException("当前Spu已经上架，请勿重复上架");
        }

        // 2、查询出当前spuCode对应的所有sku信息
        List<Sku> skus = skuDao.queryListBySpuCode(spuCode);
        List<Long> skuCodes = skus.stream().map(Sku::getSkuCode).collect(Collectors.toList());

        // 3、查询分类的信息
        Map<Long, String> categoryCodeAndNameMap = categoryDao.list().stream()
                .collect(Collectors.toMap(Category::getCategoryCode, Category::getCategoryName));

        // 4、查询品牌的信息
        Map<Long, Brand> brandCodeAndEntityMap = brandDao.list().stream()
                .collect(Collectors.toMap(Brand::getBrandCode, Function.identity()));

        // 5.1、查询出SpuCode对应的所有可以被用来检索的规格属性
        List<SpuBaseAttributeValue> spuBaseAttributeValues = spuBaseAttributeValueDao.queryListBySpuCode(spuCode);
        List<Long> attributeCodes = spuBaseAttributeValues.stream()
                .map(SpuBaseAttributeValue::getAttributeCode)
                .collect(Collectors.toList());

        // 5.2、根据SpuCode查询出来的规格属性Code再进行查询，查询出可检索的属性
        List<Attribute> attributes = attributeDao.queryListByAttributeCodes(attributeCodes, AttributeSearchTypeEnum.RETRIEVABLE.getCode());
        // 过滤出可检索属性Code并进行去重
        List<Long> searchAttributeCodes = attributes.stream().map(Attribute::getAttributeCode).distinct().collect(Collectors.toList());
        // 根据过滤出来的可检索属性Code过滤出属性信息
        List<SkuSearchAddReq.Attribute> attrsList = spuBaseAttributeValues.stream()
                .filter(item -> searchAttributeCodes.contains(item.getAttributeCode()))
                .map(item -> {
                    SkuSearchAddReq.Attribute attribute = new SkuSearchAddReq.Attribute();
                    attribute.setAttrCode(item.getAttributeCode());
                    attribute.setAttrName(item.getAttributeName());
                    attribute.setAttrValue(item.getAttributeValue());
                    return attribute;
                }).collect(Collectors.toList());

        // 6、发送远程调用，库存系统查询是否有库存
        Map<Long, Boolean> stockMap = new HashMap<>();
        try {
            WareStockDto wareStockDto = new WareStockDto();
            wareStockDto.setSkuCodes(skuCodes);
            ApiResponse<List<WareSkuRelationStockVo>> apiResponse = wareFeignClient.querySkuHasStock(wareStockDto);
            stockMap = apiResponse.getData().stream().collect(Collectors.toMap(WareSkuRelationStockVo::getSkuCode, WareSkuRelationStockVo::getHasStock));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因{}", e);
        }

        // 7、封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuSearchAddReq> collect = skus.stream()
                .map(sku -> {
                    // 组装需要的数据
                    SkuSearchAddReq esModel = new SkuSearchAddReq();
                    BeanUtils.copyProperties(sku, esModel);
                    // 设置Sku价格
                    esModel.setSkuPrice(sku.getSkuPrice());
                    // 设置Sku图片
                    esModel.setSkuDefaultImg(sku.getSkuDefaultImg());
                    // 热度评分。0
                    esModel.setHotScore(0L);
                    // 设置分类
                    esModel.setCategoryCode(sku.getCategoryCode());
                    if (sku.getCategoryCode() != null && MapUtils.isNotEmpty(categoryCodeAndNameMap)) {
                        esModel.setCategoryName(categoryCodeAndNameMap.get(sku.getCategoryCode()));
                    }
                    // 设置品牌
                    esModel.setBrandCode(sku.getBrandCode());
                    if (sku.getBrandCode() != null && MapUtils.isNotEmpty(brandCodeAndEntityMap)) {
                        Brand brand = brandCodeAndEntityMap.get(sku.getBrandCode());
                        if (brand != null) {
                            esModel.setBrandName(brand.getBrandName());
                            esModel.setLogo(brand.getBrandName());
                        }
                    }
                    // 设置检索属性
                    esModel.setAttributes(attrsList);
                    // 设置Sku库存信息
                    if (MapUtils.isEmpty(finalStockMap)) {
                        esModel.setHasStock(true);
                    } else {
                        esModel.setHasStock(finalStockMap.get(sku.getSkuCode()));
                    }
                    return esModel;
                })
                .collect(Collectors.toList());
        // 8、将数据发给es进行保存
        ApiResponse<Boolean> apiResponse = searchFeignService.saveProductToEs(collect);
        if (ResultCode.SUCCESS.code() == apiResponse.getCode()) {
            // 远程调用成功
            // 8.1、修改当前spu的状态
            spuDao.updateSpuPublishStatus(spuCode, SpuPublishTypeEnum.SPU_UP.getCode());
        } else {
            // 远程调用失败
            // TODO 8.2、重复调用？接口幂等性:重试机制
            /**
             * 1、构造请求数据，将对象转为json。
             *    RequestTemplate template = buildTemplateFromArgs.create(avgv);
             * 2、发送请求进行执行（执行成功会解码响应数据）
             *    executeAndDecode(template);
             * 3、执行请求会有重试机制
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
