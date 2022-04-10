package com.abi.tmall.ware.server.service;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageResp;
import com.abi.tmall.ware.dao.entity.Ware;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 仓库 服务类
 *
 * @ClassName: WareService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 仓库信息
 */
public interface WareService extends IService<Ware> {

    /**
     * 查询 仓库分页列表
     *
     * @param warePageReq 查询条件
     * @return 仓库分页列表
     */
    PageResponse<WarePageResp> queryWarePageByCondition(WarePageReq warePageReq);

    /**
     * 查询 仓库列表
     *
     * @param wareListReq 查询条件
     * @return 仓库列表
     */
    List<Ware> queryWareListByCondition(WareListReq wareListReq);

    /**
     * 新增 仓库信息
     *
     * @param wareAddReq 仓库信息
     * @return 新增是否成功: true-成功, false-失败
     */
    boolean saveWare(WareAddReq wareAddReq);

    /**
     * 删除 仓库信息
     *
     * @param wareDelReq 仓库Code
     * @return 删除是否成功: true-成功, false-失败
     */
    boolean removeWare(WareDelReq wareDelReq);

    /**
     * 修改 仓库信息
     *
     * @param wareEditReq 仓库信息
     * @return 修改是否成功: true-成功, false-失败
     */
    boolean modifyWare(WareEditReq wareEditReq);

    /**
     * 查询 仓库信息
     *
     * @param wareInfoReq 仓库Code
     * @return 仓库信息
     */
    Ware findWareByCode(WareInfoReq wareInfoReq);

}
