package com.abi.tmall.product.dao.service;

import com.abi.tmall.product.dao.entity.GroupAttributeRelation;
import com.abi.tmall.product.dao.mapper.GroupAttributeRelationMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GroupAttributeRelationDao
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description: 属性分组-属性关系
 */
@Repository
public class GroupAttributeRelationDao extends ServiceImpl<GroupAttributeRelationMapper, GroupAttributeRelation> {

    public List<GroupAttributeRelation> queryListByGroupCodes(List<Long> groupCodes) {
        List<GroupAttributeRelation> groupAttributeRelations = this.lambdaQuery()
                .in(GroupAttributeRelation::getGroupCode, groupCodes)
                .list();
        return groupAttributeRelations;
    }

    public GroupAttributeRelation queryInfoByGroupCodeAndAttributeCode(Long attributeCode, Long groupCode) {
        GroupAttributeRelation groupAttributeRelation = this.lambdaQuery()
                .eq(GroupAttributeRelation::getGroupCode, groupCode)
                .eq(GroupAttributeRelation::getAttributeCode, attributeCode)
                .one();
        return groupAttributeRelation;
    }

    public GroupAttributeRelation queryInfoByAttributeCode(Long attributeCode) {
        GroupAttributeRelation groupAttributeRelation = this.lambdaQuery()
                .eq(GroupAttributeRelation::getAttributeCode, attributeCode)
                .one();
        return groupAttributeRelation;
    }

    public void updateGroupNameByGroupCode(Long groupCode, String groupName) {
        LambdaUpdateWrapper<GroupAttributeRelation> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(GroupAttributeRelation::getGroupCode, groupCode)
                .set(GroupAttributeRelation::getGroupName, groupName);
        this.update(null, updateWrapper);
    }

    public void deleteByAttributeCode(Long attributeCode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("attribute_code", attributeCode);
        this.remove(wrapper);
    }

}
