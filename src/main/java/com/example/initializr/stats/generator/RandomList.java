package com.example.initializr.stats.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

final class RandomList<T> {

	private final NavigableMap<Double, T> values;

	private final Random random = new Random();

	public RandomList(T defaultValue, Item<T>... items) {
		this(applyDefaultValue(defaultValue, items));
	}

	private static <T> List<Item<T>> applyDefaultValue(T defaultValue, Item<T>[] items) {
		double coveredRatio = Arrays.stream(items).mapToDouble(Item::getRatio).sum();
		if (coveredRatio >= 1) {
			throw new IllegalArgumentException("100% ratio already covered");
		}
		List<Item<T>> total = new ArrayList<>(Arrays.asList(items));
		total.add(new Item<>(defaultValue, 1 - coveredRatio));
		return total;
	}

	public RandomList(List<Item<T>> items) {
		this.values = new TreeMap<>();
		Double previousRatio = null;
		double currentKey = 0;
		for (Item<T> item : items) {
			if (previousRatio != null) {
				currentKey +=previousRatio;
			}
			if (currentKey > 1) {
				throw new IllegalStateException("Invalid ratio for " + items);
			}
			values.put(currentKey, item.getValue());
			previousRatio = item.getRatio();
		}
	}

	public T getRandomValue() {
		double random = this.random.nextDouble();
		Map.Entry<Double, T> doubleTEntry = this.values.lowerEntry(random);
		if (doubleTEntry == null) {
			return null;
		}
		return doubleTEntry.getValue();
	}

	public static class Item<T> {

		private T value;

		private Double ratio;

		public Item(T value, Double ratio) {
			this.value = value;
			this.ratio = ratio;
		}

		public T getValue() {
			return this.value;
		}

		public Double getRatio() {
			return this.ratio;
		}
	}

}
