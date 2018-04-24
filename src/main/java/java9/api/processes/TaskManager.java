package java9.api.processes;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TaskManager {

	/*
	 * TASK: Create a static factory method in `Task` that creates a `Task` from a `ProcessHandle`.
	 *
	 * TASK: In `Tasks::all`, get all `ProcessHandle`s, create a list of `Task`s, and instantiate
	 *       a `Tasks` object with it.
	 *
	 * OBSERVE: Run the `main` method to observe the results.
	 */

	private final TasksPresenter presenter;

	public TaskManager(TasksPresenter presenter) {
		this.presenter = presenter;
	}

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager(new SystemOutTasksPresenter(TaskManager::filterTasks));
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(taskManager::presentTasks, 1, 1, TimeUnit.SECONDS);
		scheduler.schedule(scheduler::shutdown, 10, TimeUnit.SECONDS);
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

	private void presentTasks() {
		presenter.present(Tasks.all());
	}

}
