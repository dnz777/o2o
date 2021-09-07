package com.imooc.o2o.util;

public class PathUtil {
    //获取系统的文件夹分隔符
    private static String separator = System.getProperty("file.separator");
    //存储文件的基础文件夹
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath="D:/web/image/";
        }else {
            basePath="/home/web/image/";
        }
        return basePath=basePath.replace("/",separator);

    }

    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",separator);
    }

}
