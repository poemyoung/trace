package com.trace.controller;

import com.trace.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@RestController
@RequestMapping("/miniapi")
public class ColdChainController {

    @GetMapping("/upchain")
    public Result upChain() {

        return null;
    }
}
