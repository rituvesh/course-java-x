package org.codefx.courses.java9.api.collection_factory_methods;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class CollectionFactories {

	/*
	 * TASK: In `createCollections`, initialize the static fields with
	 *       collection factory methods and print the collections.
	 *
	 * TASK: In `collectionsAreImmutable`, try mutating the collections
	 *       (but remember to catch the exceptions) and observe that
	 *       immutability is not expressed in the type system (i.e.
	 *       mutating methods are still visible).
	 *
	 * TASK: In `orderIsUnstableAcrossRuns`, create a couple of sets and
	 *       convince yourself that iteration order is stable during a
	 *       program run; run it several times to see that it changes
	 *       across runs.
	 */

	private static List<String> list;
	private static Set<String> set;
	private static Map<String, Integer> map;

	public static void main(String[] args) {
		System.out.println("CREATING COLLECTIONS");
		createCollections();
		System.out.println();

		System.out.println("COLLECTIONS CAN NOT BE MUTATED");
		collectionsAreImmutable();
		System.out.println();

		System.out.println("ORDER IS UNSTABLE ACROSS RUNS");
		System.out.println(""
				+ "\t(If you look closely, you will see that the order of the following \n"
				+ "\t three sets is always the same but differs across run. This is so \n"
				+ "\t that you do not accidentally depend on the order.)");
		orderIsUnstableAcrossRuns();
		System.out.println();
	}

	public static void createCollections() {
		list = List.of("a", "b", "c");
		set = Set.of("a", "b", "c");
		map = Map.of(
				"one", 1,
				"two", 2,
				"three", 3);
		map = Map.ofEntries(
				Map.entry("one", 1),
				Map.entry("two", 2),
				Map.entry("three", 3));

		Stream.of(list, set, map).forEach(System.out::print);
		System.out.println();

	}

	public static void collectionsAreImmutable() {

		addToCollection(list, "d");
		addToCollection(set, "d");
		addToCollection(map.entrySet(), Map.entry("two", 2));
	}

	private static <T> void addToCollection(Collection<T> collection, T item) {
		try {
			collection.add(item);
			throw new IllegalStateException();
		} catch (UnsupportedOperationException ex) {
			System.out.println("Could not mutate " + collection);
		}
	}

	public static void orderIsUnstableAcrossRuns() {
		Stream.of(
				Set.of("a", "b", "c"),
				Set.of("a", "b", "c"),
				Set.of("a", "b", "c"))
				.forEach(System.out::println);

		System.out.println(Set.of("a","b","c","d", "e"));
		System.out.println(Set.of("a","b","c","d"));
		System.out.println(Set.of("a","b","c","d"));
		System.out.println(Set.of("a","b","c","d"));
		System.out.println(Set.of("a","b","c","d"));
		System.out.println(Set.of("a","b","c","d"));

	}

}
