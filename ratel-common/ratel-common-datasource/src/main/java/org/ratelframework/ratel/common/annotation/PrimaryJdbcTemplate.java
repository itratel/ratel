package org.ratelframework.ratel.common.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicator for the bean that stands for primary JdbcTemplate
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
