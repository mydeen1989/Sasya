package com.sasya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sasya"})
public class SasyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SasyaApplication.class, args);
	}
}
