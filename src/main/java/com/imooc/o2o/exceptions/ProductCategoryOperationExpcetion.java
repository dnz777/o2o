package com.imooc.o2o.exceptions;

public class ProductCategoryOperationExpcetion extends RuntimeException {

    private static final long serialVersionUID = -1436286943197806985L;
    //对店铺进行操作时每有一处失败都将事务进行回滚，所以抛出RuntimeException


    public ProductCategoryOperationExpcetion(String message) {
        super(message);
    }
}
