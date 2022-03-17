package com.abi.tmall.product.server.controller;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.enums.EnumEntity;
import com.abi.tmall.product.server.enums.AttributeTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: EnumController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 枚举模块
 */
@Api(tags = "枚举模块")
@Slf4j
@RestController
@RequestMapping("/enums")
public class EnumController {

    /**
     * 商品属性-类型
     *
     * @return
     */
    @GetMapping("/attributeTypeEnum")
    @ApiOperation("商品属性-类型")
    public ApiResponse<List<EnumEntity>> attributeTypeEnum() {
        return ApiResponse.result(AttributeTypeEnum.toList());
    }

}
