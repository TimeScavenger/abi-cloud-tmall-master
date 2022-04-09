package com.abi.tmall.product.dao.mapper;

import com.abi.tmall.product.dao.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌 持久类
 *
 * @ClassName: BrandMapper
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 商品品牌 持久类
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 查询 品牌列表
     *
     * @return 品牌列表
     */
    List<Brand> selectBrandListByBrandName(@Param("brandName") String brandName);

    /**
     * 查询 根据品牌ids查询品牌列表
     *
     * @param brandIds 品牌id集合
     * @return 品牌列表
     */
    List<Brand> selectBrandListByIds(@Param("brandIds") List<Long> brandIds);

    /**
     * 查询 根据品牌ids查询品牌名称列表
     *
     * @param brandIds 品牌id集合
     * @return 品牌名称列表
     */
    List<String> selectBrandNameListByIds(@Param("brandIds") List<Long> brandIds);

    /**
     * 添加 品牌信息
     *
     * @param record 需要添加的品牌信息
     * @return 默认返回结果
     */
    Integer insertSelective(Brand record);

    /**
     * 修改 品牌信息
     *
     * @param record 需要修改的品牌信息
     * @return 默认返回结果
     */
    Integer updateByPrimaryKeySelective(Brand record);

    /**
     * 查询 根据品牌id查询品牌信息
     *
     * @param brandId 品牌id
     * @return 品牌信息
     */
    Brand selectBrandInfoVoById(Long brandId);

}
