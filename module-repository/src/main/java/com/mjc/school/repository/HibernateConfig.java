package com.mjc.school.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

		@Bean
		public LocalSessionFactoryBean sessionFactory() {
				LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
				sessionFactory.setDataSource(dataSource());
				sessionFactory.setPackagesToScan("com.mjc.school.repository");
				sessionFactory.setHibernateProperties(hibernateProperties());
				return sessionFactory;
		}
		@Bean
		public Session session() {
				return Objects.requireNonNull(sessionFactory().getObject()).openSession();
		}

		@Bean
		public DataSource dataSource() {
				BasicDataSource dataSource = new BasicDataSource();
				dataSource.setDriverClassName("org.h2.Driver");
				dataSource.setUrl("jdbc:h2:mem:testdb");
				dataSource.setUsername("sa");
				dataSource.setPassword("password");
				return dataSource;
		}

		@Bean
		public PlatformTransactionManager hibernateTransactionManager() {
				HibernateTransactionManager transactionManager
								= new HibernateTransactionManager();
				transactionManager.setSessionFactory(sessionFactory().getObject());
				return transactionManager;
		}

		private Properties hibernateProperties() {
				Properties hibernateProperties = new Properties();
				hibernateProperties.setProperty(
								"hibernate.hbm2ddl.auto", "create-drop");
				hibernateProperties.setProperty(
								"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
				return hibernateProperties;
		}
}