package org.ratelframework.ratel.common.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicator for the bean that stands for Sub MyBatis SqlSession
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(SubSqlSessionTemplate.NAME)
public @interface SubSqlSessionTemplate {
    /**
     * The qualifier name.
     */
    String NAME = "subSqlSessionTemplate";
}