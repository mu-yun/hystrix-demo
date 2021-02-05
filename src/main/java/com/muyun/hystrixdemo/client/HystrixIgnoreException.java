package com.muyun.hystrixdemo.client;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * 继承HystrixBadRequestException的异常不会触发断路器从关闭到打开，但是断路器在半开状态下会触发断路器打开
 *
 * @author muyun
 * @date 2021/2/1
 */
public class HystrixIgnoreException extends HystrixBadRequestException {
    public HystrixIgnoreException(String message) {
        super(message);
    }
}
