package org.codefx.courses.java10.lang.var;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Rules {

	/*
	 * TASK: Go through this class and try to replace every type with `var`.
	 *
	 * OBSERVE: See where it works and where it doesn't.
	 */

	public static void main(String[] args) throws Exception {
		Rules rules = new Rules();
		rules.var();

		rules.localVariables();
		rules.inLoop();
		rules.inTry();
		rules.incompleteInitializers();
		rules.polyExpressions();
	}

	private void var() {
		// var is still allowed as variable and method name, just not as class name
		String var = "var";
	}

	private void localVariables() throws IOException {
		URL codefx = new URL("https://codefx.org");
		URLConnection connection = codefx.openConnection();
		// reader needs to be closed, but not in the demo
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));

		reader.lines()
				.map(s -> s + " ")
				.dropWhile(s -> !s.contains("<article"))
				.takeWhile(s -> !s.contains("</article"))
				.forEach(System.out::println);
	}

	private void inLoop() {
		List<String> numbers = List.of("a", "b", "c");
		for (String nr : numbers)
			System.out.print(nr + " ");
		for (int i = 0; i < numbers.size(); i++)
			System.out.print(numbers.get(i) + " ");
		System.out.println();
	}

	private void inTry() {
		try (FileInputStream file = new FileInputStream(new File("no-such-file"))) {
			new BufferedReader(new InputStreamReader(file))
					.lines()
					.forEach(System.out::println);
		} catch (IOException ex) {
			// at least we tried
			System.out.println("There's actually no `no-such-file`. :)");
		}
	}

	private String duplicate(String s) {
		return s + s;
	}

	private void incompleteInitializers() {
		// lacking initializers
		String s;
		String f;
		f = "Foo";

		// declaring multiple variables
		int foo = 0, bar = 1;
	}

	private void polyExpressions() {
		// array literals
		int[] intses = { 0, 1, 2 };

		// lambdas
		Function<String, String> appendSpace = a -> a + " ";
	}

	private void programmingToTheInterface() {
		List<String> letters = new ArrayList<>();
		letters = new LinkedList<>();
	}

	private void withGenerics() {
		Optional<String> opt = Optional.empty();
		String s = opt.orElse("");
	}

	private void withLiterals() {
		long l = 42;
		l = Long.MAX_VALUE;

		// replace with `var` and ask your IDE what type they are
		// (e.g. CTRL+Q in IntelliJ)
		byte b = 42;
		short s = 42;
	}

}
