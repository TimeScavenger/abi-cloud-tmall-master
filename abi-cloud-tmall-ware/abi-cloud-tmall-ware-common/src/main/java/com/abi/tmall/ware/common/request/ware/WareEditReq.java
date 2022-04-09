package com.abi.tmall.ware.common.request.ware;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 仓库信息
 */
@Data
@ApiModel(value = "仓库信息")
public class WareEditReq {

    @NotNull(message = "仓库Code不能为空")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @NotBlank(message = "仓库名称不能为空")
    @ApiModelProperty(value = "仓库名")
    private String wareName;

    @NotBlank(message = "仓库地址不能为空")
    @ApiModelProperty(value = "仓库地址")
    private String wareAddress;

    @NotBlank(message = "区域编码不能为空")
    @ApiModelProperty(value = "区域编码")
    private String areaCode;

}