package org.ratelframework.ratel.common.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.annotation.*;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/22 15:03
 * @apiNote Annotation to mark a DataSource bean to be configured to use a first choose.
 * @since 0.0.1
 * @version 0.0.1
 */
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier(PrimaryDataSource.NAME)
public @interface PrimaryDataSource {

    /**
     * The qualifier name, note that this name is dedicated picked the same as the spring default one so that
     * we can override that.
     */
    String NAME = "primaryDataSource";
}