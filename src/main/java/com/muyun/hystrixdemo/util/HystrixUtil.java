package com.muyun.hystrixdemo.util;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author muyun
 * @date 2021/1/30
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HystrixUtil {

    public static boolean circuitBreakerIsOpen(String methodKey) {
        HystrixCircuitBreaker circuitBreaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory.asKey(methodKey));
        return circuitBreaker != null && circuitBreaker.isOpen();
    }
}
