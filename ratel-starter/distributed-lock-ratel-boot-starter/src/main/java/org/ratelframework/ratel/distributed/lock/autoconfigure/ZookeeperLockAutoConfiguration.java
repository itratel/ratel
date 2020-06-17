package org.ratelframework.ratel.distributed.lock.autoconfigure;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.ratelframework.ratel.distributed.lock.properties.ZookeeperProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <p>ZookeeperLockAutoConfiguration<p/>
 * @author whd.java@gmail.com
 * @date 2020/6/17 15:49
 * @since 0.0.1
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperLockAutoConfiguration {

    private final ZookeeperProperties zkProperties;

    @Autowired
    public ZookeeperLockAutoConfiguration(ZookeeperProperties zkProperties) {
        this.zkProperties = zkProperties;
    }

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProperties.getTimeout(), zkProperties.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkProperties.getUrl(), retryPolicy);
        client.start();
        return client;
    }
}
