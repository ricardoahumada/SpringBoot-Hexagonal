/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */
 
package es.bit.tareasproyectoshex.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.repositoryadapters.UserJPARepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringRepositoryConfig {

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(javatunesEmf().getObject());
		return transactionManager;
	}

	@Bean
	DataSource javatunesDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("tareasproyectos.driverClassName"));
		ds.setUrl(env.getProperty("tareasproyectos.url"));
		ds.setUsername(env.getProperty("tareasproyectos.dbUserName"));
		ds.setPassword(env.getProperty("tareasproyectos.password"));
		return ds;
	}

	@Bean
	public JpaVendorAdapter vendorAdapter() {
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
		va.setShowSql(true);
		va.setGenerateDdl(false);
		return va;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean javatunesEmf() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(javatunesDataSource());
		em.setPersistenceUnitName(env.getProperty("tareasproyectos.persistenceUnitName"));
		em.setPackagesToScan("es.bit.tareasproyectoshex.*");
		em.setJpaVendorAdapter(vendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("tareasproyectos.dialect"));
		return properties;
	}

	@Bean(name = "jpaRepo")
	public UserRepository userRepository() {
		return new UserJPARepositoryAdapter();
	}

}