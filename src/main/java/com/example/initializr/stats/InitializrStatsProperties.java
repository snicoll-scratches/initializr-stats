package com.example.initializr.stats;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stats")
public class InitializrStatsProperties {

	private final Generator generator = new Generator();

	private long delay = 100;

	public Generator getGenerator() {
		return this.generator;
	}

	public long getDelay() {
		return this.delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public static class Generator {

		/**
		 * Ratio of invalid requests.
		 */
		private double errorRatio = 0.02;

		public double getErrorRatio() {
			return this.errorRatio;
		}

		public void setErrorRatio(double errorRatio) {
			this.errorRatio = errorRatio;
		}
	}

}
