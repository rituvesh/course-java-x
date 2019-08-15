package org.codefx.courses.java9.api.flow;

import org.codefx.courses.java9.api.processes.Tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Publishes {@link Tasks} following a specified schedule.
 */
public class TasksPublisher implements Publisher<Tasks> {



	private Subscriber subscriber;
	private int requestedItems;

	private final ScheduledExecutorService scheduler;

	public TasksPublisher(ScheduledExecutorService scheduler) {
		this.scheduler = scheduler;
	}

	public TasksPublisher() {
		this(Executors.newScheduledThreadPool(1));
	}

	@Override
	public void subscribe(Subscriber<? super Tasks> subscriber) {
		// TIP: Create a subscription to pass to the subscriber
		//      and make sure to track how many items are requested
		this.subscriber =subscriber;
  subscriber.onSubscribe(new Flow.Subscription() {
	  @Override
	  public void request(long n) {
		  requestedItems += n;
	  }

	  @Override
	  public void cancel() {

	  	TasksPublisher.this.subscriber = null;
		  requestedItems =0;

	  }
  });




	}

	public void publishAtFixedRate(int period, TimeUnit unit) {
		scheduler.scheduleAtFixedRate(this::publish, 0, period, unit);
	}

	private void publish() {
		// TIP: Publish a `Tasks` instance to the subscriber if more items are requested

		if (subscriber != null && requestedItems > 0) {
			subscriber.onNext(Tasks.all());
			requestedItems--;
		}

	}

	public void shutdownAfter(int period, TimeUnit unit) {
		scheduler.schedule(this::shutdown, period, unit);
	}

	private void shutdown() {
		scheduler.shutdown();
		// TIP: Let the subscriber know that publishing was completed

		subscriber.onComplete();
	}

}
