package com.abi.tmall.product.common.request.group;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询分组分页
 */
@Data
@Api("查询分组分页")
public class GroupPageDto extends PageRequest {

    @ApiModelProperty(value = "属性分组名字")
    private String groupName;

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

}
