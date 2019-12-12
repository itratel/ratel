package org.ratelframework.ratel.order.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author whd.java@gmail.com
 * @date 2019/12/12 23:59
 * @apiNote Describe the function of this class in one sentence
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EnableRatelImportSelector.class)
public @interface EnableRatel {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
