package org.codefx.courses.java10.lang.var;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Readability {

	/*
	 * TASK: Go through this class and replace types with `var`. For each method, discuss
	 *       with the developer next to you whether it improves readability.
	 *
	 * HINT: There's no need to look into the helper classes and methods - none of them do
	 *       anything useful or enlightening.
	 */

	public static void main(String[] args) throws Exception {
		Readability read = new Readability();

		read.lessRedundancy();
		read.alignedVariableNames();
		read.emphasizedVariableName();
	}

	private void lessRedundancy() throws IOException {
		URL codefx = new URL("https://codefx.org");
		URLConnection connection = codefx.openConnection();
		// reader needs to be closed, but not in the demo
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
	}

	private void alignedVariableNames() {
		No no = new No();
		AmountIncrease<BigDecimal> more = new BigDecimalAmountIncrease();
		HorizontalConnection<LinePosition, LinePosition> jumping =
				new HorizontalLinePositionConnection();
		Variable variable = new Constant(5);
		List<String> names = List.of("Max", "Maria");
	}

	private List<Drawing> moreVariables() {
		/*
		 * The code's original author used comments to mark important intermediate results
		 * of this stream pipeline. Why may she not have declared variables instead? Try
		 * it with and without `var` and see which variant you (and your neighbor) prefer.
		 */
		return Canvas
				.activeCanvas()
				.drawings()
				.filter(Drawing::isLine)
				.map(drawing -> (HorizontalConnection<LinePosition, LinePosition>) drawing) // now we have lines
				.filter(line -> length(line) == 7)
				.map(this::generateSquare) // now we have squares
				.map(this::createRandomColoredSquare)
				.map(this::createRandomBorderedSquare)
				.collect(toList());
	}

	private void emphasizedVariableName() {
		/*
		 * With type information, the horrible variable names seem bearable. If you use `var`,
		 * it becomes even more obvious that they need to be improved. Find better variable
		 * names and compare the original version with the one with `var` and better names.
		 * Which one do you (and your neighbor) prefer?
		 */
		Logger l = Logger.getInstance();

		LoginWindow lw = LoginWindow.acquire();
		Credentials cr = lw.getEnteredCredentials();
		String n = cr.getUserName();
		Optional<User> u = UserRepository.load(n);

		if (u.isPresent()) {
			LoginService s = LoginService.getInstance();
			User uu = u.orElseThrow();
			try {
				s.logIn(uu);
				l.debug("Login for " + n + " succeeded.");
				lw.displaySuccess();
			} catch (IllegalCredentialsException ex) {
				l.debug("Login for " + n + " failed.", ex);
				lw.displayFailure();
			}
		} else {
			l.debug("Login for " + n + " failed - no such user.");
			lw.displayFailure();
		}
	}

	// HELPER METHODS

	private int length(HorizontalConnection<LinePosition, LinePosition> line) {
		return 0;
	}

	private Tetragon<LinePosition, LinePosition, Point, Point> generateSquare(
			HorizontalConnection<LinePosition, LinePosition> linePositionLinePositionHorizontalConnection) {
		// in case you're looking: type inference for anonymous classes! :)
		return new Tetragon<>() { };
	}

	private Tetragon<LinePosition, LinePosition, Point, Point> createRandomColoredSquare(
			Tetragon<LinePosition, LinePosition, Point, Point> square) {
		return square;
	}

	private Tetragon<LinePosition, LinePosition, Point, Point> createRandomBorderedSquare(
			Tetragon<LinePosition, LinePosition, Point, Point> square) {
		return square;
	}

	// INNER CLASSES

	// - jumping variable names and more intermediate variables

	private static class No { }

	private interface AmountIncrease<T> { }

	private static class BigDecimalAmountIncrease implements AmountIncrease<BigDecimal> { }

	private static class LinePosition implements Drawing { }

	private static class Point implements Drawing { }

	private interface Drawing {

		default boolean isLine() {
			return false;
		}

	}

	private interface HorizontalConnection<T1 extends Drawing, T2 extends Drawing> extends Drawing { }

	private interface Tetragon<T1 extends Drawing, T2 extends Drawing, T3 extends Drawing, T4 extends Drawing>
			extends Drawing { }

	private static class HorizontalLinePositionConnection
			implements HorizontalConnection<LinePosition, LinePosition> {

		@Override
		public boolean isLine() {
			return true;
		}

	}

	private interface Variable { }

	private static class Constant implements Variable {

		public Constant(Number number) { }

	}

	private static class Canvas {

		public static Canvas activeCanvas() {
			return new Canvas();
		}

		public Stream<Drawing> drawings() {
			return Stream.empty();
		}

	}

	// - emphasized variable names

	private static class LoginWindow {

		public static LoginWindow acquire() {
			return new LoginWindow();
		}

		public Credentials getEnteredCredentials() {
			return new Credentials();
		}

		public void displaySuccess() { }

		public void displayFailure() { }

	}

	private static class Credentials {

		public String getUserName() {
			return "user";
		}

	}

	private static class User { }

	private static class UserRepository {

		public static Optional<User> load(String n) {
			return Optional.empty();
		}

	}

	private static class LoginService {

		public static LoginService getInstance() {
			return new LoginService();
		}

		public void logIn(User user) { }

	}

	private static class Logger {

		public static Logger getInstance() {
			return new Logger();
		}

		public void debug(String s) { }

		public void debug(String s, Throwable ex) { }

	}

	private static class IllegalCredentialsException extends RuntimeException { }

}
