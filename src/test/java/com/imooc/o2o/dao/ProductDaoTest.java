package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct() throws Exception{
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setShopId(1L);
        //初始化三个商品实例并添加进shopId为1的店铺里
        //同时商品类别也为1
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试DESC1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试DESC2");
        product2.setImgAddr("test2");
        product2.setPriority(1);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);

        Product product3 = new Product();
        product3.setProductName("test3");
        product3.setProductDesc("测试DESC3");
        product3.setImgAddr("test3");
        product3.setPriority(1);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);

        //判断是否添加成功
        int effectNum = productDao.insertProduct(product1);
        assertEquals(1,effectNum);
        effectNum = productDao.insertProduct(product2);
        assertEquals(1,effectNum);
        effectNum = productDao.insertProduct(product3);
        assertEquals(1,effectNum);

    }
}
