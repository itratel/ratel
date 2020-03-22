package org.ratelframework.ratel.excel.core.formatter;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
public interface ReturnValueFormatter {

    default Object format(Object obj, Object original) {
        return original;
    }
}