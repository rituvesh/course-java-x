import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Echo {

	/*
	 * TASK: Execute this file with the `java` command. Try to pass arguments and input ot it.
	 *
	 * TASK: Rename the file to `echo` and execute that with the `java` command.
	 *
	 * TASK: If you are on a Unix-based system, add a shebang line, make the file execubable,
	 *       and execute it directly.
	 */

	public static void main(String[] args) throws IOException {
		System.out.println("--- ARGS ---");
		Stream.of(args).forEach(System.out::println);
		System.out.println("--- INPUT ---");
		readInput().forEach(System.out::println);
	}

	private static Stream<String> readInput() throws IOException {
		var reader = new BufferedReader(new InputStreamReader(System.in));
		if (!reader.ready())
			return Stream.empty();
		else
			return reader.lines();
	}

}
