package com.abi.tmall.product.dao.entity;

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

/**
 * Spu商品评价回复关系
 */
@ApiModel(value = "Spu商品评价回复关系")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_spu_comment_replay_relation")
public class SpuCommentReplayRelation implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * 评论Code
     */
    @TableField(value = "spu_comment_code")
    @ApiModelProperty(value = "评论Code")
    private Long spuCommentCode;

    /**
     * 回复Code
     */
    @TableField(value = "reply_code")
    @ApiModelProperty(value = "回复Code")
    private Long replyCode;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMMENT_CODE = "comment_code";

    public static final String COL_REPLY_CODE = "reply_code";
}