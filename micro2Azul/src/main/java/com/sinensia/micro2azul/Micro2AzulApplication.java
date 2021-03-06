package com.sinensia.micro2azul;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class Micro2AzulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Micro2AzulApplication.class, args);
	}
	

}
