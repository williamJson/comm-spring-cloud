package com.jiangxing.user.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wyq on 2020/1/8.
 *
 * @auther wyq
 * qq:342622023
 */
@FeignClient(value = "order-center",fallback = FallBackRemoteApiImpl.class)
public interface RemoteApi {


    @RequestMapping(value = "hi")
    String hi(@RequestParam(name="msg") String msg);
}
