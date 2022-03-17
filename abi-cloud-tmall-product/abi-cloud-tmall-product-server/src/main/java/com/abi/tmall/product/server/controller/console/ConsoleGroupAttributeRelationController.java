package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.group.GaRelationAddDto;
import com.abi.tmall.product.common.request.group.GaRelationDelDto;
import com.abi.tmall.product.common.request.group.GaRelationPageDto;
import com.abi.tmall.product.common.response.attribute.AttributePageVo;
import com.abi.tmall.product.server.service.GroupAttributeRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: GroupAttributeRelationController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 属性分组-属性关系
 */
@Api(tags = "属性分组-属性关系模块")
@Slf4j
@RestController
@RequestMapping("/console/group-attribute-relation")
public class ConsoleGroupAttributeRelationController {

    @Autowired
    private GroupAttributeRelationService groupAttributeRelationService;

    @PostMapping("/page/no-attributes/by/groupCode")
    @ApiOperation(value = "查询 分组没有关联的属性列表")
    public ApiResponse<PageResponse<AttributePageVo>> queryNoAttributePageByGroupCode(@RequestBody GaRelationPageDto gaRelationPageDto) {
        return ApiResponse.result(groupAttributeRelationService.queryNoAttributePageByGroupCode(gaRelationPageDto));
    }

    @PostMapping("/list/attributes/by/groupCode")
    @ApiOperation(value = "查询 根据分组id查找关联的所有基本属性")
    public ApiResponse<PageResponse<AttributePageVo>> queryAttributeListByGroupCode(@RequestBody GaRelationPageDto gaRelationPageDto) {
        return ApiResponse.result(groupAttributeRelationService.queryAttributeListByGroupCode(gaRelationPageDto));
    }

    @PostMapping("/save/batch")
    @ApiOperation(value = "添加 分组和属性关系的对象")
    public ApiResponse<Object> batchSaveGroupAttributeRelation(@RequestBody List<GaRelationAddDto> gaRelationAddDtos) {
        return ApiResponse.result(groupAttributeRelationService.batchSaveGroupAttributeRelation(gaRelationAddDtos));
    }

    @PostMapping("/remove/batch")
    @ApiOperation(value = "删除 分组和属性关系的对象")
    public ApiResponse<Object> batchRemoveGroupAttributeRelation(@RequestBody List<GaRelationDelDto> gaRelationDelDtos) {
        return ApiResponse.result(groupAttributeRelationService.batchRemoveGroupAttributeRelation(gaRelationDelDtos));
    }

}
