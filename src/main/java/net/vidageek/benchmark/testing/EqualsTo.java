package net.vidageek.benchmark.testing;

import net.vidageek.benchmark.time.TimeUnit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EqualsTo extends TypeSafeMatcher<TimeUnit> {

	private TimeUnit expected;

	public EqualsTo(TimeUnit expected) {
		this.expected = expected;
	}

	public void describeTo(Description description) {
		description.appendText("A TimeUnit equal to ");
		description.appendValue(expected);
	}

	@Override
	protected boolean matchesSafely(TimeUnit item) {
		return item.equalsTo(expected);
	}

}
