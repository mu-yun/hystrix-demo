# Hystrix Demo

### 使用方法

1. 在`application.yml`配置hystrix参数
2. 启动项目，默认端口是8080
3. 请求`http://localhost:8080/client/timeout`，测试feign client接口**超时情况下**hystrix的熔断情况
4. 请求`http://localhost:8080/client/{status}`，测试feign client接口**返回不同http状态码时**hystrix的熔断情况