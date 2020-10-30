package com.example.numbers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class NumbersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumbersApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		log.info("Started application in IST timezone :" + ZonedDateTime.now());
	}
}
