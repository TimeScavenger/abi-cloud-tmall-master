package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.product.common.request.group.GroupAddReq;
import com.abi.tmall.product.common.request.group.GroupDelReq;
import com.abi.tmall.product.common.request.group.GroupEditReq;
import com.abi.tmall.product.common.request.group.GroupPageReq;
import com.abi.tmall.product.common.response.group.GroupInfoResp;
import com.abi.tmall.product.common.response.group.GroupPageResp;
import com.abi.tmall.product.server.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品属性分组 Console模块
 *
 * @ClassName: GroupController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 属性分组
 */
@Api(tags = "商品属性分组 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/group")
public class ConsoleGroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 查询 分组分页列表
     *
     * @param groupPageReq 查询条件
     * @return 分组分页列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "查询 分组分页列表")
    public ApiResponse<PageResponse<GroupPageResp>> queryGroupPageByCondition(@RequestBody GroupPageReq groupPageReq) {
        return ApiResponse.result(groupService.queryGroupPageByCondition(groupPageReq));
    }

    /**
     * 添加 分组信息
     *
     * @param groupAddReq 分组信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 分组信息")
    public ApiResponse<Boolean> saveGroup(@RequestBody @Validated GroupAddReq groupAddReq) {
        return ApiResponse.result(groupService.saveGroup(groupAddReq));
    }

    /**
     * 删除 根据Codes删除分组信息
     *
     * @param groupDelReq 分组Codes
     * @return 删除是否成功: true-成功, false-失败
     */
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 根据分组Codes删除分组信息")
    public ApiResponse<Boolean> removeGroup(@RequestBody @Validated GroupDelReq groupDelReq) {
        return ApiResponse.result(groupService.removeGroup(groupDelReq));
    }

    /**
     * 修改 分组信息
     *
     * @param groupEditReq 分组信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改 分组信息")
    public ApiResponse<Boolean> modifyGroup(@RequestBody @Validated GroupEditReq groupEditReq) {
        return ApiResponse.result(groupService.modifyGroup(groupEditReq));
    }

    /**
     * 查询 根据分组Code查询分组信息
     *
     * @param groupCode 分组Code
     * @return 分组信息
     */
    @GetMapping("/find/{groupCode}")
    @ApiOperation(value = "查询 根据分组Code查询分组信息")
    public ApiResponse<GroupInfoResp> findGroupByCode(@PathVariable("groupCode") Long groupCode) {
        return ApiResponse.result(groupService.findGroupByCode(groupCode));
    }

}
