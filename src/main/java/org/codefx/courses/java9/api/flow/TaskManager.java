package org.codefx.courses.java9.api.flow;

import org.codefx.courses.java9.api.processes.SystemOutTasksPresenter;
import org.codefx.courses.java9.api.processes.Task;
import org.codefx.courses.java9.api.processes.Tasks;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TaskManager {

	/*
	 * TASK: Implement the empty methods in `TaskPresentingSubscriber` so that it presents
	 *       received `Tasks` instances with the `presenter`.
	 *
	 * TASK: Implement the empty methods in `TasksPublisher` so that it publishes `Tasks`
	 *
	 * OBSERVE: Run the `main` method to observe the results.
	 *
	 * TASK: Implement the empty methods in `TasksTransformer` so that it can act as a filter
	 *       between `TasksPublisher` and `TaskPresentingSubscriber`. Uncomment the declaration
	 *       and insert the filter into the pipeline.
	 *
	 * OBSERVE: Run the `main` method to observe the results.
	 */

	public static void main(String[] args) {
		TasksPublisher taskPublisher = new TasksPublisher();
//		TasksTransformer taskFilter = new TasksTransformer(TaskManager::filterTasks);
		TasksPresentingSubscriber presenter = new TasksPresentingSubscriber(new SystemOutTasksPresenter());

		taskPublisher.subscribe(presenter);

		taskPublisher.publishAtFixedRate(1, TimeUnit.SECONDS);
		taskPublisher.shutdownAfter(10, TimeUnit.SECONDS);
	}

	private static Tasks filterTasks(Tasks tasks) {
		List<Task> filteredTasks = tasks.tasks()
				.filter(task -> task.user.isPresent())
				.filter(task -> !task.user.orElse("").equals("root"))
				.sorted(comparing((Task task) -> task.start.orElse(Instant.EPOCH)).reversed())
				.limit(20)
				.collect(toList());
		return Tasks.of(filteredTasks);
	}

}
