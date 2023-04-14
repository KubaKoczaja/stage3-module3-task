package com.mjc.school.main;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
@NoArgsConstructor
public class EntityManagerFactoryConfig {
		@Bean
		@Primary
		public static javax.persistence.EntityManagerFactory entityManagerFactory() {
				return Persistence.createEntityManagerFactory("KAPAPLAN");
		}
}