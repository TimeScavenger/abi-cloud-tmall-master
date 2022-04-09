package com.abi.tmall.product.common.request.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 删除分组
 */
@Data
@Api("删除分组")
public class GroupDelReq {

    @NotEmpty(message = "分组Code集合不能为空")
    @ApiModelProperty(value = "分组Code集合", name = "groupCodes", example = "[1,2,3]", required = true)
    private List<Long> groupCodes;

}
