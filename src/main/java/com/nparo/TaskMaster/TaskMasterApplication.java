package com.nparo.TaskMaster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskMasterApplication {
	
	@Value("${amazon.aws.accesskey}")
	public static String amazonAWSAccessKey;

	public static void main(String[] args) {
		SpringApplication.run(TaskMasterApplication.class, args);
	}

}
