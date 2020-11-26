package org.ratelframework.ratel.common.security.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>EnableComponentScanAutoConfiguration<p/>
 * EnableComponentScanAutoConfiguration <br>
 * 用来开启扫描的配置
 * @author whd.java@gmail.com
 * @date 2020/11/26 18:00
 * @since 1.0.0
 */
@Configuration
@ComponentScan(basePackages = "org.ratelframework.ratel.common.security")
public class EnableComponentScanAutoConfiguration {
}
