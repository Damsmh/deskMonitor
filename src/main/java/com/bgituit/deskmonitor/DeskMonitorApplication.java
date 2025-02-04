package com.bgituit.deskmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DeskMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeskMonitorApplication.class, args);
	}

}
