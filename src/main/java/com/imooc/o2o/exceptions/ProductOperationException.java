package com.imooc.o2o.exceptions;

public class ProductOperationException extends RuntimeException{

    private static final long serialVersionUID = -6414885575537564678L;

    public ProductOperationException(String message) {
        super(message);
    }
}
