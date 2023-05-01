package com.kong.king.spring.youtuber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringYoutuberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringYoutuberApplication.class, args);
	}

}
