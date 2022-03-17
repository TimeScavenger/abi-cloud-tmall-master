package com.abi.tmall.coupon.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品会员价格
 */
@ApiModel(value = "商品会员价格")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms_member_price")
public class MemberPrice implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 会员等级Id
     */
    @TableField(value = "member_level_id")
    @ApiModelProperty(value = "会员等级Id")
    private Long memberLevelId;

    /**
     * 会员等级名
     */
    @TableField(value = "member_level_name")
    @ApiModelProperty(value = "会员等级名")
    private String memberLevelName;

    /**
     * 会员对应价格
     */
    @TableField(value = "member_price")
    @ApiModelProperty(value = "会员对应价格")
    private BigDecimal memberPrice;

    /**
     * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
     */
    @TableField(value = "add_other")
    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SKU_ID = "sku_code";

    public static final String COL_MEMBER_LEVEL_ID = "member_level_id";

    public static final String COL_MEMBER_LEVEL_NAME = "member_level_name";

    public static final String COL_MEMBER_PRICE = "member_price";

    public static final String COL_ADD_OTHER = "add_other";
}