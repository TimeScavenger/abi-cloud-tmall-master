package com.abi.tmall.ware.server.service;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageVo;
import com.abi.tmall.ware.dao.entity.Ware;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: WareService
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 仓库信息
 */
public interface WareService extends IService<Ware> {

    PageResponse<WarePageVo> queryWarePageByCondition(WarePageDto warePageDto);

    List<Ware> queryWareListByCondition(WareListDto wareListDto);

    boolean saveWare(WareAddDto wareAddDto);

    boolean removeWare(WareDelDto wareDelDto);

    boolean modifyWare(WareEditDto wareEditDto);

    Ware findWareByCode(WareInfoDto wareInfoDto);

}
