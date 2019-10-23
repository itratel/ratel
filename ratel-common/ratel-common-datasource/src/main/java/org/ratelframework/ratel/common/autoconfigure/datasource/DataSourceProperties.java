package org.ratelframework.ratel.common.autoconfigure.datasource;

import lombok.Data;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ResourceLoader;

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
 *
 */
@Data
@ConfigurationProperties("sdp.datasource")
class DataSourceProperties {

    // we can use a java.util.Map to host all possible data sources, but at this stage, we're going to restrict data
    // sources with support to local, dwh, and dwhmx, which looks silly somehow.
    //
    // On thing to note here is: the variable name must be the same as what defined in {@link DataSourceAutoConfiguration}
    // otherwise, the generated metadata will not match.

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
         * @see JdbcTemplateAutoConfiguration#jdbcTemplate(DataSource)
         * @see JdbcTemplateAutoConfiguration#jdbcTemplateDwh(DataSource)
         * @see JdbcTemplateAutoConfiguration#jdbcTemplateDwhMx(DataSource)
         */
        private boolean enableJdbcTemplate = false;

        /**
         * Should MyBatis support be turned on.
         *
         * @see MyBatisAutoConfiguration#sqlSessionTemplate(MybatisProperties, DataSource, ResourceLoader)
         * @see MyBatisAutoConfiguration#sqlSessionTemplateDwh(MybatisProperties, DataSource, ResourceLoader)
         * @see MyBatisAutoConfiguration#sqlSessionTemplateDwhMx(MybatisProperties, DataSource, ResourceLoader)
         */
        private boolean enableSqlSessionTemplate = false;
    }
}
