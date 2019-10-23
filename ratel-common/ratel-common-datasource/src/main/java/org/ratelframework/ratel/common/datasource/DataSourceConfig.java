package org.ratelframework.ratel.common.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/23 17:26
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 * @version 0.0.1
 */
@Configuration
@EnableAutoConfiguration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties("spring.datasource.druid.primary")
    public DataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "subDataSource")
    @ConfigurationProperties("spring.datasource.druid.sub")
    public DataSource subDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "candidateDataSource")
    @ConfigurationProperties("spring.datasource.druid.candidate")
    public DataSource candidateDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
