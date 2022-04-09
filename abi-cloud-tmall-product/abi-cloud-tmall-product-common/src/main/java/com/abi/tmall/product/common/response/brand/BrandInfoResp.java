package com.abi.tmall.product.common.response.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询品牌信息
 */
@Data
@Api("查询品牌信息")
public class BrandInfoResp {

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名字")
    private String brandName;

    @ApiModelProperty(value = "logo地址")
    private String logo;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "检索首字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

}
