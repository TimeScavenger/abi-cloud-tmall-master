package com.abi.tmall.product.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Spu商品评价
 */
@ApiModel(value = "Spu商品评价")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_spu_comment")
public class SpuComment implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * spu评论Code
     */
    @TableField(value = "spu_comment_code")
    @ApiModelProperty(value = "spu评论Code")
    private Long spuCommentCode;

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * spu名字
     */
    @TableField(value = "spu_name")
    @ApiModelProperty(value = "spu名字")
    private String spuName;

    /**
     * 商品购买时属性组合
     */
    @TableField(value = "spu_attributes")
    @ApiModelProperty(value = "商品购买时属性组合")
    private String spuAttributes;

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 会员昵称
     */
    @TableField(value = "member_nick_name")
    @ApiModelProperty(value = "会员昵称")
    private String memberNickName;

    /**
     * 会员头像
     */
    @TableField(value = "member_icon")
    @ApiModelProperty(value = "会员头像")
    private String memberIcon;

    /**
     * 会员星级
     */
    @TableField(value = "member_star")
    @ApiModelProperty(value = "会员星级")
    private Integer memberStar;

    /**
     * 会员IP
     */
    @TableField(value = "member_ip")
    @ApiModelProperty(value = "会员IP")
    private String memberIp;

    /**
     * 评论内容
     */
    @TableField(value = "comment_content")
    @ApiModelProperty(value = "评论内容")
    private String commentContent;

    /**
     * 评论类型 0-对商品的直接评论，1-对评论的回复
     */
    @TableField(value = "comment_type")
    @ApiModelProperty(value = "评论类型 0-对商品的直接评论，1-对评论的回复")
    private Integer commentType;

    /**
     * 评论时间
     */
    @TableField(value = "comment_time")
    @ApiModelProperty(value = "评论时间")
    private LocalDateTime commentTime;

    /**
     * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
     */
    @TableField(value = "comment_resources")
    @ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String commentResources;

    /**
     * 点赞数
     */
    @TableField(value = "likes_count")
    @ApiModelProperty(value = "点赞数")
    private Integer likesCount;

    /**
     * 回复数
     */
    @TableField(value = "reply_count")
    @ApiModelProperty(value = "回复数")
    private Integer replyCount;

    /**
     * 是否显示 0-不显示，1-显示
     */
    @TableField(value = "showed")
    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

    /**
     * 启用状态 0-未启用，1-启用
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
    private Integer enabled;

    /**
     * 是否删除 0-未删除，1-删除
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "是否删除 0-未删除，1-删除")
    private Integer deleted;

    /**
     * 创建人Code
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人Code")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最近一次修改人Code
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value = "最近一次修改人Code")
    private String modifyBy;

    /**
     * 最近一次修改时间
     */
    @TableField(value = "modify_time")
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime modifyTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMMENT_CODE = "comment_code";

    public static final String COL_SPU_CODE = "spu_code";

    public static final String COL_SPU_NAME = "spu_name";

    public static final String COL_SPU_ATTRIBUTES = "spu_attributes";

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_MEMBER_NICK_NAME = "member_nick_name";

    public static final String COL_MEMBER_ICON = "member_icon";

    public static final String COL_MEMBER_STAR = "member_star";

    public static final String COL_MEMBER_IP = "member_ip";

    public static final String COL_COMMENT_CONTENT = "comment_content";

    public static final String COL_COMMENT_TYPE = "comment_type";

    public static final String COL_COMMENT_TIME = "comment_time";

    public static final String COL_COMMENT_RESOURCES = "comment_resources";

    public static final String COL_LIKES_COUNT = "likes_count";

    public static final String COL_REPLY_COUNT = "reply_count";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}