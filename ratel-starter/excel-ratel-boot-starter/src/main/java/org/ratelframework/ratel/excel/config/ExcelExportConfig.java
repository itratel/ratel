package org.ratelframework.ratel.excel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@ConfigurationProperties(prefix = "excel.export")
public class ExcelExportConfig {

    private int rowAccessSize = 100;

    public ExcelExportConfig() {
    }

    public int getRowAccessSize() {
        return this.rowAccessSize;
    }

    public void setRowAccessSize(int rowAccessSize) {
        this.rowAccessSize = rowAccessSize;
    }
}