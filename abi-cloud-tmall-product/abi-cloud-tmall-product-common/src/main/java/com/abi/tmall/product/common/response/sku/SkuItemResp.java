package com.abi.tmall.product.common.response.sku;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查询Sku详情
 */
@Data
@Api("查询Sku详情")
public class SkuItemResp {

    // 1、Sku的基本信息 - pms_sku
    @ApiModelProperty(value = "Sku基本信息")
    private SkuBase skuBase;

    // 2、Spu商品介绍图片 - pms_spu_img_desc
    @ApiModelProperty(value = "Spu商品介绍图片")
    private List<SpuImgDescVo> spuImgDescVoList;

    // 3、Spu商品详情图片 - pms_spu_img_detail
    private List<SpuImgDetailVo> spuImgDetailVoList;

    // 4、获取Spu的销售属性组合 - pms_sku_sale_attribute_value
    private List<SkuSaleAttributeValueVo> skuSaleAttributeValueVoList;

    // 5、获取Spu的分组规格参数信息组合 - pms_spu_base_attribute_value
    private List<SpuBaseAttributeValueVo> spuBaseAttributeValueVoList;

//    // 6、秒杀商品的优惠信息 -
//    private SeckillSkuVo seckillSkuVo;
//
//    private boolean hasStock = true;

    @Data
    @ApiModel(value = "Sku基本信息", description = "Sku基本信息")
    public static class SkuBase {

        @ApiModelProperty(value = "skuCode")
        private Long skuCode;

        @ApiModelProperty(value = "sku名称")
        private String skuName;

        @ApiModelProperty(value = "sku描述")
        private String skuDesc;

        @ApiModelProperty(value = "分类Code")
        private Long categoryCode;

        @ApiModelProperty(value = "品牌Code")
        private Long brandCode;

        @ApiModelProperty(value = "spuCode")
        private Long spuCode;

        @ApiModelProperty(value = "sku默认图片")
        private String skuDefaultImg;

        @ApiModelProperty(value = "sku标题")
        private String skuTitle;

        @ApiModelProperty(value = "sku副标题")
        private String skuSubTitle;

        @ApiModelProperty(value = "sku价格")
        private BigDecimal skuPrice;

        @ApiModelProperty(value = "sku销量")
        private Long skuSaleCount;

        @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
        private Integer showed;
    }

    @Data
    @ApiModel(value = "Spu商品介绍图片", description = "Spu商品介绍图片")
    public static class SpuImgDescVo {

        @ApiModelProperty(value = "spuCode")
        private Long spuCode;

        @ApiModelProperty(value = "商品介绍图片Url")
        private String imgUrl;

        @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
        private Integer showed;
    }

    @Data
    @ApiModel(value = "Spu商品详情图片", description = "Spu商品详情图片")
    public static class SpuImgDetailVo {

        @ApiModelProperty(value = "spuCode")
        private Long spuCode;

        @ApiModelProperty(value = "图片名")
        private String imgName;

        @ApiModelProperty(value = "图片地址")
        private String imgUrl;

        @ApiModelProperty(value = "图片顺序")
        private Integer imgSort;

        @ApiModelProperty(value = "是否默认图 0-否 1-是")
        private Integer imgType;

        @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
        private Integer showed;
    }

    @Data
    @ApiModel(value = "Spu的销售属性组合", description = "Spu的销售属性组合")
    public static class SkuSaleAttributeValueVo {

        @ApiModelProperty(value = "销售属性Code")
        private Long attributeCode;

        @ApiModelProperty(value = "销售属性名")
        private String attributeName;

        @ApiModelProperty(value = "销售属性值")
        private List<String> attributeValueList;
    }

    @Data
    @ApiModel(value = "Spu规格参数属性分组", description = "Spu规格参数属性分组")
    public static class SpuBaseAttributeValueVo {

        @ApiModelProperty(value = "规格参数分组信息")
        private String groupName;

        @ApiModelProperty(value = "Spu的规格参数信息")
        private List<SpuBaseAttribute> spuBaseAttributes;
    }

    @Data
    @ApiModel(value = "Spu的规格参数信息", description = "Spu的规格参数信息")
    public static class SpuBaseAttribute {

        @ApiModelProperty(value = "销售属性名")
        private String attributeName;

        @ApiModelProperty(value = "销售属性值")
        private String attributeValue;
    }

}
