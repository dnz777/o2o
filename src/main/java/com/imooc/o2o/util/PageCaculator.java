package com.imooc.o2o.util;

public class PageCaculator {
    /**
     *
     */

    public static int caculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
