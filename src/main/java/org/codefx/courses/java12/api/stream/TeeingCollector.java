package org.codefx.courses.java12.api.stream;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.teeing;

public class TeeingCollector {

	/*
	 * TASK: Implement the two methods `computeStats` and `computeRangeFromMinToMax`,
	 *       so that they pass the tests in TeeinfCollectorTests.
	 */

	public static Statistics computeStats(Stream<Integer> numbers) {
		return numbers
				.collect(teeing(
						summingInt(i -> i),
						averagingInt(i -> i),
						Statistics::of));
	}

	public static Optional<Range<String>> computeRangeFromMinToMax(Stream<String> words) {
		Comparator<String> lexicographic = Comparator.<String>naturalOrder();
		return words
				.collect(teeing(
						minBy(lexicographic),
						maxBy(lexicographic),
						Range::ofOptional));
	}

	public static class Statistics {

		private final int sum;
		private final double average;

		private Statistics(int sum, double average) {
			this.sum = sum;
			this.average = average;
		}

		public static Statistics of(int sum, double average) {
			return new Statistics(sum, average);
		}

		public int sum() {
			return sum;
		}

		public double average() {
			return average;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Statistics that = (Statistics) o;
			return sum == that.sum &&
					Double.compare(that.average, average) == 0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(sum, average);
		}

		@Override
		public String toString() {
			return "Statistics {" +
					"sum=" + sum +
					", average=" + average +
					'}';
		}

	}

	public static class Range<T> {

		private final T min;
		private final T max;

		private Range(T min, T max) {
			this.min = requireNonNull(min);
			this.max = requireNonNull(max);
		}

		public static <T> Range<T> of(T min, T max) {
			return new Range<T>(min, max);
		}

		public static <T> Optional<Range<T>> ofOptional(Optional<T> min, Optional<T> max) {
			if (min.isEmpty() || max.isEmpty())
				return Optional.empty();
			return Optional.of(new Range<T>(min.get(), max.get()));
		}

		public T min() {
			return min;
		}

		public T max() {
			return max;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Range<?> range = (Range<?>) o;
			return min.equals(range.min) &&
					max.equals(range.max);
		}

		@Override
		public int hashCode() {
			return Objects.hash(min, max);
		}

		@Override
		public String toString() {
			return "Range [" + min + ", " + max + ']';
		}

	}

}
