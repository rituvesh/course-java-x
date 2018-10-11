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
	 * NOTE: This exercise uses some classes with somewhat misleading names. `TaskPublisher`
	 *       and `TaskPresentingSubscriber` have the generic type `Tasks`, which means that
	 *       `TaskPublisher` publishes instances of `Tasks` and `TaskPresentingSubscriber`
	 *       consumes them. The misleading aspect is the plural name.
	 *       It seems natural to assume that the publisher publishes `Task` (singular!)
	 *       instances and that subscriber consumes `Task` instances. That may be the
	 *       more fitting approach in general, but isn't a good fit in this specific case.
	 *
	 *       First, a task manager like the one you're working on usually updates all
	 *       tasks at once (as opposed to one by one). Second, if the APIs would deal with
	 *       `Task` instances, the publisher would have to decide which individual process
	 *       to wrap into a `Task` and publish next - there is no obvious way to fo that.
	 *
	 *       So, in conclusion, from the perspective of the publisher and subscriber,
	 *       `Tasks` (plural!) is the indivisible unit they handle and if, for example
	 *       subscriber, requests one item, it will receive one `Tasks` instance.
	 *
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
		TasksPublisher tasksPublisher = new TasksPublisher();
//		TasksTransformer tasksFilter = new TasksTransformer(TaskManager::filterTasks);
		TasksPresentingSubscriber presenter = new TasksPresentingSubscriber(new SystemOutTasksPresenter());

		tasksPublisher.subscribe(presenter);

		tasksPublisher.publishAtFixedRate(1, TimeUnit.SECONDS);
		tasksPublisher.shutdownAfter(10, TimeUnit.SECONDS);
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
