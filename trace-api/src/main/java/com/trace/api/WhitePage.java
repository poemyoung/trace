package com.trace.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhitePage {

    @GetMapping("/white")
    public String white(){
        return "white";
    }
}
