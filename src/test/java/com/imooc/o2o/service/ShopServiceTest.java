package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStatesEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的名称");
        File shopImg = new File("C:\\Users\\Admin\\Desktop\\joker.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder("joker.jpg",inputStream);
        ShopExecution shopExecution = shopService.modifyShop(shop,imageHolder);
        System.out.println("新的图片地址："+ shopExecution.getShop().getShopImg());
    }
    @Test
    public void testShopAddshop() throws FileNotFoundException {
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
        shop.setShopName("测试店铺名称2");
        shop.setShopDesc("测试desc2");
        shop.setShopAddr("测试地址2");
        shop.setPriority(10);
        shop.setPhone("18111199999");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStatesEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("D:\\javaCode\\image\\ymhd.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(),inputStream);
        ShopExecution shopExecution = shopService.addShop(shop,imageHolder);
        assertEquals(ShopStatesEnum.CHECK.getState(), shopExecution.getState());
    }

    @Test
    public void testGetShopList(){
        Shop shopContidion = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(22L);
        shopContidion.setShopCategory(shopCategory);
        ShopExecution shopExecution = shopService.getShopList(shopContidion,2,3);
        System.out.println(shopExecution.getShopList().size());
        System.out.println(shopExecution.getCount());
    }
}
