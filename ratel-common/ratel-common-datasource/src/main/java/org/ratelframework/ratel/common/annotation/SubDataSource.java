package org.ratelframework.ratel.common.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

import static org.ratelframework.ratel.common.annotation.SubDataSource.NAME;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @apiNote Annotation to mark a DataSource bean to be configured to use a first choose.
 * @since 0.0.1
 * @version 0.0.1
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier(value = NAME)
public @interface SubDataSource {

    String NAME = "subDataSource";
}