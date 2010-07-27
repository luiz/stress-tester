package net.vidageek.benchmark.testing;

import net.vidageek.benchmark.time.TimeUnit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public final class SmallerThan extends TypeSafeMatcher<TimeUnit> {
	private final TimeUnit bigger;

	public SmallerThan(TimeUnit bigger) {
		this.bigger = bigger;
	}

	public void describeTo(Description description) {
		description.appendText("A TimeUnit smaller than ");
		description.appendValue(bigger);
	}

	@Override
	protected boolean matchesSafely(TimeUnit item) {
		return item.smallerThan(bigger);
	}
}