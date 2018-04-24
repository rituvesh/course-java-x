package java9.api.processes;

import java.util.List;
import java.util.stream.Stream;

/**
 * Container for a set of tasks as collected at one point in time. Immutable.
 */
public class Tasks {

	private final List<Task> tasks;

	public Tasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public static Tasks all() {
		return new Tasks(List.of());
	}

	public static Tasks of(List<Task> tasks) {
		return new Tasks(tasks);
	}

	public Stream<Task> tasks() {
		return tasks.stream();
	}

}
