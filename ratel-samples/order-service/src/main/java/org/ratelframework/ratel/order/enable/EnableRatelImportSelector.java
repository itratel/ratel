package org.ratelframework.ratel.order.enable;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2019/12/13 00:01
 * @apiNote Describe the function of this class in one sentence
 */
@Order
public class EnableRatelImportSelector implements ImportSelector, BeanClassLoaderAware {

    private ClassLoader beanClassLoader;

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        String name = getAnnotationClass().getName();
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(name, true);
        //TODO 继续完成
        return new String[0];
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }


    /**
     * Return the source annotation class used by the selector.
     * @return the annotation class
     */
    protected Class<?> getAnnotationClass() {
        return EnableRatel.class;
    }
}
