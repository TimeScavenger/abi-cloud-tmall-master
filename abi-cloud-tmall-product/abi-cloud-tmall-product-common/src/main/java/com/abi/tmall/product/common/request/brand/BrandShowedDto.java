package com.abi.tmall.product.common.request.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 修改品牌展示状态
 */
@Data
@Api("修改品牌展示状态")
public class BrandShowedDto {

    @ApiModelProperty(value = "品牌Code", name = "brandCode", example = "1", required = true)
    @NotNull(message = "品牌Code不能为空")
    private Long brandCode;

    @ApiModelProperty(value = "启用状态 0-未显示，1-显示", name = "showed", example = "1", required = true)
    @NotNull(message = "品牌显示状态不能为空")
    private Integer showed;

}
