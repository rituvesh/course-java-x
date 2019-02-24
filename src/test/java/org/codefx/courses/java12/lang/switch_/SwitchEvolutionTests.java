package org.codefx.courses.java12.lang.switch_;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.codefx.courses.java12.lang.switch_.Country.GERMANY;
import static org.codefx.courses.java12.lang.switch_.Country.UNKNOWN;
import static org.codefx.courses.java12.lang.switch_.Country.USA;
import static org.codefx.courses.java12.lang.switch_.Country.VIETNAM;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.IMPERIAL;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.METRIC;

class SwitchEvolutionTests {

	@Nested
	class ConvertWithMultipleCaseLabels {

		@Test
		void convert_Germany() {
			assertThat(SwitchEvolution.convertWithMultipleCaseLabels(GERMANY))
					.isEqualTo(METRIC);
		}

		@Test
		void convert_Vietnam() {
			assertThat(SwitchEvolution.convertWithMultipleCaseLabels(VIETNAM))
					.isEqualTo(METRIC);
		}

		@Test
		void convert_USA() {
			assertThat(SwitchEvolution.convertWithMultipleCaseLabels(USA))
					.isEqualTo(IMPERIAL);
		}

		@Test
		void convert_Unknown() {
			assertThatThrownBy(() -> SwitchEvolution
					.convertWithMultipleCaseLabels(UNKNOWN))
					.isInstanceOf(IllegalArgumentException.class);
		}

	}

	@Nested
	class LogUnitSystemMessage {

		@Test
		void convert_Germany() {
			List<String> messages = new ArrayList<>();
			SwitchEvolution.logUnitSystemMessage(GERMANY, messages::add);

			assertThat(messages).containsExactly(METRIC.toString());
		}

		@Test
		void convert_Vietnam() {
			List<String> messages = new ArrayList<>();
			SwitchEvolution.logUnitSystemMessage(VIETNAM, messages::add);

			assertThat(messages).containsExactly(METRIC.toString());
		}

		@Test
		void convert_USA() {
			List<String> messages = new ArrayList<>();
			SwitchEvolution.logUnitSystemMessage(USA, messages::add);

			assertThat(messages).containsExactly(IMPERIAL.toString());
		}

		@Test
		void convert_Unknown() {
			List<String> messages = new ArrayList<>();

			assertThatThrownBy(() -> SwitchEvolution
					.logUnitSystemMessage(UNKNOWN, messages::add))
					.isInstanceOf(IllegalArgumentException.class);
		}

	}

	@Nested
	class ConvertAndLogUnitSystemMessage {

		@Test
		void convert_Germany() {
			List<String> messages = new ArrayList<>();
			UnitSystem unitSystem = SwitchEvolution
					.convertAndLogUnitSystemMessage(GERMANY, messages::add);

			assertThat(unitSystem).isEqualTo(METRIC);
			assertThat(messages).containsExactly(METRIC.toString());
		}

		@Test
		void convert_Vietnam() {
			List<String> messages = new ArrayList<>();
			UnitSystem unitSystem = SwitchEvolution
					.convertAndLogUnitSystemMessage(VIETNAM, messages::add);

			assertThat(unitSystem).isEqualTo(METRIC);
			assertThat(messages).containsExactly(METRIC.toString());
		}

		@Test
		void convert_USA() {
			List<String> messages = new ArrayList<>();
			UnitSystem unitSystem = SwitchEvolution
					.convertAndLogUnitSystemMessage(USA, messages::add);

			assertThat(unitSystem).isEqualTo(IMPERIAL);
			assertThat(messages).containsExactly(IMPERIAL.toString());
		}

		@Test
		void convert_Unknown() {
			List<String> messages = new ArrayList<>();

			assertThatThrownBy(() -> SwitchEvolution
					.convertAndLogUnitSystemMessage(UNKNOWN, messages::add))
					.isInstanceOf(IllegalArgumentException.class);
		}

	}

}
