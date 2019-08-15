package org.codefx.courses.java11.api.http2;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ReactivePageSearch implements PageSearch {

	/*
	 * HINT: Odqmfq m oxmee ftmf qjfqzpe `OaybxqfmnxqRgfgdq<Dqegxf>` mzp uybxqyqzfe `Egneodunqd<Efduzs>`;
	 *       bmee uf fa NapkTmzpxqde::rdayXuzqEgneodunqd (Caesar 12)
	 *
	 * OBSERVE: The reactive solution is faster than the async solution when the pages contain the search term;
	 *          otherwise it is slower.
	 */

	private final HttpClient client;

	public ReactivePageSearch(HttpClient client) {
		this.client = client;
	}

	@Override
	public List<Result> search(List<Search> searches) {
		CompletableFuture[] futures = searches.stream()
				.map(search -> search(search))
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(futures).join();

		return Stream.of(futures)
				.map(this::getUnsafely)
				.collect(toList());
	}

	private CompletableFuture<Result> search(Search search) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(search.url())
				.build();
		StringFinder finder = new StringFinder(search);
		client
				.sendAsync(request, BodyHandlers.fromLineSubscriber(finder))
				// forward errors that do not show up via response,
				// e.g. connection problems (try when offline)
				.exceptionally(ex -> {
					finder.onError(ex);
					return null;
				});
		return finder
				.exceptionally(ex -> Result.failed(search, ex));
	}

	private Result getUnsafely(CompletableFuture<Result> result) {
		try {
			return result.get();
		} catch (ExecutionException | InterruptedException ex) {
			throw new IllegalStateException("Future should have completed and handled errors.", ex);
		}
	}

	private static class StringFinder extends CompletableFuture<Result> implements Subscriber<String> {

		private final Search search;
		private Subscription subscription;

		private StringFinder(Search search) {
			this.search = search;
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			this.subscription = subscription;
			subscription.request(1);
		}

		@Override
		public void onNext(String line) {
			if (line.contains(search.term())) {
				complete(Result.completed(search, true));
				// cancelling the subscription causes the
				// `CompletableFuture<HttpResponse<Void>>`
				// to complete exceptionally, which would
				// fail this CompletableFuture;
				// hence, cancel after completion
				subscription.cancel();
			} else {
				subscription.request(1);
			}
		}

		@Override
		public void onError(Throwable ex) {
			completeExceptionally(ex);
		}

		@Override
		public void onComplete() {
			complete(Result.completed(search, false));
		}

	}

}
