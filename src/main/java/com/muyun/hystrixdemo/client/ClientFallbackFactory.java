package com.muyun.hystrixdemo.client;

import com.muyun.hystrixdemo.util.HystrixUtil;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author muyun
 * @date 2021/1/30
 */
@Slf4j
@Component
public class ClientFallbackFactory implements FallbackFactory<Client> {


    @Override
    public Client create(Throwable cause) {
        return new Client() {
            @Override
            public String health(Integer status) {
                return handleException(Client.HEALTH_METHOD_KEY);
            }

            @Override
            public String timeout() {
                return handleException(Client.TIMEOUT_METHOD_KEY);
            }

            private String handleException(String methodKey) {
                cause.printStackTrace();

                String hystrixStatus = getHystrixStatus(methodKey);

                return "fallback" + "<br>"
                        + "hystrix status: " + hystrixStatus + "<br>"
                        + "exception: " + cause.getClass().getName() + "<br>"
                        + cause.getMessage() + "<br>";
            }

        };

    }

    private String getHystrixStatus(String methodKey) {
        String hystrixStatus = HystrixUtil.circuitBreakerIsOpen(methodKey) ? "open" : "close";
        return hystrixStatus;
    }

}
