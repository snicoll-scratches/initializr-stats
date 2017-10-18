package com.example.initializr.stats;

import com.example.initializr.stats.generator.InitializrRequestGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(InitializrStatsProperties.class)
public class InitializrStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitializrStatsApplication.class, args);
	}

	@Bean
	public InitializrRequestGenerator initializrRequestGenerator(
			InitializrStatsProperties properties) {
		return new InitializrRequestGenerator(properties.getGenerator().getErrorRatio());
	}
	
}
