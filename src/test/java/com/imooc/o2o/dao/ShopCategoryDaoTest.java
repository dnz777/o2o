package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(23,shopCategoryList.size());

        ShopCategory testShopCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();

        parentCategory.setShopCategoryId(10L);
        testShopCategory.setParent(parentCategory);

        List<ShopCategory> shopCategoryList1 = shopCategoryDao.queryShopCategory(testShopCategory);
        assertEquals(2,shopCategoryList1.size());
        System.out.println(shopCategoryList1.get(0).getShopCategoryName());

    }
}
