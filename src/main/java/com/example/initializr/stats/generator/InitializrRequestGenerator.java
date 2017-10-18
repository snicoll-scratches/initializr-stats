package com.example.initializr.stats.generator;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.initializr.stats.domain.InitializrRequest;

public class InitializrRequestGenerator {

	private final Random random = new Random();

	private final RandomList<String> bootVersions = new RandomList<>("1.5.8.RELEASE",
			new RandomList.Item<>("2.0.0.M5", 0.2),
			new RandomList.Item<>("2.0.0.BUILD-SNAPSHOT", 0.1),
			new RandomList.Item<>("1.4.7.RELEASE", 0.05));
	private final RandomList<String> javaVersions = new RandomList<>("1.8",
			new RandomList.Item<>("1.7", 0.15),
			new RandomList.Item<>("1.6", 0.05));
	private final RandomList<String> languages = new RandomList<>("java",
			new RandomList.Item<>("kotlin", 0.02),
			new RandomList.Item<>("groovy", 0.005));
	private final RandomList<String> packagings = new RandomList<>("jar",
			new RandomList.Item<>("war", 0.05));
	private final RandomList<String> types = new RandomList<>("maven-project",
			new RandomList.Item<>("gradle-project", 0.15));
	private final RandomList<List<String>> dependencies = new RandomList<>(
			Collections.singletonList("web"),
			new RandomList.Item<>(Arrays.asList("web", "thymeleaf"), 0.15),
			new RandomList.Item<>(Arrays.asList("web", "data-jpa", "h2"), 0.12),
			new RandomList.Item<>(Arrays.asList("web", "actuator"), 0.08),
			new RandomList.Item<>(Arrays.asList("web", "security"), 0.08),
			new RandomList.Item<>(Arrays.asList("web", "data-jpa", "mybatis", "mysql"), 0.07),
			new RandomList.Item<>(Collections.singletonList("cloud-eureka-server"), 0.05));


	private final RandomList<String> invalidDependencies = new RandomList<>("foobar",
			new RandomList.Item<>("data-test", 0.2),
			new RandomList.Item<>("could-server", 0.1),
			new RandomList.Item<>("personaWebService", 0.05));

	private final RandomList<String> invalidLanguages = new RandomList<>("scala",
			new RandomList.Item<>("java,groovy", 0.1),
			new RandomList.Item<>("groooooovy", 0.1),
			new RandomList.Item<>("jarjar", 0.1),
			new RandomList.Item<>("kortlin", 0.1));

	private final RandomList<String> invalidTypes = new RandomList<>("ant",
			new RandomList.Item<>("ant-project", 0.2),
			new RandomList.Item<>("kortlin", 0.1));


	private final double errorRatio;

	public InitializrRequestGenerator(double errorRatio) {
		this.errorRatio = errorRatio;
	}

	public InitializrRequest generateRequest() {
		InitializrRequest request = new InitializrRequest();
		request.setGenerationTimestamp(Instant.now().toEpochMilli());
		request.setBootVersion(this.bootVersions.getRandomValue());
		request.setJavaVersion(this.javaVersions.getRandomValue());
		request.setLanguage(this.languages.getRandomValue());
		request.setPackaging(this.packagings.getRandomValue());
		request.setType(this.types.getRandomValue());
		request.getDependencies().addAll(this.dependencies.getRandomValue());
		if (this.random.nextDouble() < this.errorRatio) {
			handleInvalidRequest(request);
		}
		return request;
	}

	private void handleInvalidRequest(InitializrRequest request) {
		request.setInvalid(true);
		double random = this.random.nextDouble();
		if (random < 0.6) {
			request.getInvalidDependencies().add(this.invalidDependencies.getRandomValue());
		}
		else if (random >= 0.6 && random < 0.8) {
			request.setLanguage(this.invalidLanguages.getRandomValue());
			request.setInvalidLanguage(true);
		}
		else {
			request.setType(this.invalidTypes.getRandomValue());
			request.setInvalidType(true);
		}
	}

}
