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

    public static StatusEnum covertInt(int status) {
        switch (status) {
            case 1:
                return StatusEnum.UNREAD_MEUNHANDLE;
            case 2:
                return StatusEnum.UNHANDLE;
            case 0:
                return StatusEnum.READED_HANDLE;
        }
        return StatusEnum.UNHANDLE;
    }
}
