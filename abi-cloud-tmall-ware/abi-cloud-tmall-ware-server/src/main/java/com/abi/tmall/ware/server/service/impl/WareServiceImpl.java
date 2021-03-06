package com.abi.tmall.ware.server.service.impl;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.exception.BusinessException;
import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.snowflake.SnowflakeIdWorker;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageVo;
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
 * @ClassName: WareServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 仓库信息
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private WareDao wareDao;

    @Override
    public PageResponse<WarePageVo> queryWarePageByCondition(WarePageDto dto) {
        // 1、新建用于返回的分页对象
        PageResponse<WarePageVo> pageResponse = new PageResponse<>();
        // 2、检查分页参数，如果分页未设置，则赋予默认值
        dto.checkParam();
        // 3、分页查询
        Page<Ware> page = wareDao.queryPageByCondition(dto.getPageNo(), dto.getPageSize(), dto.getWareName());
        // 4、数据进行转换、组装返回数据
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 数据进行转换
            List<WarePageVo> pageVoList = page.getRecords().stream()
                    .map(ware -> {
                        WarePageVo pageVo = new WarePageVo();
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

    @Override
    public List<Ware> queryWareListByCondition(WareListDto dto) {
        // 1、查询列表信息
        List<Ware> wareList = wareDao.queryListByCondition(dto.getWareName());
        // 2、返回数据
        return wareList;
    }

    @Override
    public boolean saveWare(WareAddDto dto) {
        // 1、判断是否为重复添加
        Ware result = wareDao.queryInfoByWareNameAndAreaCode(dto.getWareName(), dto.getAreaCode());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建仓库对象
        Ware ware = new Ware();
        BeanUtils.copyProperties(dto, ware);
        ware.setWareCode(snowflakeIdWorker.nextId());
        return wareDao.save(ware);
    }

    @Override
    public boolean removeWare(WareDelDto dto) {
        // 1、TODO 拓展：检查当前删除的仓库, 是否被别的地方引用，例如仓库和分销单的关联关系
        // 2、逻辑删除
        return wareDao.removeByWareCodes(dto.getWareCodes());
    }

    @Override
    public boolean modifyWare(WareEditDto dto) {
        // 1、查询校验分类是否合法
        Ware wareOld = wareDao.queryInfoByWareCode(dto.getWareCode());
        if (wareOld != null) {
            Ware wareNew = new Ware();
            BeanUtils.copyProperties(dto, wareNew);
            wareNew.setId(wareOld.getId());
            // 2、更新仓库对象数据
            wareDao.updateById(wareNew);
            // 3、返回数据
            return true;
        }
        return false;
    }

    @Override
    public Ware findWareByCode(WareInfoDto dto) {
        // 1、查询数据
        Ware ware = wareDao.queryInfoByWareCode(dto.getWareCode());
        // 2、返回数据
        if (ware != null) {
            return ware;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }

}
