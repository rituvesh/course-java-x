package org.codefx.courses.java12.lang.switch_;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.codefx.courses.java12.lang.switch_.Country.GERMANY;
import static org.codefx.courses.java12.lang.switch_.Country.UNKNOWN;
import static org.codefx.courses.java12.lang.switch_.Country.USA;
import static org.codefx.courses.java12.lang.switch_.Country.VIETNAM;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.IMPERIAL;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.METRIC;

class SwitchExpressionTests {

	@Test
	void convert_Germany() {
		assertThat(SwitchExpression.convert(GERMANY)).isEqualTo(METRIC);
	}

	@Test
	void convert_Vietnam() {
		assertThat(SwitchExpression.convert(VIETNAM)).isEqualTo(METRIC);
	}

	@Test
	void convert_USA() {
		assertThat(SwitchExpression.convert(USA)).isEqualTo(IMPERIAL);
	}

	@Test
	void convert_Unknown() {
		assertThatThrownBy(() -> SwitchExpression.convert(UNKNOWN))
				.isInstanceOf(IllegalArgumentException.class);
	}

}
