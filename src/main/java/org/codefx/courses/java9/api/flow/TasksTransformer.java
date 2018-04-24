package org.codefx.courses.java9.api.flow;

import org.codefx.courses.java9.api.processes.Tasks;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;

/**
 * A {@link Publisher}/{@link Subscriber} that publishes the subscribed
 * {@link Tasks} after transforming them.
 */
public class TasksTransformer
		extends SimplePublisher<Tasks>
		implements Subscriber<Tasks>, Publisher<Tasks> {

	private final Function<Tasks, Tasks> tasksTransformer;
	private Subscription subscription;

	public TasksTransformer(Function<Tasks, Tasks> tasksTransformer) {
		this.tasksTransformer = tasksTransformer;
	}

	// SUBSCRIBER

	// TIP: Acting as a subscriber, the transformer needs to handle a
	//      subscription to its publisher and request items.

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public void onNext(Tasks tasks) {
		publishItem(tasksTransformer.apply(tasks));
	}

	@Override
	public void onError(Throwable throwable) {
		publishError(throwable);
	}

	@Override
	public void onComplete() {
		publishCompletion();
	}

	// PUBLISHER

	// TIP: Acting as a publisher, the transformer needs to create a
	//      subscription for its subscribers and track how many items
	//      it has requested.
	//      It publishes the items it receives after transforming them.

	@Override
	void itemsRequested(long newlyRequested) {
		subscription.request(newlyRequested);
	}

}
