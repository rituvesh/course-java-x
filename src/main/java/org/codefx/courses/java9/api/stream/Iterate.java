package org.codefx.courses.java9.api.stream;

import java.util.stream.Stream;

public class Iterate {

	public static void main(String[] args) {
		forLoopUpTo(5).forEach(System.out::println);
		stringsUpToLength(5).forEach(System.out::println);
	}

	public static Stream<Integer> forLoopUpTo(int max) {
		// TASK:
		// use Stream::iterate to implement `for (int i = 0; i <= max; i++)
		return Stream.empty();
	}

	public static Stream<String> stringsUpToLength(int maxLength) {
		// TASK:
		// use Stream::iterate to create a stream of random strings,
		// starting with the empty string and increasing in length by one
		// until `maxLength` is reached
		return Stream.empty();
	}

}
