package org.ratelframework.ratel.common.autoconfigure.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.ratelframework.ratel.common.annotation.CandidateDataSource;
import org.ratelframework.ratel.common.annotation.PrimaryDataSource;
import org.ratelframework.ratel.common.annotation.SubDataSource;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnMissingCandidateDataSource;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnMissingPrimaryDataSource;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnMissingSubDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;


/***
 *  DataSource related auto-configuration.
 *  For the current being, there might be 3 kind of data-sources:
 *  1. Local data source -- for local business (e.g. user login/authentication, etc)
 *  2. Main data warehouse  -- for storing main info of Expense (typically, t_jsxx)
 *  3. Detailed data warehouse  -- for storing detail info of Expense (typically, t_jsxx_mx)
 *
 *  And here is the design goal/constraint:
 *  1. We can omit any one of them.
 *  2. The local data source, if present, must have transaction managed, while the other
 *       two may not as they are built for analytical purpose(OLAP).
 *
 * @author whd.java@gmail.com
 * @date 2019/10/23 17:26
 * @apiNote Describe the function of this class in one sentence
 * @since 0.0.1
 * @version 0.0.1
 */
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAutoConfiguration {

    /**
     * Configuration for primary data source
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ratel.datasource.primary.config", name = "url")
    @ConditionalOnMissingPrimaryDataSource
    static class PrimaryDataSourceConfiguration {

        @Bean
        @Primary
        @PrimaryDataSource
        @ConditionalOnProperty(name = "ratel.datasource.primary.config.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
        @ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
        @ConfigurationProperties(prefix = "ratel.datasource.primary.config")
        public DruidDataSource primaryDataSource() {
            return DataSourceBuilder.create().type(DruidDataSource.class).build();
        }
    }

    /**
     * Configuration for sub data source
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ratel.datasource.sub.config", name = "url")
    @ConditionalOnMissingSubDataSource
    static class SubDataSourceConfiguration {

        @Bean
        @SubDataSource
        @ConditionalOnClass(DruidDataSource.class)
        @ConditionalOnProperty(name = "ratel.datasource.sub.config.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
        @ConfigurationProperties(prefix = "ratel.datasource.sub.config")
        public DruidDataSource subDataSource() {
            return DataSourceBuilder.create().type(DruidDataSource.class).build();
        }
    }


    /**
     * Configuration for candidate data source
     */
    @Configuration
    @ConditionalOnProperty(prefix = "ratel.datasource.candidate.config", name = "url")
    @ConditionalOnMissingCandidateDataSource
    static class CandidateDataSourceConfiguration {

        @Bean
        @CandidateDataSource
        @ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
        @ConditionalOnProperty(name = "ratel.datasource.candidate.config.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
        @ConfigurationProperties(prefix = "ratel.datasource.candidate.config")
        public DruidDataSource candidateDataSource() {
            return DataSourceBuilder.create().type(DruidDataSource.class).build();
        }
    }
}
