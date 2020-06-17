package org.ratelframework.ratel.multidatasource.autoconfigure.condition;

import org.ratelframework.ratel.multidatasource.annotation.SubDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>ConditionalOnSubDataSource<p/>
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 0.0.1
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnBean(name = SubDataSource.NAME)
public @interface ConditionalOnSubDataSource {

}
