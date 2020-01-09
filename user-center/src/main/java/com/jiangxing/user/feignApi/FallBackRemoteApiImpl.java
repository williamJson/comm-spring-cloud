package com.jiangxing.user.feignApi;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wyq on 2020/1/8.
 *
 * @auther wyq
 * qq:342622023
 */
@Component
public class FallBackRemoteApiImpl implements RemoteApi {
    @Override
    public String hi(@RequestParam(name = "msg") String msg) {
        return "sorry network error";
    }
}
