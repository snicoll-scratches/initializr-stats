package com.example.initializr.stats;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stats")
public class InitializrStatsProperties {

	private final Generator generator = new Generator();

	/**
	 * Minimum delay in ms between two requests.
	 */
	private int minDelay = 25;

	/**
	 * Minimum delay in ms between two requests.
	 */
	private int maxDelay = 200;

	public Generator getGenerator() {
		return this.generator;
	}

	public int getMinDelay() {
		return this.minDelay;
	}

	public void setMinDelay(int minDelay) {
		this.minDelay = minDelay;
	}

	public int getMaxDelay() {
		return this.maxDelay;
	}

	public void setMaxDelay(int maxDelay) {
		this.maxDelay = maxDelay;
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
