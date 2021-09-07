package com.imooc.o2o.util;

import com.imooc.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 将CommonsMultipartFile转换成File
     *
     * @param cfile
     * @return
     */
    private static File transferCommonsMultipartFileToFile(CommonsMultipartFile cfile) {
        File newFile = new File(cfile.getOriginalFilename());
        try {
            cfile.transferTo(newFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            logger.error(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return newFile;
    }

    /**
     * 处理缩略图，并返回新生成图片的相对路径
     *
     * @param thumbnailInputStream
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        //获取文件后缀名
        String extension = getFileExtension(thumbnail.getImageName());
        //创建文件存储文件夹
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativAddr is:" + relativeAddr);
        logger.debug(basePath + "/waterMark.jpg");
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(800, 800)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "waterMark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;

    }

    /**
     * 创建目标路径所涉及到的目录 例如：/home/dnz/xxx.jpg 那么home dnz 文件夹自动创建
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists())
            dirPath.mkdirs();
    }

    /**
     * 获取文件输入流的扩展名
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日＋五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        int randomNum = random.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + randomNum;
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("D:\\javaCode\\image\\ymhd.jpg"))
                .size(800, 800).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\waterMark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("D:\\javaCode\\image\\addWaterMark.jpg");
    }

    /**
     * 输出文件或目录，先判断入参storePath是文件路径还是目录，如果是文件路径则直接删除，如果是目录则删除该目录下所有文件
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File file[] = fileOrPath.listFiles();
                for(int i = 0; i < file.length; i++){
                    file[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
        // 获取不重复的随机名
        String realFileName = getRandomFileName();
        // 获取文件的扩展名如png,jpg等
        String extension = getFileExtension(thumbnail.getImageName());
        // 如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        // 获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is :" + relativeAddr);
        // 获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("创建缩图片失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }
}
