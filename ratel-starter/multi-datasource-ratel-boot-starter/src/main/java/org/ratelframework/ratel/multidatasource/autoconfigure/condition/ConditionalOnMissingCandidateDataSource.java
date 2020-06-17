package org.ratelframework.ratel.multidatasource.autoconfigure.condition;

import org.ratelframework.ratel.multidatasource.annotation.CandidateDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.lang.annotation.*;

/**
 * <p>ConditionalOnMissingCandidateDataSource<p/>
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 0.0.1
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnMissingBean(name = CandidateDataSource.NAME)
public @interface ConditionalOnMissingCandidateDataSource {

}
