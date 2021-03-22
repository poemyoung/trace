package com.trace.service.converter;

import com.trace.service.entity.commentity.ImagePosEnum;

/**
 * @author xzp
 * Created on 2021/3/22
 */
public class ImagePosConverter {
    public static boolean convertImagePos(ImagePosEnum imagePos) {
        switch (imagePos) {
            case WEAPP:
                return true;
            case SERVER:
                return false;
        }
        return true;
    }
}
