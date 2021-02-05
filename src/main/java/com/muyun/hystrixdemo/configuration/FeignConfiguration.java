package com.muyun.hystrixdemo.configuration;

import com.muyun.hystrixdemo.client.FeignErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muyun
 * @date 2021/1/30
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public ErrorDecoder feignDecoder() {
        return new FeignErrorDecoder();
    }

}
