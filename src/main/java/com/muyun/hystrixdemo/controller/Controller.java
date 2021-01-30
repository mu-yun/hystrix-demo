package com.muyun.hystrixdemo.controller;

import com.muyun.hystrixdemo.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author muyun
 * @date 2021/1/30
 */
@RestController
@RequiredArgsConstructor
public class Controller {

    private final Client client;

    @Value("${hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds}")
    private Long timeout;

    @GetMapping("client/timeout")
    public String hystrixClientTimeout() {
        return client.timeout();
    }

    @GetMapping("client/{status}")
    public String hystrixClientHealth(@PathVariable Integer status) {
        return client.health(status);
    }

    //------------------------mock feign client service------------------------------------
    @GetMapping("health/{status}")
    public ResponseEntity<String> health(@PathVariable Integer status) throws InterruptedException {
        if (status == HttpStatus.GATEWAY_TIMEOUT.value()) {
            Thread.sleep(timeout);
        }
        return ResponseEntity.status(status).body(status.toString());
    }

    @GetMapping("timeout")
    public ResponseEntity<String> timeout() throws InterruptedException {
        Thread.sleep(timeout);
        return ResponseEntity.ok("timeout");
    }

}
