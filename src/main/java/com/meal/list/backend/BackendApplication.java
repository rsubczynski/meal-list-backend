package com.meal.list.backend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendApplication {


	@Bean
	ApplicationRunner applicationRunner (GreetingRepository greetingRepository){
		return args -> {
			greetingRepository.save(new Greeting("Hello"));
			greetingRepository.save(new Greeting("Dziala"));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
