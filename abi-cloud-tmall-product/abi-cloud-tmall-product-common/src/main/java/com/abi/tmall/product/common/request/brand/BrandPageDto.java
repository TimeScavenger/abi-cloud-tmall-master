package com.abi.tmall.product.common.request.brand;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询品牌分页
 */
@Data
@Api("查询品牌分页")
public class BrandPageDto extends PageRequest {

    @ApiModelProperty(value = "品牌名字", name = "brandName", example = "小米")
    private String brandName;

}
