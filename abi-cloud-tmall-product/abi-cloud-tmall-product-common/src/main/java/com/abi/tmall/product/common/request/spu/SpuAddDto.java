package com.abi.tmall.product.common.request.spu;

import com.abi.tmall.product.common.request.sku.SkuAddDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 新增Spu
 */
@Data
@Api("新增Spu")
public class SpuAddDto {

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "商品简单描述")
    private String spuDesc;

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "上架状态 0-下架，1-上架")
    private Integer publishStatus;

    @ApiModelProperty(value = "Spu的介绍图片")
    private List<String> spuDescImgs;

    @ApiModelProperty(value = "Spu的详情图片")
    private List<String> spuDetailImgs;

    @ApiModelProperty(value = "积分信息")
    private Bounds bounds;

    @ApiModelProperty(value = "Spu的规格参数")
    private List<SpuBaseAttribute> spuBaseAttributes;

    @ApiModelProperty(value = "Sku商品列表")
    private List<SkuAddDto> skuAddDtos;

    @Data
    @ApiModel(value = "商品属性", description = "商品属性")
    public static class SpuBaseAttribute {
        @ApiModelProperty(value = "属性Code")
        private Long attributeCode;

        @ApiModelProperty(value = "可选值列表 用逗号分隔")
        private String valueList;

        @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】")
        private Integer quickShow;
    }

    @Data
    @ApiModel(value = "积分信息", description = "积分信息")
    public static class Bounds {
        @ApiModelProperty(value = "购物积分")
        private BigDecimal buyBounds;

        @ApiModelProperty(value = "成长积分")
        private BigDecimal growBounds;
    }

}