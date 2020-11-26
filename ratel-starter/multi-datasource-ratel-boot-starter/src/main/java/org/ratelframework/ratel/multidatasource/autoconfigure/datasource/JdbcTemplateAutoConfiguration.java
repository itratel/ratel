package org.ratelframework.ratel.multidatasource.autoconfigure.datasource;

import org.ratelframework.ratel.multidatasource.annotation.*;
import org.ratelframework.ratel.multidatasource.autoconfigure.condition.ConditionalOnCandidateDataSource;
import org.ratelframework.ratel.multidatasource.autoconfigure.condition.ConditionalOnPrimaryDataSource;
import org.ratelframework.ratel.multidatasource.autoconfigure.condition.ConditionalOnSecondaryDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * <p>JdbcTemplateAutoConfiguration<p/>
 * JdbcTemplate related auto-configuration.
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 1.0.0
 */
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ConditionalOnClass(JdbcTemplate.class)
public class JdbcTemplateAutoConfiguration {

	/**
	 * Configuration jdbc template for Primary data source
	 */
	@Primary
	@Bean
	@PrimaryJdbcTemplate
	@ConditionalOnPrimaryDataSource
	@ConditionalOnProperty(name = "ratel.datasource.primary.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = PrimaryJdbcTemplate.NAME)
	public JdbcTemplate primaryJdbcTemplate(@PrimaryDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Configuration jdbc template for Secondary data source
	 */
	@Bean
	@SecondaryJdbcTemplate
	@ConditionalOnSecondaryDataSource
	@ConditionalOnProperty(name = "ratel.datasource.secondary.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = SecondaryJdbcTemplate.NAME)
	public JdbcTemplate secondaryJdbcTemplate(@SecondaryDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Configuration jdbc template for candidate data source
	 */

	@Bean
	@CandidateJdbcTemplate
	@ConditionalOnCandidateDataSource
	@ConditionalOnProperty(name = "ratel.datasource.candidate.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = CandidateJdbcTemplate.NAME)
	public JdbcTemplate candidateJdbcTemplate(@CandidateDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
