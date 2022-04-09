package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GaRelationAddReq;
import com.abi.tmall.product.common.request.group.GaRelationDelReq;
import com.abi.tmall.product.common.request.group.GaRelationPageReq;
import com.abi.tmall.product.common.response.attribute.AttributePageResp;
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
 * 商品属性分组-属性关系 Console模块
 *
 * @ClassName: GroupAttributeRelationController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description:
 */
@Api(tags = "商品属性分组-属性关系 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/group-attribute-relation")
public class ConsoleGroupAttributeRelationController {

    @Autowired
    private GroupAttributeRelationService groupAttributeRelationService;

    @PostMapping("/page/no-attributes/by/groupCode")
    @ApiOperation(value = "查询 分组没有关联的属性列表")
    public ApiResponse<PageResponse<AttributePageResp>> queryNoAttributePageByGroupCode(@RequestBody GaRelationPageReq gaRelationPageReq) {
        return ApiResponse.result(groupAttributeRelationService.queryNoAttributePageByGroupCode(gaRelationPageReq));
    }

    @PostMapping("/list/attributes/by/groupCode")
    @ApiOperation(value = "查询 根据分组id查找关联的所有基本属性")
    public ApiResponse<PageResponse<AttributePageResp>> queryAttributeListByGroupCode(@RequestBody GaRelationPageReq gaRelationPageReq) {
        return ApiResponse.result(groupAttributeRelationService.queryAttributeListByGroupCode(gaRelationPageReq));
    }

    @PostMapping("/save/batch")
    @ApiOperation(value = "添加 分组和属性关系的对象")
    public ApiResponse<Object> batchSaveGroupAttributeRelation(@RequestBody List<GaRelationAddReq> gaRelationAddReqs) {
        return ApiResponse.result(groupAttributeRelationService.batchSaveGroupAttributeRelation(gaRelationAddReqs));
    }

    @PostMapping("/remove/batch")
    @ApiOperation(value = "删除 分组和属性关系的对象")
    public ApiResponse<Object> batchRemoveGroupAttributeRelation(@RequestBody List<GaRelationDelReq> gaRelationDelReqs) {
        return ApiResponse.result(groupAttributeRelationService.batchRemoveGroupAttributeRelation(gaRelationDelReqs));
    }

}
