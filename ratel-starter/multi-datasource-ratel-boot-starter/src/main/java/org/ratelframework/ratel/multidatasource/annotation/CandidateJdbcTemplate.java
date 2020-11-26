package org.ratelframework.ratel.multidatasource.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>CandidateJdbcTemplate<p/>
 * Indicator for the bean that stands for Detailed data warehouse JdbcTemplate
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(CandidateJdbcTemplate.NAME)
public @interface CandidateJdbcTemplate {

    /**
     * The qualifier name
     */
    String NAME = "candidateJdbcTemplate";

}
