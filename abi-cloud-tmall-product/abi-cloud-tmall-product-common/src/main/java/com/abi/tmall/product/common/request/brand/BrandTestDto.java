package com.abi.tmall.product.common.request.brand;

import com.abi.infrastructure.core.verified.annotation.EnumValue2;
import com.abi.infrastructure.core.verified.group.AddGroup;
import com.abi.infrastructure.core.verified.group.UpdateGroup;
import com.abi.infrastructure.core.verified.group.UpdateStatusGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 测试品牌字段校验
 */
@Data
@Api("测试品牌字段校验")
public class BrandTestDto implements Serializable {
    /**
     * 品牌Code
     */
    @NotNull(message = "修改品牌信息必须指定品牌Code", groups = {UpdateGroup.class, UpdateStatusGroup.class})
    @Null(message = "新增品牌信息不能指定Code", groups = {AddGroup.class})
    @ApiModelProperty(value = "品牌Code")
    private Long brandId;

    /**
     * 品牌名字
     */
    @NotBlank(message = "品牌名字不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "品牌名字")
    private String brandName;

    /**
     * 品牌logo地址
     */
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "品牌logo地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "logo地址")
    private String logo;

    /**
     * 品牌描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 检索首字母
     */
    // 自定义校验规则
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "检索首字母")
    private String firstLetter;

    /**
     * 排序
     */
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class})
    @NotNull(message = "品牌排序不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 是否显示 0-不显示，1-显示
     */
    @EnumValue2(values = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class})
    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

}
