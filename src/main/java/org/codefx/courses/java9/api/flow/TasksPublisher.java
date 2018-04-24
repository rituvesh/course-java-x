package org.codefx.courses.java9.api.flow;

import org.codefx.courses.java9.api.processes.Tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Publishes {@link Tasks} following a specified schedule.
 */
public class TasksPublisher extends SimplePublisher<Tasks> {

	private final ScheduledExecutorService scheduler;

	public TasksPublisher(ScheduledExecutorService scheduler) {
		this.scheduler = scheduler;
	}

	public TasksPublisher() {
		this(Executors.newScheduledThreadPool(1));
	}

	public void publishAtFixedRate(int period, TimeUnit unit) {
		scheduler.scheduleAtFixedRate(this::publish, 0, period, unit);
	}

	private void publish() {
		try {
			Tasks tasks = Tasks.all();
			publishItem(tasks);
		} catch (Throwable throwable) {
			publishError(throwable);
		}
	}

	public void shutdownAfter(int period, TimeUnit unit) {
		scheduler.schedule(this::shutdown, period, unit);
	}

	private void shutdown() {
		scheduler.shutdown();
		publishCompletion();
	}

	@Override
	void itemsRequested(long totalRequested) {
		// since items are created per schedule, there's nothing to be done if new items get requested
	}

}
