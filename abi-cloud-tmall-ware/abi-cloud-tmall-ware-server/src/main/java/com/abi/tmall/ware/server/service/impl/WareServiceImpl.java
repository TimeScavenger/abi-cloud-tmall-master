package com.abi.tmall.ware.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.web.snowflake.SnowflakeIdWorker;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageResp;
import com.abi.tmall.ware.dao.entity.Ware;
import com.abi.tmall.ware.dao.mapper.WareMapper;
import com.abi.tmall.ware.dao.service.WareDao;
import com.abi.tmall.ware.server.service.WareService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 仓库 服务实现类
 *
 * @ClassName: WareServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private WareDao wareDao;

    /**
     * 查询 仓库分页列表
     *
     * @param req 查询条件
     * @return 仓库分页列表
     */
    @Override
    public PageResponse<WarePageResp> queryWarePageByCondition(WarePageReq req) {
        // 1、新建用于返回的分页对象
        PageResponse<WarePageResp> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        req.checkParam();
        // 3、分页查询
        Page<Ware> page = wareDao.queryPageByCondition(req.getPageNo(), req.getPageSize(), req.getWareName());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<WarePageResp> pageVoList = page.getRecords().stream()
                    .map(ware -> {
                        WarePageResp pageVo = new WarePageResp();
                        BeanUtils.copyProperties(ware, pageVo);
                        return pageVo;
                    })
                    .collect(Collectors.toList());
            // 组装返回数据
            pageResponse.setTotal(page.getTotal());
            pageResponse.setPage(page.getCurrent());
            pageResponse.setSize(page.getSize());
            pageResponse.setRecords(pageVoList);
        }
        // 5、返回数据
        return pageResponse;
    }

    /**
     * 查询 仓库列表
     *
     * @param req 查询条件
     * @return 仓库列表
     */
    @Override
    public List<Ware> queryWareListByCondition(WareListReq req) {
        // 1、查询列表信息
        List<Ware> wareList = wareDao.queryListByCondition(req.getWareName());
        // 2、返回数据
        return wareList;
    }

    /**
     * 新增 仓库信息
     *
     * @param req 仓库信息
     * @return 新增是否成功: true-成功, false-失败
     */
    @Override
    public boolean saveWare(WareAddReq req) {
        // 1、判断是否为重复添加
        Ware result = wareDao.queryInfoByWareNameAndAreaCode(req.getWareName(), req.getAreaCode());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建仓库对象
        Ware ware = new Ware();
        BeanUtils.copyProperties(req, ware);
        ware.setWareCode(snowflakeIdWorker.nextId());
        return wareDao.save(ware);
    }

    /**
     * 删除 仓库信息
     *
     * @param req 仓库Code
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeWare(WareDelReq req) {
        // 1、TODO 拓展：检查当前删除的仓库, 是否被别的地方引用，例如仓库和分销单的关联关系
        // 2、逻辑删除
        return wareDao.removeByWareCodes(req.getWareCodes());
    }

    /**
     * 修改 仓库信息
     *
     * @param req 仓库信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    public boolean modifyWare(WareEditReq req) {
        // 1、查询校验分类是否合法
        Ware wareOld = wareDao.queryInfoByWareCode(req.getWareCode());
        if (wareOld != null) {
            Ware wareNew = new Ware();
            BeanUtils.copyProperties(req, wareNew);
            wareNew.setId(wareOld.getId());
            // 2、更新仓库对象数据
            wareDao.updateById(wareNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    /**
     * 查询 仓库信息
     *
     * @param req 仓库Code
     * @return 仓库信息
     */
    @Override
    public Ware findWareByCode(WareInfoReq req) {
        // 1、查询数据
        Ware ware = wareDao.queryInfoByWareCode(req.getWareCode());
        // 2、返回数据
        if (ware != null) {
            return ware;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }

}
