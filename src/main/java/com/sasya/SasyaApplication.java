package com.sasya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sasya"})
@EnableJpaRepositories
public class SasyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SasyaApplication.class, args);
	}
}
