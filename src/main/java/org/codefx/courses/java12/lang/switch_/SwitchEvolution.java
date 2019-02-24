package org.codefx.courses.java12.lang.switch_;

import java.util.function.Consumer;

import static org.codefx.courses.java12.lang.switch_.UnitSystem.IMPERIAL;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.METRIC;

class SwitchEvolution {

	/*
	 * TASK: Implement the following methods so the tests in `SwitchEvolutionTests` pass.
	 *
	 * HINT: In each exercise, try all combinations of switch expressions vs statements
	 *       and arrow form vs colon form.
	 */

	public static UnitSystem convertWithMultipleCaseLabels(Country country) {
		// HINT: use multiple case labels
		return switch (country) {
			case GERMANY, VIETNAM -> METRIC;
			case USA -> IMPERIAL;
			case UNKNOWN -> throw new IllegalArgumentException();
		};
	}

	public static void logUnitSystemMessage(Country country, Consumer<String> logger) {
		// OBSERVE: even without `break`, `return`, or `{}`, there is no fall-through
		switch (country) {
			case GERMANY, VIETNAM -> logger.accept(METRIC.toString());
			case USA -> logger.accept(IMPERIAL.toString());
			case UNKNOWN -> throw new IllegalArgumentException();
		};
	}

	public static UnitSystem convertAndLogUnitSystemMessage(Country country, Consumer<String> logger) {
		// OBSERVE: in arrow form, try to `return` a value
		return switch (country) {
			case GERMANY, VIETNAM -> {
				logger.accept(METRIC.toString());
				break METRIC;
			}
			case USA -> {
				logger.accept(IMPERIAL.toString());
				break IMPERIAL;
			}
			case UNKNOWN -> throw new IllegalArgumentException();
		};
	}

}
