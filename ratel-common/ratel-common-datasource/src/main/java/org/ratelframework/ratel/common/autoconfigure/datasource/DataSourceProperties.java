package org.ratelframework.ratel.common.autoconfigure.datasource;

import lombok.Data;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Data source properties. The *only* purpose of this class is to generate spring configuration metadata, i.e.,
 * the /META-INF/spring-configuration-metadata.json file.
 *
 * The spring configuration metadata is composed with two parts in this project:
 * 1). Normal part of the datasource configuration is delegated to each data source, which is achieved by adding
 *     \@ConfiguationProperites(prefix = "some.prefix") to specific DataSource bean. Refer to
 *     {@link DataSourceAutoConfiguration} for more.
 * 2). Extended part of the datasource configuration is contributed here.
 * @author whd.java@gmail.com
 */
@Data
@Component
@ConfigurationProperties(DataSourceProperties.DATASOURCE_PREFIX)
public class DataSourceProperties {

    public static final String DATASOURCE_PREFIX = "ratel.datasource";

    /**
     * Extended datasource properties for primary datasource
     */
    private ExtendedDataSourceProperties primary;

    /**
     * Extended datasource properties for sub datasource
     */
    private ExtendedDataSourceProperties sub;

    /**
     * Extended datasource properties for candidate datasource
     */
    private ExtendedDataSourceProperties candidate;

    @Data
    private static class ExtendedDataSourceProperties {

        /**
         * Should JdbcTemplate support be turned on.
         *
         * @see JdbcTemplateAutoConfiguration#primaryJdbcTemplate(DataSource)
         * @see JdbcTemplateAutoConfiguration#subJdbcTemplate(DataSource)
         * @see JdbcTemplateAutoConfiguration#candidateJdbcTemplate(DataSource)
         */
        private boolean enableJdbcTemplate = false;

        /**
         * Should MyBatis support be turned on.
         *
         * @see MyBatisAutoConfiguration#primarySqlSessionTemplate(MybatisProperties, DataSource, ResourceLoader)
         * @see MyBatisAutoConfiguration#subSqlSessionTemplateSub(MybatisProperties, DataSource, ResourceLoader)
         * @see MyBatisAutoConfiguration#candidateSqlSessionTemplateCandidate(MybatisProperties, DataSource, ResourceLoader)
         */
        private boolean enableSqlSessionTemplate = false;
    }
}
