package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 删除分类品牌关联关系
 */
@Data
@Api("删除分类品牌关联关系")
public class CbRelationDelReq {

    @NotEmpty(message = "品牌分类关联id集合不能为空")
    @ApiModelProperty(value = "品牌分类关联id集合", name = "brandCodes", example = "[1,2,3]", required = true)
    private List<Long> ids;

}
