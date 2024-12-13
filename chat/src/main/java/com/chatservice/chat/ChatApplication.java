package com.chatservice.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = {"com.chat.config",
		"com.chat.controller",
		"com.chat.models",
		"com.chat.service",
})
@EnableMongoRepositories(basePackages = "com.chat.repositories")
public class ChatApplication {


	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}


}
