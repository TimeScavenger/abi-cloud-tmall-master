package com.abi.tmall.product.common.request.brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 新增品牌
 */
@Data
@Api("新增品牌")
public class BrandAddReq {

    @NotBlank(message = "品牌名称不能为空")
    @ApiModelProperty(value = "品牌名字", name = "brandName", example = "小米", required = true)
    private String brandName;

    @NotBlank(message = "品牌logo不能为空")
    @ApiModelProperty(value = "品牌logo", name = "logo", example = "https://tmall-master.oss-cn-shanghai.aliyuncs.com/2021-03-28/a1dd375d-8c3d-4d0c-8156-333ed8e3070f.jpeg", required = true)
    private String logo;

    @ApiModelProperty(value = "品牌描述", name = "description", example = "小米")
    private String description;

    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

    @ApiModelProperty(value = "检索首字母", name = "firstLetter", example = "A")
    private String firstLetter;

    @ApiModelProperty(value = "排序", name = "brandName", example = "1")
    private Integer sort;

}