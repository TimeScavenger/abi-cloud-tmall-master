package com.abi.tmall.product.common.request.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询品牌列表
 */
@Data
@Api("查询品牌列表")
public class BrandListDto {

    @ApiModelProperty(value = "品牌名字", name = "brandName", example = "小米")
    private String brandName;

}
