package org.codefx.courses.java11.api.http2;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlockingPageSearch implements PageSearch {

	private final HttpClient client;

	public BlockingPageSearch(HttpClient client) {
		this.client = client;
	}

	@Override
	public List<Result> search(List<Search> searches) {
		return searches.stream()
				.map(search -> Result.failed(search, new RuntimeException("Not yet implemented")))
				.collect(toList());
	}

}
