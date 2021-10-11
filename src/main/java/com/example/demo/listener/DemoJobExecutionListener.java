package com.example.demo.listener;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import com.example.demo.util.FileUtil;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component(value="demoJobExecutionListener")
@Slf4j
public class DemoJobExecutionListener implements JobExecutionListener {

	@Autowired
	private FileUtil fileUtil;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("*** DemoJobExecutionListener : beforeJob ***");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		fileUtil.deleteFile();
		log.info("*** DemoJobExecutionListener : afterJob ***");
	}

}
