package com.abi.tmall.product.common.request.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 删除品牌
 */
@Data
@Api("删除品牌")
public class BrandDelDto {

    @ApiModelProperty(value = "品牌Code集合", name = "brandCodes", example = "[1,2,3]", required = true)
    @NotEmpty(message = "品牌Code集合不能为空")
    private List<Long> brandCodes;

}
