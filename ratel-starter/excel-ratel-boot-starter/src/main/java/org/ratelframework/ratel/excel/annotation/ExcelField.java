package org.ratelframework.ratel.excel.annotation;


import org.ratelframework.ratel.excel.core.formatter.ReturnValueFormatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /***
     * alias
     * @return {@link String}
     */
    String alias() default "";


    /***
     * whether ignore or not
     * @return {@link Boolean}
     */
    boolean ignore() default false;

    /***
     * formatter
     * @return the sub class of {@link Class<ReturnValueFormatter>}
     */
    Class<? extends ReturnValueFormatter> formatter() default ReturnValueFormatter.class;
}
