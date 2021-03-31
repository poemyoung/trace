package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author xzp
 * Created on 2021/3/26
 */
// unfinished
public class ImageUtil {
    private static final String BASE_PATH = "/Users/xzp/Desktop/upload";
    private static final String ADMIN_IMG_PATH = "/Users/xzp/Desktop/upload/admin";
    private static SnowflakeIdUtil snow = new SnowflakeIdUtil();
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);

    public static String imageStore(MultipartFile file) {
        if(file == null) {
            return "";
        }
        CreateFileUtil.createDir(ADMIN_IMG_PATH);
        String path = "/" + snow.nextId() + ".png";
        File f = new File(ADMIN_IMG_PATH + path);
        try {
            file.transferTo(f);
        }catch (Exception e) {
            LOGGER.error("图片转换失败" + e.getMessage());
            return "";
        }
        return "/admin/" + path;
    }
}
