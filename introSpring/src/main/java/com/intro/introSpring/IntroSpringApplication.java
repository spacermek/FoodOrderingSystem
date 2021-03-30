package com.intro.introSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.intro.introSpring.Repository")
public class IntroSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringApplication.class, args);
	}

}
