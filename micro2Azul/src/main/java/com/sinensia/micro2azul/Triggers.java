package com.sinensia.micro2azul;

import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Triggers {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private JobLauncher asyncJobLauncher; 
	
	@Scheduled(cron = "0 * * * * *" )
	public void cronTrigger() throws BeansException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException  {
		

		Calendar fecha1 = Calendar.getInstance();
		fecha1.setTime(new Date());
		fecha1.set(Calendar.HOUR_OF_DAY, 00);
		Calendar fecha2 = Calendar.getInstance();
		fecha1.setTime(new Date());
		fecha1.set(Calendar.HOUR_OF_DAY, 24);
		System.out.println("se lanza");
		asyncJobLauncher.run(context.getBean("jobPedido", Job.class), new JobParametersBuilder()
	        .addDate("fecha1", fecha1.getTime())
	        .addDate("fecha2", fecha2.getTime())
	        .toJobParameters());
	}
}
