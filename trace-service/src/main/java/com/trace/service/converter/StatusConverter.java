package com.trace.service.converter;

import com.trace.service.entity.commentity.StatusEnum;

/**
 * @author xzp
 * Created on 2021/3/22
 */
public class StatusConverter {
    public static int convertStatus(StatusEnum status) {
        switch (status) {
            case UNREAD_MEUNHANDLE:
                return 1;
            case UNHANDLE:
                return 2;
            case READED_HANDLE:
                return 0;
        }
        return 0;
    }
}
