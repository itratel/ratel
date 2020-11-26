package org.ratelframework.ratel.distributed.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>RedisDistributedLock<p/>
 *
 * @author whd.java@gmail.com
 * @date 2020/6/19 10:28
 * @since 1.0.0
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisDistributedLock {

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
