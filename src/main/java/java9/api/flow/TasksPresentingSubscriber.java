package java9.api.flow;

import java9.api.processes.Tasks;
import java9.api.processes.TasksPresenter;

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

	public TasksPresentingSubscriber(TasksPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		// TIP: Store the subscription and use it to request new `Tasks` instances
	}

	@Override
	public void onNext(Tasks tasks) {

	}

	@Override
	public void onError(Throwable throwable) {

	}

	@Override
	public void onComplete() {

	}

}
