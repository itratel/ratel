package org.ratelframework.ratel.multidatasource.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>PrimaryJdbcTemplate<p/>
 * Indicator for the bean that stands for primary JdbcTemplate
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(PrimaryJdbcTemplate.NAME)
public @interface PrimaryJdbcTemplate {
    /**
     * The qualifier name, note that this name is dedicated picked the same as the spring default one so that
     * we can override that.
     */
    String NAME = "primaryJdbcTemplate";
}
