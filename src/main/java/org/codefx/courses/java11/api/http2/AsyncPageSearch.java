package org.codefx.courses.java11.api.http2;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AsyncPageSearch implements PageSearch {

	/*
	 * HINT: Xs aemx jsv e fyrgl sj gsqtpixefpi jyxyviw, ywi `GsqtpixefpiJyxyvi:nsmr` (Caesar 4)
	 *
	 * OBSERVE: The order in which results are reported changes from run to run. Why?
	 */

	private final HttpClient client;

	public AsyncPageSearch(HttpClient client) {
		this.client = client;
	}

	@Override
	public List<Result> search(List<Search> searches) {
		CompletableFuture[] futures = searches.stream()
				.map(search -> searchIndividual(search))
				.toArray(CompletableFuture[]::new);

		System.out.println("Waiting for the future to complete.");

		// wait for all future to done.
		CompletableFuture.allOf(futures).join();

		System.out.println("All Futures are done ");
		return Stream.of(futures)
				.map(this::getUnsafely)
				.collect(toList());

	}



	private CompletableFuture<Result> searchIndividual(Search search){
		var request = HttpRequest.newBuilder().GET().uri(search.url()).build();

		return client.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse -> HttpResponse.body())
				.thenApply(body	-> body.contains(search.term()))
				.thenApply(contains ->  Result.completed(search,contains))
				.exceptionally(exception -> Result.failed(search, exception));
	}

	// this may come in handy (you never know)
	private Result getUnsafely(CompletableFuture<Result> result) {
		try {
			return result.get();
		} catch (ExecutionException | InterruptedException ex) {
			throw new IllegalStateException("Future should have completed and handled errors.", ex);
		}
	}

}
