package com.abi.tmall.product.common.request.sku;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 新增Sku
 */
@Data
@Api("新增Sku")
public class SkuAddReq {

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "标题")
    private String skuTitle;

    @ApiModelProperty(value = "副标题")
    private String skuSubTitle;

    @ApiModelProperty(value = "价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "满多少")
    private BigDecimal fullPrice;

    @ApiModelProperty(value = "减多少")
    private BigDecimal reducePrice;

    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer priceStatus;

    @ApiModelProperty(value = "满几件")
    private String fullCount;

    @ApiModelProperty(value = "打几折")
    private String discount;

    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer countStatus;

    @ApiModelProperty(value = "属性")
    private List<Attribute> attr;

    @ApiModelProperty(value = "sku产品")
    private List<String> descar;

    @ApiModelProperty(value = "sku图集")
    private List<Images> skuDetailImgs;

    @ApiModelProperty(value = "会员价格")
    private List<MemberPrice> memberPrice;

    @Data
    @ApiModel(value = "属性信息", description = "属性信息")
    public static class Attribute {
        @ApiModelProperty(value = "属性id")
        private Long attributeCode;

        @ApiModelProperty(value = "属性名")
        private String attributeName;

        @ApiModelProperty(value = "可选值列表 用逗号分隔")
        private String attrValue;
    }

    @Data
    @ApiModel(value = "sku图集", description = "sku图集")
    public static class Images {
        @ApiModelProperty(value = "图片地址")
        private String imgUrl;

        @ApiModelProperty(value = "是否是默认图片  0-不是默认图片，1-默认图片")
        private Integer defaultImg;
    }

    @Data
    @ApiModel(value = "会员价格", description = "会员价格")
    public static class MemberPrice {
        @ApiModelProperty(value = "会员级别ID")
        private Long id;

        @ApiModelProperty(value = "会员级别名称")
        private String name;

        @ApiModelProperty(value = "会员价格")
        private BigDecimal price;
    }
}