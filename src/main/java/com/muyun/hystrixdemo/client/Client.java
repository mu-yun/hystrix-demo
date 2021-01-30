package com.muyun.hystrixdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author muyun
 * @date 2021/1/30
 */
@FeignClient(name = "hystrix-service", url = "${feign.hystrix-service.url:}",
        fallbackFactory = ClientFallbackFactory.class)
public interface Client {

    String HEALTH_METHOD_KEY = "Client#health(Integer)";
    String TIMEOUT_METHOD_KEY = "Client#timeout()";


    @GetMapping("health/{status}")
    String health(@PathVariable Integer status);

    @GetMapping("timeout")
    String timeout();

}
