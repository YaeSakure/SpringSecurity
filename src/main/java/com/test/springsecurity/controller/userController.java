package com.test.springsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class userController {

    @GetMapping("/login")
    public String Login(){
        return "登录成功";
    }

}
