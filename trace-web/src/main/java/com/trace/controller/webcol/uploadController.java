package com.trace.controller.webcol;

import com.trace.service.entity.recentity.WoRec;
import com.trace.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xzp
 * Created on 2021/3/31
 */
@RestController
@RequestMapping("/webapi")
public class uploadController {

    @PostMapping(value = "/imgpload" )
    public Result uploadImage( @RequestParam("file") MultipartFile file,WoRec rec) {
        System.out.println(file);
        System.out.println(rec);
        return Result.success();
    }
}
