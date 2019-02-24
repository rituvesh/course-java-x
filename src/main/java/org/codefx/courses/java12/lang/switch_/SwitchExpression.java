package org.codefx.courses.java12.lang.switch_;

import static org.codefx.courses.java12.lang.switch_.UnitSystem.IMPERIAL;
import static org.codefx.courses.java12.lang.switch_.UnitSystem.METRIC;

public class SwitchExpression {

	/*
	 * TASK: Implement the following methods so the tests in `SwitchExpressionTests` pass.
	 */

	public static UnitSystem convert(Country country) {
		// HINT: use switch expression and arrow form
		// OBSERVE: you don't need a `default` clause
		return switch (country) {
			case GERMANY -> METRIC;
			case VIETNAM -> METRIC;
			case USA -> IMPERIAL;
			case UNKNOWN -> throw new IllegalArgumentException();
		};
	}

}
