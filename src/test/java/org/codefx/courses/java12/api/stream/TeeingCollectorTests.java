package org.codefx.courses.java12.api.stream;

import org.codefx.courses.java12.api.stream.TeeingCollector.Range;
import org.codefx.courses.java12.api.stream.TeeingCollector.Statistics;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class TeeingCollectorTests {

	@Test
	void computeStatistics_emptyStream() {
		Stream<Integer> numbers = Stream.empty();
		Statistics stats = TeeingCollector.computeStats(numbers);

		assertThat(stats).isEqualTo(Statistics.of(0, 0));
	}

	@Test
	void computeStatistics_smallStream() {
		Stream<Integer> numbers = Stream.of(1, 3, 5);
		Statistics stats = TeeingCollector.computeStats(numbers);

		assertThat(stats).isEqualTo(Statistics.of(9, 3));
	}

	@Test
	void computeStatistics_largeStream() {
		Stream<Integer> numbers = IntStream.rangeClosed(0, 10_000).mapToObj(i -> i);
		Statistics stats = TeeingCollector.computeStats(numbers);

		assertThat(stats).isEqualTo(Statistics.of(50_005_000, 5000));
	}

	@Test
	void computeRange_emptyStream() {
		Stream<String> words = Stream.empty();
		Optional<Range<String>> range = TeeingCollector.computeRangeFromMinToMax(words);

		assertThat(range).isEmpty();
	}

	@Test
	void computeRange_smallStream() {
		Stream<String> words = Stream.of("A", "B", "C");
		Optional<Range<String>> range = TeeingCollector.computeRangeFromMinToMax(words);

		assertThat(range).contains(Range.of("A", "C"));
	}

	@Test
	void computeRange_largeStream() {
		Stream<String> words = ThreadLocalRandom.current()
				.ints(0, 26)
				.limit(100)
				.mapToObj(i -> (char) ('a' + i))
				.map(c -> "" + c);
		Optional<Range<String>> range = TeeingCollector.computeRangeFromMinToMax(words);

		assertThat(range).contains(Range.of("a", "z"));
	}

}
