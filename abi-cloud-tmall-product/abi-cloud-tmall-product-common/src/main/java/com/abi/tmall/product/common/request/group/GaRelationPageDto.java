package com.abi.tmall.product.common.request.group;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询分组属性关联关系分页
 */
@Data
@ApiModel(description = "查询分组属性关联关系分页")
public class GaRelationPageDto extends PageRequest {

    @ApiModelProperty(value = "属性分组名字")
    private Long groupCode;

    @ApiModelProperty(value = "属性名称")
    private String attributeName;

}
