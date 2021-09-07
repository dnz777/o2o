package com.imooc.o2o.enums;

public enum ShopStatesEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCSEE(1,"操作成功"),PASS(2,"通过认证")
    ,INNER_ERROR(-1001,"内部错误"), NULL_SHOPID(-1002,"shopId为空"),NULL_SHOP(-1003,"shop信息为空");
    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ShopStatesEnum(int state, String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    public static ShopStatesEnum stateOf(int state ){
        for (ShopStatesEnum statesEnum:values()) {
            if(statesEnum.getState() == state){
                return statesEnum;
            }
        }return null;
    }


}
