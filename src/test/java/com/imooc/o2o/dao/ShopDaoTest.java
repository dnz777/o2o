package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    @Ignore
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owern = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owern.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(owern);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺名称");
        shop.setShopImg("测试img");
        shop.setShopDesc("测试desc");
        shop.setShopAddr("测试地址");
        shop.setPriority(10);
        shop.setPhone("18111199999");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShopTest() {
        Shop shop = new Shop();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);

        shop.setShopId(1L);
        shop.setShopCategory(shopCategory);
        shop.setShopName("修改测试店铺名称");
        shop.setShopImg("修改测试img");
        shop.setShopDesc("修改测试desc");
        shop.setShopAddr("修改地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);

    }

    @Test
    public void testQueryByShopId() {
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getShopCategory().getShopCategoryName());

    }

    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);

        List<Shop> shopList = new ArrayList<Shop>();
        shopList = shopDao.queryShopList(shopCondition, 0, 5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小：" + shopList.size());
        System.out.println("店铺总数：" + count);

        shopCategory.setShopCategoryId(22L);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList2= shopDao.queryShopList(shopCondition,0,2);
        shopList2 = shopDao.queryShopList(shopCondition, 0, 2);
        int count2 = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表大小：" + shopList2.size());
        System.out.println("店铺总数：" + count2);
    }

}
