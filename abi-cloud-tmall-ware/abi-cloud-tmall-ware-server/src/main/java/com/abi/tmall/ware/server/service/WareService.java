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

    PageResponse<WarePageResp> queryWarePageByCondition(WarePageReq warePageReq);

    List<Ware> queryWareListByCondition(WareListReq wareListReq);

    boolean saveWare(WareAddReq wareAddReq);

    boolean removeWare(WareDelReq wareDelReq);

    boolean modifyWare(WareEditReq wareEditReq);

    Ware findWareByCode(WareInfoReq wareInfoReq);

}
