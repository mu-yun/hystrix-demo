package com.muyun.hystrixdemo.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

/**
 * @author muyun
 * @date 2021/2/1
 */
public class FeignErrorDecoder extends ErrorDecoder.Default {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            // 404 将不会触发fallback，也不会触发断路器打开
            return new HystrixIgnoreException("404");
        }
        return super.decode(methodKey, response);
    }
}
