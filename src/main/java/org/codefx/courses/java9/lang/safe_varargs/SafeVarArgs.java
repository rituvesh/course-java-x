package org.codefx.courses.java9.lang.safe_varargs;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static java.util.Arrays.stream;

class SafeVarArgs {

	public static void main(String[] args) {
		String[] bar = new UnSafeVarargs().replaceTwoNulls(", ", "hello", null);
		Arrays.stream(bar).forEach(System.out::print);
		System.out.println();
	}

	class InJava8 {

		// @java.lang.SafeVarArgs
		// With the annotation, it would not compile
		// because the method is private and non-final.
		// Without the annotation, we get a pesky warning.
		private <T> Optional<T> firstNonNull(T... args) {
			return stream(args)
					.filter(Objects::nonNull)
					.findFirst();
		}

	}

	class InJava9 {

		@SafeVarargs
		// here we go with Java 9
		private <T> Optional<T> firstNonNull(T... args) {
			return stream(args)
					.filter(Objects::nonNull)
					.findFirst();
		}

	}

	public static class UnSafeVarargs {

		// calling this results in a runtime exception
		private <T> T[] replaceTwoNulls(T value, T first, T second) {
			return replaceAllNulls(value, first, second);
		}

		private <T> T[] replaceAllNulls(T value, T... args) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] == null)
					args[i] = value;
			}
			return args;
		}

	}

}
