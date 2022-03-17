package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.group.GroupAddDto;
import com.abi.tmall.product.common.request.group.GroupDelDto;
import com.abi.tmall.product.common.request.group.GroupEditDto;
import com.abi.tmall.product.common.request.group.GroupPageDto;
import com.abi.tmall.product.common.response.group.GroupInfoVo;
import com.abi.tmall.product.common.response.group.GroupPageVo;
import com.abi.tmall.product.server.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GroupController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 属性分组
 */
@Api(tags = "属性分组模块")
@Slf4j
@RestController
@RequestMapping("/console/group")
public class ConsoleGroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 分组分页列表")
    public ApiResponse<PageResponse<GroupPageVo>> queryGroupPageByCondition(@RequestBody GroupPageDto groupPageDto) {
        return ApiResponse.result(groupService.queryGroupPageByCondition(groupPageDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 分组信息")
    public ApiResponse<Boolean> saveGroup(@RequestBody @Validated GroupAddDto groupAddDto) {
        return ApiResponse.result(groupService.saveGroup(groupAddDto));
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 根据ids删除分组信息")
    public ApiResponse<Boolean> removeGroup(@RequestBody @Validated GroupDelDto groupDelDto) {
        return ApiResponse.result(groupService.removeGroup(groupDelDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 分组信息")
    public ApiResponse<Boolean> modifyGroup(@RequestBody @Validated GroupEditDto groupEditDto) {
        return ApiResponse.result(groupService.modifyGroup(groupEditDto));
    }

    @GetMapping("/find/{groupCode}")
    @ApiOperation(value = "查询 根据分组id")
    public ApiResponse<GroupInfoVo> findGroupByCode(@PathVariable("groupCode") Long groupCode) {
        return ApiResponse.result(groupService.findGroupByCode(groupCode));
    }

}
