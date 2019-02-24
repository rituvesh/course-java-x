package org.codefx.courses.java11.api.string;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class LinesStripRepeat {

	/*
	 * TASK: Implement `linewiseStripRepeat` so that it passes the tests
	 *       in `LineStripRepeatTests`
	 *
	 * HINT: The method needs to treat each line of the input separately,
	 *       removing leading and trailing whitespace and duplicating lines
	 * 		 that starts with an underscore. (Check the tests for details!)
	 */

	public static String linewiseStripRepeat(String input){
		return input.lines()
				.map(String::strip)
				.map(line -> line.startsWith("_") ? line.repeat(2) : line)
				.collect(joining("|"));
	}

}
