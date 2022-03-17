package com.abi.tmall.product.common.request.sku;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询Sku分页
 */
@Data
@ApiModel("查询Sku分页")
public class SkuPageDto extends PageRequest {

    @ApiModelProperty(value = "所属分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "最小价格")
    private String priceMin;

    @ApiModelProperty(value = "最大价格")
    private String priceMax;

}