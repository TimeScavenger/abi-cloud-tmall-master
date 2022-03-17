package com.abi.tmall.product.common.response.attribute;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 查询属性信息
 */
@Data
@Api("查询属性信息")
public class BaseAttributeValueVo {

    @ApiModelProperty(value = "规格参数分组信息")
    private String groupName;

    @ApiModelProperty(value = "Spu的规格参数信息")
    private List<BaseAttributeValueVo.SpuBaseAttribute> spuBaseAttributes;

    @Data
    @ApiModel(value = "Spu的规格参数信息", description = "Spu的规格参数信息")
    public static class SpuBaseAttribute {

        @ApiModelProperty(value = "销售属性名")
        private String attributeName;

        @ApiModelProperty(value = "销售属性值")
        private String attributeValue;
    }
}
