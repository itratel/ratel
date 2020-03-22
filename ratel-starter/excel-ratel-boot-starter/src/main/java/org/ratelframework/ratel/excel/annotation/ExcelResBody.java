package org.ratelframework.ratel.excel.annotation;

import org.ratelframework.ratel.excel.enums.FileSuffix;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResBody {

    /***
     * export file name
     * @return {@link String}
     */
    String fileName() default "";

    /***
     * sheet name
     * @return {@link String}
     */
    String sheetName() default "";

    /***
     * fields name
     * @return an array of {@link String}
     */
    String[] fieldNames() default {};

    /***
     * alias
     * @return an array of {@link String}
     */
    String[] alias() default {};

    /***
     * file name suffix
     * @return {@link FileSuffix}
     */
    FileSuffix suffix() default FileSuffix.XLSX;

    /***
     * file title
     * @return {@link FileSuffix}
     */
    String title();


}
