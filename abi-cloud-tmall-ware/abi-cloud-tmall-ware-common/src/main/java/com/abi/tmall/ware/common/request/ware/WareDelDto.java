package com.abi.tmall.ware.common.request.ware;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 仓库信息
 */
@ApiModel(value = "仓库信息")
@Data
public class WareDelDto {

    @NotEmpty(message = "仓库Code不能为空")
    @ApiModelProperty(value = "仓库Code")
    private List<Long> wareCodes;

}