package org.ratelframework.ratel.common.autoconfigure.condition;

import org.ratelframework.ratel.common.annotation.CandidateDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnBean(name = CandidateDataSource.NAME)
public @interface ConditionalOnCandidateDataSource {

}
