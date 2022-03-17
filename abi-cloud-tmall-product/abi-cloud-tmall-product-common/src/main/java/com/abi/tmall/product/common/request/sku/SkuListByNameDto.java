package com.abi.tmall.product.common.request.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 查询Sku列表
 */
@Data
@ApiModel("查询Sku列表")
public class SkuListByNameDto {

    @ApiModelProperty(value = "skuName")
    private String skuName;

}