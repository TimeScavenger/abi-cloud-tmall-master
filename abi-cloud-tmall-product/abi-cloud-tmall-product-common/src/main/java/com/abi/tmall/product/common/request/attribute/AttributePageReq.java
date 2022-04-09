package com.abi.tmall.product.common.request.attribute;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询属性分页
 */
@Data
@ApiModel(description = "查询属性分页")
public class AttributePageReq extends PageRequest {

    @ApiModelProperty(value = "属性名称", name = "attributeName", example = "基本属性")
    private String attributeName;

    @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性", name = "type", example = "1")
    private Integer type;

    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1")
    private Long categoryCode;

}
