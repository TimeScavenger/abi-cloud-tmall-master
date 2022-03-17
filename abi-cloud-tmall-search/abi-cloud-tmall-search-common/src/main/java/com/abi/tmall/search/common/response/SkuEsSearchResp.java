package com.abi.tmall.search.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 搜索ES中Sku的数据
 */
@Data
public class SkuEsSearchResp {

    @ApiModelProperty(value = "查询到的所有商品信息")
    private List<SkuEsModel> product;

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "总页码")
    private Integer totalPages;

    @ApiModelProperty(value = "总记录数")
    private Long total;

    @ApiModelProperty(value = "当前查询到的结果，所有涉及到的所有分类")
    private List<CategoryVo> categorys;

    @ApiModelProperty(value = "当前查询到的结果，所有涉及到的品牌")
    private List<BrandVo> brands;

    @ApiModelProperty(value = "当前查询到的结果，所有涉及到的所有属性")
    private List<AttrVo> attrs;

    /* 面包屑导航数据 */
    private List<NavVo> navs;

    private List<Integer> pageNavs;

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

    @Data
    public static class CategoryVo {
        private Long categoryCode;
        private String categoryName;
    }

    @Data
    public static class BrandVo {
        private Long brandCode;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVo {
        private Long attrCode;
        private String attrName;
        private List<String> attrValue;
    }

}