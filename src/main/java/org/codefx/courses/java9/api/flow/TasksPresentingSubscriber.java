package org.codefx.courses.java9.api.flow;

import org.codefx.courses.java9.api.processes.Tasks;
import org.codefx.courses.java9.api.processes.TasksPresenter;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * A {@link Subscriber} that wraps a {@link TasksPresenter} and uses it
 * to present all {@link Tasks} it receives.
 *
 * Not thread-safe.
 */
public class TasksPresentingSubscriber implements Subscriber<Tasks> {

	private final TasksPresenter presenter;
	private Subscription subscription;

	public TasksPresentingSubscriber(TasksPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		// TIP: Store the subscription and use it to request new `Tasks` instances
		System.out.println(" > subscribed");
		this.subscription = subscription;
		System.out.println(" > request next");
		this.subscription.request(1);
	}

	@Override
	public void onNext(Tasks tasks) {
		System.out.println(" > next tasks");
		presenter.present(tasks);
		System.out.println(" > request next");
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println(" > error occurred");
		System.out.println(throwable);
	}

	@Override
	public void onComplete() {
		System.out.println(" > printing completed");
	}

}
