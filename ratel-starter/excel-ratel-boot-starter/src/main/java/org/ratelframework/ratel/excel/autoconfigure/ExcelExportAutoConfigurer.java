package org.ratelframework.ratel.excel.autoconfigure;

import org.ratelframework.ratel.excel.config.ExcelExportConfig;
import org.ratelframework.ratel.excel.config.ExcelExportHandler;
import org.ratelframework.ratel.excel.config.ExcelExportMvcConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author whd.java@gmail.com
 * @date 2020/03/21 23:49
 * @apiNote Describe the function of this class in one sentence
 */
@Configuration
@EnableConfigurationProperties({ExcelExportConfig.class})
public class ExcelExportAutoConfigurer {

    @Resource
    private ExcelExportConfig excelExportConfig;

    public ExcelExportAutoConfigurer() {
    }

    @Bean
    @ConditionalOnMissingBean({ExcelExportMvcConfigurer.class})
    public ExcelExportMvcConfigurer excelExportMvcConfigurer() {
        return new ExcelExportMvcConfigurer(this.excelExportConfig);
    }

    @Bean
    @ConditionalOnMissingBean({ExcelExportHandler.class})
    public ExcelExportHandler excelExportHandler() {
        return new ExcelExportHandler();
    }
}