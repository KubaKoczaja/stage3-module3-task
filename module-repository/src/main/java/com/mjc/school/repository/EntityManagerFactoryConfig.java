package com.mjc.school.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
public class EntityManagerFactoryConfig {
		private EntityManagerFactoryConfig() {		}

		@Bean
		public static javax.persistence.EntityManagerFactory entityManagerFactory() {
				return Persistence.createEntityManagerFactory("KAPAPLAN");
		}
}