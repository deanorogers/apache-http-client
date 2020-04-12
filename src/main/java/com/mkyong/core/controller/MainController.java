package com.mkyong.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @ResponseBody
    @GetMapping("/")
    public String hello() throws InterruptedException {
        Thread.sleep(7000);
        return "Hello Controller";
    }

}
