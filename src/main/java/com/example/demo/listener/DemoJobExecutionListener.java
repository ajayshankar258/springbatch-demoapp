package com.example.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component(value="demoJobExecutionListener")
@Slf4j
public class DemoJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("*** DemoJobExecutionListener : beforeJob ***");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("*** DemoJobExecutionListener : afterJob ***");
	}

}
