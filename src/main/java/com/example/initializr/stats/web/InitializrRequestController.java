package com.example.initializr.stats.web;

import java.time.Duration;

import com.example.initializr.stats.InitializrStatsProperties;
import com.example.initializr.stats.domain.InitializrRequest;
import com.example.initializr.stats.generator.InitializrRequestGenerator;
import reactor.core.publisher.Flux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitializrRequestController {

	private final InitializrRequestGenerator generator;

	private final Flux<InitializrRequest> requests;

	public InitializrRequestController(InitializrRequestGenerator generator,
			InitializrStatsProperties properties) {
		this.generator = generator;
		this.requests = Flux.interval(Duration.ofMillis(properties.getDelay()))
				.map(i -> generator.generateRequest())
				.share();
	}

	@GetMapping(value = "/requests", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<InitializrRequest> fetchRequests() {
		return this.requests;
	}

}
