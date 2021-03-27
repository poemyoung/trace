package com.trace.service.converter;

import com.trace.service.entity.commentity.WhomEnum;

/**
 * @author xzp
 * Created on 2021/3/22
 */
public class  WhomConverter {
    public static boolean convertWhom(WhomEnum who) {
        switch (who) {
            case USER:
                return true;
            case ADMIN:
                return false;
        }
        return true;
    }
}
