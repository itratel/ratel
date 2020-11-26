package org.ratelframework.ratel.multidatasource.autoconfigure.datasource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
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
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * <p>MyBatisAutoConfiguration<p/>
 * MyBatis support related auto-configuration.
 * @author whd.java@gmail.com
 * @date 2020/6/17 14:34
 * @since 1.0.0
 */
@Configuration
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@ConditionalOnClass(SqlSession.class)
public class MyBatisAutoConfiguration {

	/**
	 * Configuration mybatis template for primary data source
	 */
	@Bean
	@PrimarySqlSessionTemplate
	@ConditionalOnPrimaryDataSource
	@ConditionalOnProperty(name = "ratel.datasource.primary.enable-sql-session-template", havingValue = "true")
	@ConditionalOnMissingBean(name = PrimarySqlSessionTemplate.NAME)
	public SqlSessionTemplate primarySqlSessionTemplate(MybatisProperties properties,
												 @PrimaryDataSource DataSource dataSource,
												 ResourceLoader resourceLoader) throws Exception {
		return new SqlSessionTemplate(this.createSqlSessionFactory(properties, dataSource, resourceLoader));
	}

	/**
	 * Configuration mybatis template for Secondary data source
	 */
	@Bean
	@SecondarySqlSessionTemplate
	@ConditionalOnSecondaryDataSource
	@ConditionalOnProperty(name = "ratel.datasource.secondary.enable-sql-session-template", havingValue = "true")
	@ConditionalOnMissingBean(name = SecondarySqlSessionTemplate.NAME)
	public SqlSessionTemplate secondarySqlSessionTemplate(MybatisProperties properties,
													@SecondaryDataSource DataSource dataSource,
													ResourceLoader resourceLoader) throws Exception {
		return new SqlSessionTemplate(this.createSqlSessionFactory(properties, dataSource, resourceLoader));
	}

	/**
	 * Configuration mybatis template for candidate data source
	 */
	@Bean
	@CandidateSqlSessionTemplate
	@ConditionalOnCandidateDataSource
	@ConditionalOnProperty(name = "ratel.datasource.candidate.enable-sql-session-template", havingValue = "true")
	@ConditionalOnMissingBean(name = CandidateSqlSessionTemplate.NAME)
	public SqlSessionTemplate candidateSqlSessionTemplate(MybatisProperties properties,
													@CandidateDataSource DataSource dataSource,
													ResourceLoader resourceLoader) throws Exception {
		return new SqlSessionTemplate(this.createSqlSessionFactory(properties, dataSource, resourceLoader));
	}


	private SqlSessionFactory createSqlSessionFactory(MybatisProperties properties,
													  DataSource dataSource,
													  ResourceLoader resourceLoader) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
		factory.setVfs(SpringBootVFS.class);

		// handle the config location
		if (StringUtils.hasText(properties.getConfigLocation())) {
			factory.setConfigLocation(resourceLoader.getResource(properties.getConfigLocation()));
		}

		org.apache.ibatis.session.Configuration configuration = properties.getConfiguration();
		if (configuration == null && !StringUtils.hasText(properties.getConfigLocation())) {
			configuration = new org.apache.ibatis.session.Configuration();
		}
		factory.setConfiguration(configuration);
		if (properties.getConfigurationProperties() != null) {
			factory.setConfigurationProperties(properties.getConfigurationProperties());
		}
		if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
		}


		// by default, make mapper locations at 'classpath:mapper/**/*.xml'
		if (ObjectUtils.isEmpty(properties.getMapperLocations())) {
			properties.setMapperLocations(new String[]{ "classpath:mapper/**/*.xml" });
		}
		if (!ObjectUtils.isEmpty(properties.resolveMapperLocations())) {
			factory.setMapperLocations(properties.resolveMapperLocations());
		}

		return factory.getObject();
	}
}
