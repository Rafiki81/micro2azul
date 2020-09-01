package com.sinensia.micro2azul.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJobConfig {

	@Autowired
	protected JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	protected StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	protected DataSource dataSource;
}
