package org.codefx.courses.java11.api.string;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class LinesStripRepeatTests {

	@Test
	void emptyString() {
		String result = LinesStripRepeat.linewiseStripRepeat("");

		assertThat(result).isEmpty();
	}

	@Test
	void singleLine_notRepeated() {
		String result = LinesStripRepeat.linewiseStripRepeat("line");

		assertThat(result).isEqualTo("line");
	}

	@Test
	void singleLineWithTrailingWhitespace_strippedNotRepeated() {
		String result = LinesStripRepeat.linewiseStripRepeat(" li ne ");

		assertThat(result).isEqualTo("li ne");
	}

	@Test
	void underscoredLine_repeated() {
		String result = LinesStripRepeat.linewiseStripRepeat("_line");

		assertThat(result).isEqualTo("_line_line");
	}

	@Test
	void underscoredLineWithTrailingWhitespace_strippedAndRepeated() {
		String result = LinesStripRepeat.linewiseStripRepeat(" _li ne ");

		assertThat(result).isEqualTo("_li ne_li ne");
	}

	@Test
	void multiline_joined() {
		String input = ""
				+ "one line\n"
				+ "another line\n"
				+ "last line";
		String result = LinesStripRepeat.linewiseStripRepeat(input);

		assertThat(result).isEqualTo("one line|another line|last line");
	}

	@Test
	void multilineWithWhitespace_strippedAndJoined() {
		String input = ""
				+ " one line \n"
				+ " another line \n"
				+ " last line ";
		String result = LinesStripRepeat.linewiseStripRepeat(input);

		assertThat(result).isEqualTo("one line|another line|last line");
	}

	@Test
	void underscoredMultiline_repeatedJoined() {
		String input = ""
				+ "one line\n"
				+ "_underscored line\n"
				+ "last line";
		String result = LinesStripRepeat.linewiseStripRepeat(input);

		assertThat(result).isEqualTo("one line|_underscored line_underscored line|last line");
	}

	@Test
	void underscoredMultilineWithWhitespace_strippedRepeatedJoined() {
		String input = ""
				+ " one line \n"
				+ " _underscored line \n"
				+ " last line ";
		String result = LinesStripRepeat.linewiseStripRepeat(input);

		assertThat(result).isEqualTo("one line|_underscored line_underscored line|last line");
	}

}
