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
		return searches.stream()
				.map(search -> Result.failed(search, new RuntimeException("Not yet implemented")))
				.collect(toList());
	}

}
