package org.ratelframework.ratel.multidatasource.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <p>CandidateSqlSessionTemplate<p/>
 * Indicator for the bean that stands for Detailed MyBatis SqlSession
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 0.0.1
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(CandidateSqlSessionTemplate.NAME)
public @interface CandidateSqlSessionTemplate {
    /**
     * The qualifier name.
     */
    String NAME = "candidateSqlSessionTemplate";
}
