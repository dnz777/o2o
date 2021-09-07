package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStatesEnum;

import java.util.List;

public class ShopExecution {
    //状态
    private int state;
    //状态表示
    private String stateInfo;
    //店铺数量
    private int count;
    //操作的店铺（增删改查时用到）
    private Shop shop;
    //店铺列表（查询时用到）
    private List<Shop> shopList;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public ShopExecution(){

    }
    //操作失败时使用的构造器
    public ShopExecution(ShopStatesEnum statesEnum){
        this.state=statesEnum.getState();
        this.stateInfo=statesEnum.getStateInfo();
    }
    //操作成功时候使用的构造器，返回shop
    public ShopExecution(ShopStatesEnum statesEnum, Shop shop){
        this.state=statesEnum.getState();
        this.stateInfo=statesEnum.getStateInfo();
        this.shop=shop;

    }
    //操作成功时候使用的构造器，返回shop列表
    public ShopExecution(ShopStatesEnum statesEnum, List<Shop> shopList){
        this.state=statesEnum.getState();
        this.stateInfo=statesEnum.getStateInfo();
        this.shopList=shopList;
    }
}
