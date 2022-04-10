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

    /**
     * 查询 分组没有关联的属性列表
     *
     * @param gaRelationPageReq 属性分组名字+属性名称
     * @return 属性列表
     */
    @PostMapping("/page/no-attributes/by/groupCode")
    @ApiOperation(value = "查询 分组没有关联的属性列表")
    public ApiResponse<PageResponse<AttributePageResp>> queryNoAttributePageByGroupCode(@RequestBody GaRelationPageReq gaRelationPageReq) {
        return ApiResponse.result(groupAttributeRelationService.queryNoAttributePageByGroupCode(gaRelationPageReq));
    }

    /**
     * 查询 根据分组Code查找关联的所有基本属性
     *
     * @param gaRelationPageReq 属性分组名字+属性名称
     * @return 属性列表
     */
    @PostMapping("/list/attributes/by/groupCode")
    @ApiOperation(value = "查询 根据分组Code查找关联的所有基本属性")
    public ApiResponse<PageResponse<AttributePageResp>> queryAttributeListByGroupCode(@RequestBody GaRelationPageReq gaRelationPageReq) {
        return ApiResponse.result(groupAttributeRelationService.queryAttributeListByGroupCode(gaRelationPageReq));
    }

    /**
     * 添加 分组和属性关系
     *
     * @param gaRelationAddReqs 分组和属性关系列表
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save/batch")
    @ApiOperation(value = "添加 分组和属性关系")
    public ApiResponse<Boolean> batchSaveGroupAttributeRelation(@RequestBody List<GaRelationAddReq> gaRelationAddReqs) {
        return ApiResponse.result(groupAttributeRelationService.batchSaveGroupAttributeRelation(gaRelationAddReqs));
    }

    /**
     * 删除 分组和属性关系
     *
     * @param gaRelationDelReqs 分组和属性关系列表
     * @return 删除是否成功: true-成功, false-失败
     */
    @PostMapping("/remove/batch")
    @ApiOperation(value = "删除 分组和属性关系")
    public ApiResponse<Boolean> batchRemoveGroupAttributeRelation(@RequestBody List<GaRelationDelReq> gaRelationDelReqs) {
        return ApiResponse.result(groupAttributeRelationService.batchRemoveGroupAttributeRelation(gaRelationDelReqs));
    }

}
