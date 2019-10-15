package com.example.databaseConfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class MysqlSqlConfig {

	@Configuration
	@EnableJpaAuditing
	@Profile({ "mysql-dev" })
	@EnableTransactionManagement
	@EnableJpaRepositories(entityManagerFactoryRef = "devEntityManagerFactory", basePackages = {
			"com.example.dev.repository" })
	public class DevDbConfig {

		@Bean(name = "devDataSource")
		@ConfigurationProperties(prefix = "spring.datasource")
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}

		@Bean(name = "devEntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
				@Qualifier("devDataSource") DataSource dataSource) {
			String entityPackage = "com.example.dev.entity";
			return builder.dataSource(dataSource).packages(entityPackage).persistenceUnit("dev").build();
		}

		@Bean(name = "devTransactionManager")
		public PlatformTransactionManager devTransactionManager(
				@Qualifier("devEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
	}

	@Configuration
	@EnableJpaAuditing
	@Profile({ "mysql-test" })
	@EnableTransactionManagement
	@EnableJpaRepositories(entityManagerFactoryRef = "testEntityManagerFactory", basePackages = {
			"com.example.test.repository" })
	public class TestDbConfig {

		@Bean(name = "testDataSource")
		@ConfigurationProperties(prefix = "test.datasource")
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}

		@Bean(name = "testEntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
				@Qualifier("testDataSource") DataSource dataSource) {
			String entityPackage = "com.example.test.entity";
			return builder.dataSource(dataSource).packages(entityPackage).persistenceUnit("test").build();
		}

		@Bean(name = "testTransactionManager")
		public PlatformTransactionManager testTransactionManager(
				@Qualifier("testEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
	}

	@Configuration
	@EnableJpaAuditing
	@Profile({ "mysql-test1" })
	@EnableTransactionManagement
	@EnableJpaRepositories(entityManagerFactoryRef = "test1EntityManagerFactory", basePackages = {
			"com.example.test1.repository" })
	public class Test1DbConfig {

		@Bean(name = "test1DataSource")
		@ConfigurationProperties(prefix = "test1.datasource")
		public DataSource dataSource() {
			return DataSourceBuilder.create().build();
		}

		@Bean(name = "test1EntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
				@Qualifier("test1DataSource") DataSource dataSource) {
			String entityPackage = "com.example.test1.entity";
			return builder.dataSource(dataSource).packages("com.foobar.foo.domain").persistenceUnit("test1").build();
		}

		@Bean(name = "test1TransactionManager")
		public PlatformTransactionManager test1TransactionManager(
				@Qualifier("test1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
	}

}
