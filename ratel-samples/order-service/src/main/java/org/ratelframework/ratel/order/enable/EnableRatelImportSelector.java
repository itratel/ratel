package org.ratelframework.ratel.order.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/12/13 00:01
 * @apiNote Describe the function of this class in one sentence
 */
@Order
public class EnableRatelImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String name = getAnnotationClass().getName();
        Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(name, true);
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(attributes);
        return annotationAttributes != null ? annotationAttributes.getStringArray("basePackageClasses") : new String[0];
    }

    /**
     * Return the source annotation class used by the selector.
     * @return the annotation class
     */
    private Class<?> getAnnotationClass() {
        return EnableRatel.class;
    }
}
