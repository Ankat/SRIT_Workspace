package com.esic.bmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.esic.bmc" })
@EnableScheduling
public class EISICBMCCountStatusMain {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EISICBMCCountStatusMain.class);
	}
}
