package org.ratelframework.ratel.distributed.lock.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>ZookeeperProperties<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/6/17 18:14
 * @since 0.0.1
 */
@Data
@ConfigurationProperties(prefix = "ratel.zookeeper")
public class ZookeeperProperties {

    /**
     * 连接地址
     */
    private String url;

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;

}
