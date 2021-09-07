package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationExpcetion;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {

        return productCategoryDao.quaryProductCategory(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationExpcetion {
        if (productCategoryList != null&& productCategoryList.size() > 0){
            try {
                int effecteNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if(effecteNum <= 0){
                    throw new ProductCategoryOperationExpcetion("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationExpcetion("batchAddProductCategory error" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }

    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationExpcetion {
        try {
            int effectNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectNum <= 0){
                throw new ProductCategoryOperationExpcetion("商品类别更新失败");
            }
        } catch (Exception e) {
            throw  new ProductCategoryOperationExpcetion("deleteProductCategory error:" + e.toString());
        }
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effectedNum <= 0){
                throw new ProductCategoryOperationExpcetion("商品类别删除失败");

            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationExpcetion("deleteProductCategory error:" + e.getMessage());
        }
    }
}
