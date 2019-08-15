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
public class TasksTransformer implements Subscriber<Tasks>, Publisher<Tasks> {

	private final Function<Tasks, Tasks> tasksTransformer;

	private Subscription subscription;
	private Subscriber subscriber;


	public TasksTransformer(Function<Tasks, Tasks> tasksTransformer) {
		this.tasksTransformer = tasksTransformer;
	}

	// SUBSCRIBER

	// TIP: Acting as a subscriber, the transformer needs to handle a
	//      subscription to its publisher and request items.

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(Tasks item) {
		Tasks tasks = tasksTransformer.apply(item);
		subscriber.onNext(tasks);



	}

	@Override
	public void onError(Throwable throwable) {

	}

	@Override
	public void onComplete() {

	}

	// PUBLISHER

	// TIP: Acting as a publisher, the transformer needs to create a
	//      subscription for its subscribers and track how many items
	//      it has requested.
	//      It publishes the items it receives after transforming them.

	@Override
	public void subscribe(Subscriber<? super Tasks> subscriber) {

		this.subscriber =subscriber;
		subscriber.onSubscribe(new Subscription() {
			@Override
			public void request(long n) {
				subscription.request(n);
			}

			@Override
			public void cancel() {
				subscription.cancel();
			}
		});
	}
}
