package com.trace.controller;

import com.trace.dao.entity.Popularize;
import com.trace.service.article.PopService;
import com.trace.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/8
 */
@RestController
@RequestMapping("/miniapi")
public class PopController {

    @Autowired
    PopService service;

    @GetMapping("/popall")
    public Result getAllPops() {
        List<Popularize> all = service.getAll();
        return Result.success(all);
    }
}
