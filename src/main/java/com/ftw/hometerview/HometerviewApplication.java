package com.ftw.hometerview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class HometerviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(HometerviewApplication.class, args);
	}

}
