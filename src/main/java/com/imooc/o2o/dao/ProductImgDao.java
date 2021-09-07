package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    List<ProductImg> queryProductImg(long productId);

    /**
     * 批量添加商品图片
     * @param productImgList
     * @return 影响行数
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 在更新商品之前要删除所有产品的缩略图
     * @param productId
     * @return 影响行数
     */
    int deleteProductImg(long productId);

    List<ProductImg> queryProductImgList(long productId);

     int deleteProductImgByProductId(long productId);
}
