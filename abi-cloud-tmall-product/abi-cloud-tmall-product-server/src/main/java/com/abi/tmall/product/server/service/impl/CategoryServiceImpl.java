package com.abi.tmall.product.server.service.impl;

import com.abi.infrastructure.core.base.ResultCode;
import com.abi.infrastructure.core.constant.NumberConstants;
import com.abi.infrastructure.core.exception.BusinessException;
import com.abi.infrastructure.web.util.EmptyUtils;
import com.abi.infrastructure.web.util.GenerateCodeUtils;
import com.abi.infrastructure.web.util.JacksonUtils;
import com.abi.tmall.product.common.request.category.CategoryAddReq;
import com.abi.tmall.product.common.request.category.CategoryDelReq;
import com.abi.tmall.product.common.request.category.CategoryEditReq;
import com.abi.tmall.product.common.request.category.CategorySortReq;
import com.abi.tmall.product.common.response.category.CategoryInfoResp;
import com.abi.tmall.product.common.response.category.CategoryTreeResp;
import com.abi.tmall.product.dao.entity.Category;
import com.abi.tmall.product.dao.mapper.CategoryMapper;
import com.abi.tmall.product.dao.service.CategoryBrandRelationDao;
import com.abi.tmall.product.dao.service.CategoryDao;
import com.abi.tmall.product.server.constant.CategoryConstants;
import com.abi.tmall.product.server.enums.ProductInitCodeEnum;
import com.abi.tmall.product.server.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.abi.infrastructure.core.constant.CollectionConstants.*;

