package com.example.initializr.stats.web;

import java.time.Duration;
import java.util.Random;

import com.example.initializr.stats.InitializrStatsProperties;
import com.example.initializr.stats.domain.InitializrRequest;
import com.example.initializr.stats.generator.InitializrRequestGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitializrRequestController {

	private final Random random = new Random();

	private final int minDelay;
	private final int maxDelay;

	private final Flux<InitializrRequest> requests;

	public InitializrRequestController(InitializrRequestGenerator generator,
			InitializrStatsProperties properties) {
		this.minDelay = properties.getMinDelay();
		this.maxDelay = properties.getMaxDelay();
		this.requests = Flux.generate(sink -> sink.next("tick"))
				.delayUntil(i -> randomDelay())
				.map(i -> generator.generateRequest())
				.share();
	}

	@GetMapping(value = "/requests", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<InitializrRequest> fetchRequests() {
		return this.requests;
	}

	private Mono<Long> randomDelay() {
		return Mono.delay(Duration.ofMillis(this.minDelay
				+ this.random.nextInt(this.maxDelay)));
	}

}
