package com.server.muchu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MuchuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuchuApplication.class, args);
	}

}
