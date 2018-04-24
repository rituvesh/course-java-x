package org.codefx.courses.java9.api.processes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Container for a set of tasks as collected at one point in time. Immutable.
 */
public class Tasks {

	private final List<Task> tasks;

	public Tasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public static Tasks all() {
		List<Task> tasks = ProcessHandle
				.allProcesses()
				.map(Task::fromProcessHandle)
				.collect(toList());
		return new Tasks(tasks);
	}

	public static Tasks of(List<Task> tasks) {
		return new Tasks(tasks);
	}

	public Stream<Task> tasks() {
		return tasks.stream();
	}

}
