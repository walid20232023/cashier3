package org.openmrs.module.mycashier.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Bean
	public DataSource cashierDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // Changez selon votre driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/cashier"); // Changez selon votre URL de base de données
		dataSource.setUsername("waliy"); // Changez selon votre nom d'utilisateur de base de données
		dataSource.setPassword("admin"); // Changez selon votre mot de passe de base de données
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(cashierDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Changez selon votre dialecte
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
}
