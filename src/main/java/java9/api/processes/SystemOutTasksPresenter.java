package java9.api.processes;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

import static java.util.function.UnaryOperator.identity;

/**
 * Prints the tasks to console.
 */
public class SystemOutTasksPresenter implements TasksPresenter {

	private final UnaryOperator<Tasks> tasksTransformer;

	private final int pidWidth;
	private final int commandWidth;
	private final int userWidth;
	private final int startTimeWidth;

	private final DateTimeFormatter startTimeFormatter;

	public SystemOutTasksPresenter(UnaryOperator<Tasks> tasksTransformer) {
		this.tasksTransformer = tasksTransformer;
		pidWidth = 6;
		userWidth = 10;
		startTimeWidth = 13;
		commandWidth = 40;

		startTimeFormatter = DateTimeFormatter
				.ofPattern("dd.MM., HH:mm")
				.withZone(ZoneId.systemDefault());
	}

	public SystemOutTasksPresenter() {
		this(identity());
	}

	@Override
	public void present(Tasks tasks) {
		printHeader();
		tasksTransformer
				.apply(tasks)
				.tasks()
				.map(this::formatAsRow)
				.forEach(this::printRow);
		System.out.println();
	}

	private void printHeader() {
		String header = String.format(
				"%s | %s | %s | %s",
				rightsize("PID", pidWidth),
				rightsize("COMMAND", commandWidth),
				rightsize("USER", userWidth),
				rightsize("START", startTimeWidth));
		System.out.println(header);
	}

	private String formatAsRow(Task task) {
		String startTime = task.start
				.map(startTimeFormatter::format)
				.orElse("");
		return String.format(
				"%s | %s | %s | %s",
				rightsize(task.pid, pidWidth),
				middlesize(task.command.orElse(""), commandWidth),
				rightsize(task.user.orElse(""), userWidth),
				rightsize(startTime, startTimeWidth));
	}

	private void printRow(String row) {
		System.out.println(row);
	}

	private static String rightsize(Object string, int width) {
		return abbreviate(rightPad(string.toString(), width, ' '), "...", width);
	}

	private static String middlesize(Object string, int width) {
		return abbreviateMiddle(rightPad(string.toString(), width, ' '), "...", width);
	}

	/*
	 * Code copied and adapted from APACHE COMMONS LANG 3 (org.apache.commons:commons-lang3:3.7),
	 * licensed under Apache License Version 2.0.
	 */

	private static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	private static String abbreviate(String str, String abbrevMarker, int maxWidth) {
		if (isEmpty(str) || isEmpty(abbrevMarker)) {
			return str;
		}

		int abbrevMarkerLength = abbrevMarker.length();
		int minAbbrevWidth = abbrevMarkerLength + 1;
		int minAbbrevWidthOffset = abbrevMarkerLength + abbrevMarkerLength + 1;

		if (maxWidth < minAbbrevWidth) {
			throw new IllegalArgumentException(String.format("Minimum abbreviation width is %d", minAbbrevWidth));
		}
		if (str.length() <= maxWidth) {
			return str;
		}
		if (maxWidth < minAbbrevWidthOffset) {
			throw new IllegalArgumentException(
					String.format("Minimum abbreviation width with offset is %d", minAbbrevWidthOffset));
		}
		if (maxWidth - abbrevMarkerLength < str.length()) {
			return abbrevMarker + abbreviate(str, abbrevMarker, maxWidth - abbrevMarkerLength);
		}
		return abbrevMarker + str.substring(str.length() - (maxWidth - abbrevMarkerLength));
	}

	public static String abbreviateMiddle(String str, String middle, int length) {
		if (isEmpty(str) || isEmpty(middle)) {
			return str;
		}

		if (length >= str.length() || length < middle.length() + 2) {
			return str;
		}

		int targetSting = length - middle.length();
		int startOffset = targetSting / 2 + targetSting % 2;
		int endOffset = str.length() - targetSting / 2;

		return str.substring(0, startOffset) +
				middle +
				str.substring(endOffset);
	}

	private static String rightPad(String str, int size, char padChar) {
		int pads = size - str.length();
		if (pads <= 0) {
			return str;
		}
		return str.concat(repeat(padChar, pads));
	}

	private static String repeat(char ch, int repeat) {
		char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
			buf[i] = ch;
		}
		return new String(buf);
	}

}
