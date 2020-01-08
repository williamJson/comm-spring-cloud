package com.jiangxing.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestRibbonCtl {


    @Autowired
    private RestTemplate template;

    @RequestMapping("/sayHi")
    public String sayHi() {
        return template.getForObject("http://order-center/hi", String.class, "i am come from user--->");
    }
}
