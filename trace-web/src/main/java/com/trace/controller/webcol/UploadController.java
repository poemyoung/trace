package com.trace.controller.webcol;

import com.trace.service.article.ArticleImageService;
import com.trace.service.entity.recentity.WoRec;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xzp
 * Created on 2021/3/31
 */
@RestController
@RequestMapping("/webapi")
public class UploadController {

    @Autowired
    ArticleImageService service;

    @PostMapping(value = "/imgpload" )
    public Result uploadImage( @RequestParam("file") MultipartFile file,WoRec rec) {
        if(file == null || rec == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        return service.storeImage(file,rec) ? Result.success() : Result.fail(ResultCode.WORKORDER_NOT_EXIST);
    }
}
