package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void deleteProductCategoryTest(){
        long productCategoryId = 19;
        long shopId = 28;
        productCategoryService.deleteProductCategory(productCategoryId,shopId);
        System.out.println(11111111);
    }
}
