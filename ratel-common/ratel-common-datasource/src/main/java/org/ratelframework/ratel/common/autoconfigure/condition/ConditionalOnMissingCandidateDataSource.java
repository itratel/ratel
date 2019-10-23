package org.ratelframework.ratel.common.autoconfigure.condition;

import org.ratelframework.ratel.common.annotation.CandidateDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnMissingBean(name = CandidateDataSource.NAME)
public @interface ConditionalOnMissingCandidateDataSource {

}
