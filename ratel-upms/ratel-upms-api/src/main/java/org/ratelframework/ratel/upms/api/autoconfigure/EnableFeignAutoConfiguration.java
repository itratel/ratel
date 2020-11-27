package org.ratelframework.ratel.upms.api.autoconfigure;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * <p>EnableFeignAutoConfiguration<p/>
 * EnableFeignAutoConfiguration <br>
 * 自动开启扫描所有远程请求文件，这样子做的好处是不需要在调用方去开启远程调用配置，<br>
 * 一旦该工程的被依赖，就会自动开启扫描
 * @author whd.java@gmail.com
 * @date 2020/11/27 16:08
 * @since 1.0.0
 */
@Configuration
@EnableFeignClients(basePackages = "org.ratelframework.ratel.upms.api.feign")
public class EnableFeignAutoConfiguration {

}
