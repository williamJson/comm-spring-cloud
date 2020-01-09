package com.jiangxing.user.controller;


import com.jiangxing.user.feignApi.RemoteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRibbonCtl {


//    @Autowired
//    private RestTemplate template;

//    @RequestMapping("/sayHi")
//    public String sayHi() {
//        return template.getForObject("http://order-center/hi", String.class, "i am come from user--->");
//    }


    @Autowired
    RemoteApi remoteApi;

    @RequestMapping("/sayHi")
    public String sayHi() {
        return remoteApi.hi("I am from user-center");
    }
}
