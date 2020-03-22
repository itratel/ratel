package org.ratelframework.ratel.excel.extend;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
public class ExtendBigExcelWriter extends BigExcelWriter {

    ExtendBigExcelWriter(int rowAccessWindowSize) {
        super(rowAccessWindowSize);
    }


    @Override
    public ExcelWriter writeRow(Object rowBean, boolean isWriteKeyAsHead) {
        if (rowBean instanceof Iterable) {
            return this.writeRow((Iterable)rowBean);
        } else {
            Map rowMap;
            if (rowBean instanceof Map) {
                rowMap = (Map)rowBean;
            } else {
                if (!BeanUtil.isBean(rowBean.getClass())) {
                    return this.writeRow(CollUtil.newArrayList(new Object[]{rowBean}), isWriteKeyAsHead);
                }

                rowMap = BeanUtil.beanToMap(rowBean, new LinkedHashMap<>(), false, false);
            }

            return this.writeRow(rowMap, isWriteKeyAsHead);
        }
    }
}
