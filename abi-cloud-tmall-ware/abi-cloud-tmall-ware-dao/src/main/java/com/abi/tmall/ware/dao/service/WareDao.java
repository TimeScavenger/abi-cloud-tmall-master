package com.abi.tmall.ware.dao.service;

import com.abi.tmall.ware.dao.entity.Ware;
import com.abi.tmall.ware.dao.mapper.WareMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 仓库 持久服务类
 *
 * @ClassName: WareMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class WareDao extends ServiceImpl<WareMapper, Ware> {

    public Page<Ware> queryPageByCondition(Long pageNo, Long pageSize, String wareName) {
        Page<Ware> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(wareName), Ware::getWareName, wareName)
                .page(new Page<Ware>(pageNo, pageSize));
        return page;
    }

    public List<Ware> queryListByCondition(String wareName) {
        List<Ware> wares = this.lambdaQuery()
                .like(StringUtils.isNotBlank(wareName), Ware::getWareName, wareName)
                .list();
        return wares;
    }

    public List<Ware> queryListByWareCodes(List<Long> wareCodes) {
        List<Ware> wares = this.lambdaQuery()
                .in(CollectionUtils.isNotEmpty(wareCodes), Ware::getWareCode, wareCodes)
                .list();
        return wares;
    }

    public Ware queryInfoByWareCode(Long wareCode) {
        Ware ware = this.lambdaQuery()
                .eq(Ware::getWareCode, wareCode)
                .one();
        return ware;
    }

    public Ware queryInfoByWareNameAndAreaCode(String wareName, String areaCode) {
        Ware ware = this.lambdaQuery()
                .eq(StringUtils.isNotBlank(wareName), Ware::getWareName, wareName)
                .eq(StringUtils.isNotBlank(areaCode), Ware::getAreaCode, areaCode)
                .one();
        return ware;
    }

    public boolean removeByWareCodes(List<Long> wareCodes) {
        if (CollectionUtils.isNotEmpty(wareCodes)) {
            LambdaUpdateWrapper<Ware> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(Ware::getWareCode, wareCodes)
                    .set(Ware::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }

}
