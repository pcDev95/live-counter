package com.livecounter.lc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LcApplication.class, args);
	}

}
