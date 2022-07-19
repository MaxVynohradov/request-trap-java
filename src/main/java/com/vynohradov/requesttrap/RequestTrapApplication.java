package com.vynohradov.requesttrap;

import com.vynohradov.requesttrap.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class RequestTrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestTrapApplication.class, args);
	}

}
