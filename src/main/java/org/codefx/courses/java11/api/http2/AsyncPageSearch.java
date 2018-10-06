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
		return searches.stream()
				.map(search -> Result.failed(search, new RuntimeException("Not yet implemented")))
				.collect(toList());
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
