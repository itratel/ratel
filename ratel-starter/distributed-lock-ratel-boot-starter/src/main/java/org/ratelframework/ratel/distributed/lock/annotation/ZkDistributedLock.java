package org.ratelframework.ratel.distributed.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>ZookeeperLock<p/>
 * @author whd.java@gmail.com
 * @date 2020/6/17 15:49
 * @since 0.0.1
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZkDistributedLock {

    /**
     * 分布式锁的键
     */
    String key() default "";

    /**
     * 锁释放时间，默认2秒
     */
    long timeout() default 2000;

    /**
     * 时间格式，默认：毫秒
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
