package org.ratelframework.ratel.excel.config;

import org.ratelframework.ratel.excel.core.handler.ExcelResBodyReturnValueHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
public class ExcelExportMvcConfigurer implements WebMvcConfigurer {

    private ExcelExportConfig excelExportConfig;

    public ExcelExportMvcConfigurer() {
    }

    public ExcelExportMvcConfigurer(ExcelExportConfig excelExportConfig) {
        this.excelExportConfig = excelExportConfig;
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add((new ExcelResBodyReturnValueHandler()).setExcelExportConfig(this.excelExportConfig));
    }
}