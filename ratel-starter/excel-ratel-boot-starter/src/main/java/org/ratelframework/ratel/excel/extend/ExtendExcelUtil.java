package org.ratelframework.ratel.excel.extend;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;


/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
public class ExtendExcelUtil extends ExcelUtil {
    public ExtendExcelUtil() {
    }

    public static ExcelWriter getBigWriter(int rowAccessSize) {
        return new ExtendBigExcelWriter(rowAccessSize);
    }
}
