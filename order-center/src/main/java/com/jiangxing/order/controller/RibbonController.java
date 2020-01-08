package com.jiangxing.order.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {


    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hi")
    public String hello(String msg) {
        return msg + serverPort;
    }
}
