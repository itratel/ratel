package org.ratelframework.ratel.common.autoconfigure.datasource;

import org.ratelframework.ratel.common.annotation.*;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnCandidateDataSource;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnPrimaryDataSource;
import org.ratelframework.ratel.common.autoconfigure.condition.ConditionalOnSubDataSource;
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
 * JdbcTemplate related auto-configuration.
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
	@ConditionalOnProperty(name = "sdp.datasource.primary.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = PrimaryJdbcTemplate.NAME)
	public JdbcTemplate jdbcTemplate(@PrimaryDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Configuration jdbc template for sub data source
	 */
	@Bean
	@SubJdbcTemplate
	@ConditionalOnSubDataSource
	@ConditionalOnProperty(name = "sdp.datasource.sub.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = SubJdbcTemplate.NAME)
	public JdbcTemplate jdbcTemplateSub(@SubDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * Configuration jdbc template for candidate data source
	 */

	@Bean
	@CandidateJdbcTemplate
	@ConditionalOnCandidateDataSource
	@ConditionalOnProperty(name = "sdp.datasource.candidate.enable-jdbc-template", havingValue = "true")
	@ConditionalOnMissingBean(name = CandidateJdbcTemplate.NAME)
	public JdbcTemplate jdbcTemplateCandidate(@CandidateDataSource DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
