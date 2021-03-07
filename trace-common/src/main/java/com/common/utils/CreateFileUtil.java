package com.common.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author xzp
 * Created on 2021/3/7
 */

public class CreateFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(CreateFileUtil.class);

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            logger.error("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            logger.error("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                logger.error("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                logger.error("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                logger.error("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            logger.error("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            logger.error("创建目录" + destDirName + "成功！");
            return true;
        } else {
            logger.error("创建目录" + destDirName + "失败！");
            return false;
        }
    }


    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try {
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("创建临时文件失败！" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!CreateFileUtil.createDir(dirName)) {
                    logger.error("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }
}
