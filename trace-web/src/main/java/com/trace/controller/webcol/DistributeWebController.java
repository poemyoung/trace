package com.trace.controller.webcol;

import com.trace.api.addrpentity.Position;
import com.trace.service.user.DistributeService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/19
 */
@RestController
@RequestMapping("/webapi")
public class DistributeWebController {
    private final Logger LOGGER = LoggerFactory.getLogger(DistributeWebController.class);

    @Autowired
    DistributeService service;

    @GetMapping("/dstb")
    public Result getAddressByDay(@RequestParam String date) {
        String[] strs = date.split("-");
        if(strs.length != 3) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        Calendar c = Calendar.getInstance();
        try {
            int y = Integer.parseInt(strs[0]);
            c.set(Calendar.YEAR,y);
            int m = Integer.parseInt(strs[1]);
            c.set(Calendar.MONTH,m);
            int d = Integer.parseInt(strs[2]);
            c.set(Calendar.DAY_OF_MONTH,d);
        }catch (Exception e) {
            LOGGER.error("日期参数解析或设置有误");
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        List<Position> positionsByDay = service.getPositionsByDay(c);
        return Result.success(positionsByDay);
    }


}
