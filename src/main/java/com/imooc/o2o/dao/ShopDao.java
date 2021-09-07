package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 查询店铺列表并分页展示。可输入条件：店铺名称（模糊），店铺状态，店铺类别，区域id，owner
     * @param shopCondition
     * @param rowIndex 从第几行开始去数据
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 查询店铺总数
     * @param shopCondition
     * @return
     */
     int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 新增店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 根据chopId查询店铺信息
     *
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);

    /**
     * 更新店铺
     *
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
