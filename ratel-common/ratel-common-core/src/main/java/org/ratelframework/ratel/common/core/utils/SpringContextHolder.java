package org.ratelframework.ratel.common.core.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 18:34
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;


    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /***
     * 上下文
     * @param applicationContext 上下文
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringPropertiesContext中的ApplicationContext为Null.
     */
    public static void clear() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContext中的ApplicationContext: {}", applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 发布事件
     * @param event 事件
     */
    public static void publishEvent(ApplicationEvent event) {
        Assert.notNull(applicationContext, "上下文为空");
        applicationContext.publishEvent(event);
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    @SneakyThrows
    public void destroy() {
        clear();
    }
}
