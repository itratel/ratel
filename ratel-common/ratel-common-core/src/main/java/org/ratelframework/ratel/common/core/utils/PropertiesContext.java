package org.ratelframework.ratel.common.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2019/10/10 11:17
 * @apiNote 用户属性松散绑定为对象和其他数据结构
 * @since 0.0.1
 */
@Slf4j
@Component
public class PropertiesContext implements EnvironmentAware {

    private static Environment environment = null;

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        PropertiesContext.environment = environment;
    }

    /***
     * 返回环境变量
     * @return {@link Environment}
     */
    public static Environment getEnvironment() {
        return environment;
    }

    /***
     * 获取属性绑定器
     * @return {@link Binder}
     */
    public static Binder getBinder() {
        if (Objects.isNull(environment)) {
            throw new RuntimeException("环境变量为空");
        }
        return Binder.get(environment);
    }

    /***
     * 获取属性绑定的对象
     * @param proPrefix 属性前缀
     * @param clazz 类属性
     * @param <T> 参数
     * @return T
     */
    public static <T> T getProEntity(String proPrefix, Class<T> clazz) {
        return getBinder().bind(proPrefix, Bindable.of(clazz)).orElseCreate(clazz);
    }

    /***
     * 获取属性List
     * @param proPrefix 属性list
     * @param clazz 类属性
     * @param <T> 泛型参数
     * @return T
     */
    public static <T> List<T> getProEntityList(String proPrefix, Class<T> clazz) {
        return getBinder().bind(proPrefix, Bindable.listOf(clazz)).orElse(Collections.emptyList());
    }
}