/**
 * 商品分类 服务实现类
 *
 * @ClassName: CategoryServiceImpl
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description:
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 查询 分类以及子分类 以树形结构组装起来
     *
     * @param level 层级
     * @return 分类以及子分类树形结构
     */
    @Override
    public List<CategoryTreeResp> queryCategoryListWithTree(Integer level) {
        // 1、定义一个临时的集合
        List<Integer> levelList = new ArrayList<>();
        String levleStr;
        switch (level) {
            case 1:
                levelList = LIST_ONE_INT;
                levleStr = CategoryConstants.LEVEL_ONE;
                break;
            case 2:
                levelList = LIST_TWO_INT;
                levleStr = CategoryConstants.LEVEL_TWO;
                break;
            case 3:
                levelList = LIST_THREE_INT;
                levleStr = CategoryConstants.LEVEL_THREE;
                break;
            case 4:
                levelList = LIST_FOUR_INT;
                levleStr = CategoryConstants.LEVEL_FOUR;
                break;
            default:
                levelList = LIST_ONE_INT;
                levleStr = CategoryConstants.LEVEL_ONE;
                break;
        }

        // 1、加入缓存逻辑，缓存中寸的数据是json字符串
        // JSON跨语言，跨平台兼容
        String str = redisTemplate.opsForValue().get("CategoryListWithTree" + levleStr);

        List<CategoryTreeResp> levelMenus = new ArrayList<>();
        // 判断Redis中是否有缓存数据
        if (StringUtils.isBlank(str)) {
            // 缓存中没有，查询数据库
            // 2、查出所有分类, 没有查询条件就是查询所有
            List<Category> categorys = categoryDao.queryListByLevel(levelList);
            List<CategoryTreeResp> categoryTreeResps = categorys.stream()
                    .map(category -> {
                        CategoryTreeResp categoryTreeResp = new CategoryTreeResp();
                        BeanUtils.copyProperties(category, categoryTreeResp);
                        return categoryTreeResp;
                    })
                    .collect(Collectors.toList());
            // 3、组装成父子的树形结构
            levelMenus = categoryTreeResps.stream()
                    .filter(treeVo -> NumberConstants.ZERO_LONG.equals(treeVo.getParentCode())) // 简化形式：filter里面只有一行可以省略大括号
                    .map(treeVo -> {
                        // 1、找到子分类
                        treeVo.setChildren(getChildrens(treeVo, categoryTreeResps));
                        return treeVo;
                    })
                    .sorted((menu1, menu2) -> {
                        // 2、分类的排序, 判断防止空指针异常
                        return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                    })
                    .collect(Collectors.toList());
            // 4、查询到的数据放入缓存，将对象转为JSON放在缓存中
            //（2）、设置过期时间（加随机值）：解决缓存雪崩
            redisTemplate.opsForValue().set("CategoryListWithTree" + levleStr, JacksonUtils.toJson(levelMenus), 1, TimeUnit.DAYS);

        } else {
            levelMenus = JacksonUtils.toList(str, CategoryTreeResp.class);
        }
        // 5、返回数据
        return levelMenus;
    }

    /**
     * （1）、空结果缓存：解决缓存穿透
     * （2）、设置过期时间（加随机值）：解决缓存雪崩
     * （3）、加锁：解决缓存击穿
     */
    private List<CategoryTreeResp> queryCategoryListWithTreeWithRedisson(String levleStr, List<Integer> levelList) {
        // 1、设置返回结果集
        List<CategoryTreeResp> levelMenus = new ArrayList<>();

        // 2、锁的名字。锁的粒度，越洗越快。
        // 锁的粒度：具体缓存的是某个数据，11号商品：product-11-lock，product-12-lock
        RLock lock = redissonClient.getLock("CategoryListWithTree-lock-" + levleStr);
        lock.lock();

        try {
            // 设置过期时间，否则如果在业务中出现异常，不往下走，没有及时删除锁，会出现死锁
            // redisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            // 3、加入缓存逻辑，缓存中存的数据是json字符串
            String redisStr = redisTemplate.opsForValue().get("CategoryListWithTree" + levleStr); // JSON跨语言，跨平台兼容
            // 判断Redis中是否有缓存数据，没有：查询数据库，有：直接从Redis中获取
            if (StringUtils.isBlank(redisStr)) {
                // 3、执行查询业务

                // 4、查询到的数据放入缓存，将对象转为JSON放在缓存中
                //（2）、设置过期时间（加随机值）：解决缓存雪崩
                redisTemplate.opsForValue().set("CategoryListWithTree" + levleStr, JacksonUtils.toJson(levelMenus), 1, TimeUnit.DAYS);
            } else {
                levelMenus = JacksonUtils.toList(redisStr, CategoryTreeResp.class);
            }
        } finally {
            lock.unlock();
        }

        // 5、返回数据
        return levelMenus;
    }

    /**
     * （1）、空结果缓存：解决缓存穿透
     * （2）、设置过期时间（加随机值）：解决缓存雪崩
     * （3）、加锁：解决缓存击穿
     */
    private List<CategoryTreeResp> queryCategoryListWithTreeWithRedis(String levleStr, List<Integer> levelList) {
        // 1、设置返回结果集
        List<CategoryTreeResp> levelMenus = new ArrayList<>();

        // 2、创建UUID给锁设置唯一的值，避免锁的时间是10秒，业务执行30秒，业务最后删了别人的锁
        String uuid = UUID.randomUUID().toString();

        // 2、占分布式锁，去redis占坑
        //（3）、加锁：解决缓存击穿
        // (4)、设置过期时间，否则如果在业务中出现异常，不往下走，没有及时删除锁，会出现死锁，上锁和设置过期时间需要保持原子性
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        // 加锁成功：执行业务，加锁失败：重试加锁
        if (lock) {
            // 设置过期时间，否则如果在业务中出现异常，不往下走，没有及时删除锁，会出现死锁
            //redisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            // 3、加入缓存逻辑，缓存中存的数据是json字符串
            String redisStr = redisTemplate.opsForValue().get("CategoryListWithTree" + levleStr); // JSON跨语言，跨平台兼容
            // 判断Redis中是否有缓存数据，没有：查询数据库，有：直接从Redis中获取
            if (StringUtils.isBlank(redisStr)) {
                // 3、执行查询业务

                // 4、查询到的数据放入缓存，将对象转为JSON放在缓存中
                //（2）、设置过期时间（加随机值）：解决缓存雪崩
                redisTemplate.opsForValue().set("CategoryListWithTree" + levleStr, JacksonUtils.toJson(levelMenus), 1, TimeUnit.DAYS);
            } else {
                levelMenus = JacksonUtils.toList(redisStr, CategoryTreeResp.class);
            }

            String lockValue = redisTemplate.opsForValue().get("lock");
            if (uuid.equals(lockValue)) {
                // 业务逻辑执行完之后需要删除锁，否则会出现死锁的问题
                redisTemplate.delete("lock");
            }

        } else {
            // 加锁失败，重试。synchronized()
            // 休眠100ms重试
            return queryCategoryListWithTreeWithRedis(levleStr, levelList); // 自旋的方式
        }
        // 5、返回数据
        return levelMenus;
    }

    /**
     * （1）、空结果缓存：解决缓存穿透
     * （2）、设置过期时间（加随机值）：解决缓存雪崩
     * （3）、加锁：解决缓存击穿
     */
    private List<CategoryTreeResp> queryCategoryListWithTreeWithSynchronized(String levleStr, List<Integer> levelList) {
        // 1、设置返回结果集
        List<CategoryTreeResp> levelMenus = new ArrayList<>();
        // 2、加入缓存逻辑，缓存中存的数据是json字符串
        String redisStr = redisTemplate.opsForValue().get("CategoryListWithTree" + levleStr); // JSON跨语言，跨平台兼容

        // 判断Redis中是否有缓存数据，没有：查询数据库，有：直接从Redis中获取
        if (StringUtils.isBlank(redisStr)) {
            //（3）、加锁：解决缓存击穿
            synchronized (this) {
                // 拿到锁再进行判断一次，可能上一个线程刚释放锁，发送给redis，redis还没来得及处理
                String redisStr2 = redisTemplate.opsForValue().get("CategoryListWithTree" + levleStr);
                if (StringUtils.isBlank(redisStr2)) {
                    // 3、执行查询业务

                    // 4、查询到的数据放入缓存，将对象转为JSON放在缓存中
                    //（2）、设置过期时间（加随机值）：解决缓存雪崩
                    redisTemplate.opsForValue().set("CategoryListWithTree" + levleStr, JacksonUtils.toJson(levelMenus), 1, TimeUnit.DAYS);
                } else {
                    levelMenus = JacksonUtils.toList(redisStr2, CategoryTreeResp.class);
                }
            }
        } else {
            levelMenus = JacksonUtils.toList(redisStr, CategoryTreeResp.class);
        }
        // 5、返回数据
        return levelMenus;
    }

    /**
     * 添加 分类信息
     *
     * @param req 分类信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @Override
    public boolean saveCategory(CategoryAddReq req) {
        // 1、判断是否为重复添加(名字一样、父级一样)
        Category result = categoryDao.queryInfoByCategoryNameAndParentCode(req.getCategoryName(), req.getParentCode());
        // 2、判断是否为重复添加数据
        if (result != null) {
            throw new BusinessException(ResultCode.DATA_IS_EXISTED.code(), ResultCode.DATA_IS_EXISTED.message());
        }
        // 3、新建分类对象
        Category category = new Category();
        BeanUtils.copyProperties(req, category);
        category.setCategoryCode(GenerateCodeUtils.getCode(ProductInitCodeEnum.PMS_CATEGORY_INIT_CODE.getDesc()));
        // 4、保存分类对象
        return categoryDao.save(category);
    }

    /**
     * 删除 根据分类Codes删除分类信息
     *
     * @param req 分类Codes列表
     * @return 删除是否成功: true-成功, false-失败
     */
    @Override
    public boolean removeCategory(CategoryDelReq req) {
        // 1、TODO 拓展：检查当前删除的分类, 是否被别的地方引用，例如品牌和分组的关联关系
        // 2、逻辑删除
        return categoryDao.deleteByCategoryCodes(req.getCategoryCodes());
    }

    /**
     * 修改 分类信息
     *
     * @param req 分类信息
     * @return 修改是否成功: true-成功, false-失败
     */
    @Transactional
    @Override
    public boolean modifyCategory(CategoryEditReq req) {
        // 1、查询校验分类是否合法
        Category categoryOld = categoryDao.queryInfoByCategoryCode(req.getCategoryCode());
        if (categoryOld != null) {
            Category categoryNew = new Category();
            BeanUtils.copyProperties(req, categoryNew);
            categoryNew.setId(categoryOld.getId());
            // 2、更新分类对象数据
            categoryDao.updateById(categoryNew);
            // 3、同步更新数据表brand_category_relation, 数据表冗余存储了名称字段
            categoryBrandRelationDao.updateCategoryNameByCategoryCode(categoryNew.getCategoryCode(), categoryNew.getCategoryName());
            // 4、返回数据
            return true;
        }
        return false;
    }

    /**
     * 修改 分类排序
     *
     * @param reqs 分类信息列表
     * @return 修改是否成功: true-成功, false-失败
     */
    @Override
    public boolean modifyCategorySort(List<CategorySortReq> reqs) {
        // 1、查询出全部的分类列表
        Map<Long, Long> categoryCodeAndIdMap = categoryDao.list()
                .stream()
                .collect(Collectors.toMap(Category::getCategoryCode, Category::getId));
        // 2、将req对象拷贝成需要添加的对象
        List<Category> categories = reqs.stream()
                .map(categorySortReq -> {
                    Category category = new Category();
                    BeanUtils.copyProperties(categorySortReq, category);
                    category.setId(categoryCodeAndIdMap.get(categorySortReq.getCategoryCode()));
                    return category;
                })
                .collect(Collectors.toList());
        // 3. 执行批量添加对象的方法
        return categoryDao.updateBatchById(categories);
    }


    /**
     * 查询 根据分类Code查询分类信息
     *
     * @param categoryCode 分类Code
     * @return 分类信息
     */
    @Override
    public CategoryInfoResp findCategoryByCode(Long categoryCode) {
        // 1、判断数据是否为空
        if (EmptyUtils.isNull(categoryCode)) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), ResultCode.PARAM_IS_ERROR.message());
        }
        // 2、根据分类Code查询分类
        Category category = categoryDao.queryInfoByCategoryCode(categoryCode);
        // 3、返回数据
        if (category != null) {
            CategoryInfoResp categoryInfoResp = new CategoryInfoResp();
            BeanUtils.copyProperties(category, categoryInfoResp);
            return categoryInfoResp;
        } else {
            throw new BusinessException(ResultCode.DATA_NOT_EXISTED.code(), ResultCode.DATA_NOT_EXISTED.message());
        }
    }

    /**
     * 查询 分类完整路径；[父/子/孙] [2, 25, 225]
     *
     * @param categoryCode 分类Code
     * @return 类全层次路径 [父/子/孙] [2, 25, 225]
     */
    @Override
    public Long[] findCategoryPath(Long categoryCode) {
        // 1、定义一个集合存储数据
        List<Long> paths = new ArrayList<>();
        // 2、递归查询出所有的父类
        List<Long> parentPath = findParentPath(categoryCode, paths);
        // 3、逆序排序
        Collections.reverse(parentPath);
        // 4、返回数据
        return parentPath.toArray(new Long[0]);
    }

    /**
     * 递归查找所有分类的父分类
     * 返回的结果：[孙/子/父] [225, 25, 2]
     *
     * @param categoryCode 分类iCode
     * @param paths        路径集合
     * @return [孙/子/父] [225, 25, 2]
     */
    private List<Long> findParentPath(Long categoryCode, List<Long> paths) {
        // 1、收集当前节点Code
        paths.add(categoryCode);
        // 2、判断是否为有效数据
        Category category = lambdaQuery()
                .eq(Category::getCategoryCode, categoryCode)
                .one();
        if (category.getParentCode() != 0) {
            // 3、递归查询
            findParentPath(category.getParentCode(), paths);
        }
        // 4、返回数据
        return paths;
    }

    /**
     * 递归查找所有分类的子分类
     * BUG-1: 方法中的getChildrens()有错误, Long类型不能用==判断, 改为如下就可以了
     * category.getParentCode().longValue() == root.getCategoryCode().longValue();
     * 或者直接使用equals进行判断也可以
     *
     * @param root              当前分类
     * @param categoryTreeResps 分类汇总
     * @return 分类及其子类
     */
    private List<CategoryTreeResp> getChildrens(CategoryTreeResp root, List<CategoryTreeResp> categoryTreeResps) {
        List<CategoryTreeResp> children = categoryTreeResps.stream()
                .filter(treeVo -> root.getCategoryCode().equals(treeVo.getParentCode()))
                .map(treeVo -> {
                    // 1、找到子分类
                    treeVo.setChildren(getChildrens(treeVo, categoryTreeResps));
                    return treeVo;
                })
                .sorted((menu1, menu2) -> {
                    // 2、分类的排序, 判断防止空指针异常
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        // 3、返回数据
        return children;
    }

}
