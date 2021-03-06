package com.imooc.o2o.controller.shopadmin;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.dto.Result;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationExpcetion;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( value = "/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        //TO BE REMOVED
//        Shop shop = new Shop();
//        shop.setShopId(29L);
//        request.getSession().setAttribute("currentShop",shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        if (currentShop != null && currentShop.getShopId() > 0 ){
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,list);
        }else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
        }

    }
    @RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory:productCategoryList){
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0){
            try {
                ProductCategoryExecution productCategoryExecution = productCategoryService.batchAddProductCategory(productCategoryList);
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);

                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",productCategoryExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","?????????????????????????????????");
        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> removeProductCategory(Long productCategoryId,HttpServletRequest request){
        Map<String,Object> moddelMap = new HashMap<String,Object>();
        if (productCategoryId != null && productCategoryId > 0){

            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,currentShop.getShopId());
                if (pe.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    moddelMap.put("success",true);
                }else {
                    moddelMap.put("success",false);
                    moddelMap.put("errMsg",pe.getStateInfo());
                }
            } catch (ProductCategoryOperationExpcetion e) {
                moddelMap.put("success",false);
                moddelMap.put("errMsg",e.toString());
                return moddelMap;
            }
        }else {
            moddelMap.put("success",false);
        }
    return moddelMap;
    }
}
