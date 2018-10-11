package org.codefx.courses.java11.api.http2;

import org.codefx.courses.java11.api.http2.PageSearch.Result;
import org.codefx.courses.java11.api.http2.PageSearch.Search;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class WikipediaSearch {

	/*
	 * TASK: Implement `createHttpClient`.
	 *
	 * TASK: Implement `BlockingPageSearch::search` by calling `HttpClient::send`.
	 *
	 * TASK: Implement `AsyncPageSearch::search` by calling `HttpClient::sendAsync`.
	 *
	 * TASK: Implement `ReactivePageSearch::search` by subscribing to the response body
	 *       and finishing as soon as the search term is first encountered.
	 */

	private static final List<String> URLS = List.of(
			"https://en.wikipedia.org/wiki/List_of_compositions_by_Franz_Schubert",
			"https://en.wikipedia.org/wiki/2018_in_American_television",
			"https://en.wikipedia.org/wiki/List_of_compositions_by_Johann_Sebastian_Bach",
			"https://en.wikipedia.org/wiki/List_of_Australian_treaties",
			"https://en.wikipedia.org/wiki/2016%E2%80%9317_Coupe_de_France_Preliminary_Rounds",
			"https://en.wikipedia.org/wiki/Timeline_of_the_war_in_Donbass_(April%E2%80%93June_2018)",
			"https://en.wikipedia.org/wiki/List_of_giant_squid_specimens_and_sightings",
			"https://en.wikipedia.org/wiki/List_of_members_of_the_Lok_Sabha_(1952%E2%80%93present)",
			"https://en.wikipedia.org/wiki/1919_New_Year_Honours",
			"https://en.wikipedia.org/wiki/List_of_International_Organization_for_Standardization_standards");

	private static final String SEARCH_STRING = "Foo";

	public static void main(String[] args) {
		List<Search> searches = URLS.stream()
				.map(url -> URI.create(url))
				.map(url -> new Search(url, SEARCH_STRING))
				.collect(toList());

		HttpClient httpClient = createHttpClient();

		search(new BlockingPageSearch(httpClient), searches);
		search(new AsyncPageSearch(httpClient), searches);
		search(new ReactivePageSearch(httpClient), searches);
	}

	private static HttpClient createHttpClient() {
		throw new RuntimeException("Not yet implemented.");
	}

	private static void search(PageSearch pageSearch, List<Search> searches) {
		System.out.println("\n---- " + pageSearch.getClass().getSimpleName() + " ----");
		long start = System.currentTimeMillis();

		long successCount = pageSearch
				.search(searches).stream()
				.filter(Result::contains)
				.count();

		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Search terms found " + successCount + "/" + searches.size());
		System.out.println("Took " + elapsedTime + " ms.");
	}

}
