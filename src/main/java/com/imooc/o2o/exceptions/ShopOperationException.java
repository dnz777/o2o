package com.imooc.o2o.exceptions;

public class ShopOperationException extends RuntimeException {
    //对店铺进行操作时每有一处失败都将事务进行回滚，所以抛出RuntimeException
    private static final long serialVersionUID = 2250111855566052158L;

    public ShopOperationException(String message) {
        super(message);
    }
}
