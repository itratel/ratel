package org.ratelframework.ratel.multidatasource.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author whd.java@gmail.com
 * @date 2020/06/17 15:03
 * @apiNote Annotation to mark a DataSource bean to be configured to use a first choose.
 * @since 1.0.0
 */
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(CandidateDataSource.NAME)
public @interface CandidateDataSource {

    String NAME = "candidateDataSource";
}