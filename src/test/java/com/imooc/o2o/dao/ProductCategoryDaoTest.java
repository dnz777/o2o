package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testBQueryByShopId() throws Exception{
        long shopId = 29L;
        List<ProductCategory> productCategoryList = productCategoryDao.quaryProductCategory(shopId);

        System.out.println("该商店自定义的类别数为：" + productCategoryList.size());
    }

    @Test
    public void testABatchInsertProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("测试商品类别名称");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("测试商品类别名称2");
        productCategory2.setPriority(12);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);

        int effectdeNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
        assertEquals(2,effectdeNum);
    }

    @Test
    public void testDeleteProductCategoty() throws Exception{
        long productCategoryId = 28;
        long shopId = 4;
        int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
        System.out.println("删除商品类别返回值：" + effectedNum);
        assertEquals(1,effectedNum);
    }
}
