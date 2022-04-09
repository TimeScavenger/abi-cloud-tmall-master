package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.Group;
import com.abi.tmall.product.dao.mapper.GroupMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性分组 持久业务类
 *
 * @ClassName: GroupDao
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description:
 */
@Repository
public class GroupDao extends ServiceImpl<GroupMapper, Group> {

    public Page<Group> queryPage(Long pageNo, Long pageSize, Long categoryCode, String groupName) {
        Page<Group> page = this.lambdaQuery()
                .like(StringUtils.isNotBlank(groupName), Group::getGroupName, groupName)
                .eq(categoryCode != null && categoryCode != 0, Group::getCategoryCode, categoryCode)
                .page(new Page<Group>(pageNo, pageSize));
        return page;
    }

    public List<Group> queryListByCategoryCode(Long categoryCode) {
        List<Group> groups = this.lambdaQuery()
                .eq(Group::getCategoryCode, categoryCode)
                .list();
        return groups;
    }

    public List<Group> queryListByCategoryCodeAndGroupCode(Long categoryCode, Long groupCode) {
        List<Group> groups = this.lambdaQuery()
                .eq(Group::getCategoryCode, categoryCode)
                .ne(Group::getGroupCode, groupCode)
                .list();
        return groups;
    }

    public Group queryInfoByGroupCode(Long groupCode) {
        Group group = this.lambdaQuery()
                .eq(Group::getGroupCode, groupCode)
                .one();
        return group;
    }

    public Group queryInfoByCategoryCodeAndGroupName(Long categoryCode, String groupName) {
        Group group = this.lambdaQuery()
                .eq(Group::getCategoryCode, categoryCode)
                .eq(Group::getGroupName, groupName)
                .one();
        return group;
    }

    public boolean deleteByGroupCodes(List<Long> groupCodes) {
        if (CollectionUtils.isNotEmpty(groupCodes)) {
            LambdaUpdateWrapper<Group> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper
                    .in(Group::getGroupCode, groupCodes)
                    .set(Group::getDeleted, 1);
            this.update(null, updateWrapper);
            return true;
        }
        return false;
    }


}
