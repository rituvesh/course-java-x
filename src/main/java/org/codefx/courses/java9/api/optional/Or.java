package org.codefx.courses.java9.api.optional;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.util.Objects.requireNonNull;

public class Or {

	public static void main(String[] args) {
		Stream.of("123", "abc", "!§$", "%&/")
				.map(Or::load)
				.forEach(System.out::println);
	}

	public static Optional<Customer> load(String id) {
		// TASK:
		// chain the `from` methods and return the first non-empty Optional
		// (if one exists)
		return Optional.empty();
	}

	public static Optional<Customer> fromMemory(String id) {
		return isDigit(id.charAt(0))
				? Optional.of(new Customer(id))
				: Optional.empty();
	}

	public static Optional<Customer> fromDisk(String id) {
		return isLetter(id.charAt(0))
				? Optional.of(new Customer(id))
				: Optional.empty();
	}

	public static Optional<Customer> fromRemote(String id) {
		return new Random().nextBoolean()
				? Optional.of(new Customer(id))
				: Optional.empty();
	}

	static class Customer {

		final String id;

		Customer(String id) {
			this.id = requireNonNull(id);
		}

		@Override
		public String toString() {
			return "Customer (" + id + ")";
		}

	}

}
