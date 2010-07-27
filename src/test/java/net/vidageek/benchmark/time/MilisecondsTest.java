package net.vidageek.benchmark.time;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import net.vidageek.benchmark.testing.EqualsTo;
import net.vidageek.benchmark.testing.SmallerThan;
import net.vidageek.benchmark.time.Miliseconds;
import net.vidageek.benchmark.time.TimeUnit;

import org.hamcrest.Matcher;
import org.junit.Test;

public class MilisecondsTest {
	@Test
	public void milisecondsAreEqualWhenTheirValuesAreEqual() throws Exception {
		assertThat(new Miliseconds(10), equalsTo(new Miliseconds(10)));
		assertThat(new Miliseconds(10), is(new Miliseconds(10)));
	}

	@Test
	public void milisecondsAreNotEqualWhenTheirValuesAreDifferent() throws Exception {
		assertThat(new Miliseconds(10), not(equalsTo(new Miliseconds(11))));
		assertThat(new Miliseconds(10), not(is(new Miliseconds(11))));
	}

	@Test
	public void oneMilisecondIsSmallerThanTwoMiliseconds() throws Exception {
		assertThat(new Miliseconds(1), isSmallerThan(new Miliseconds(2)));
	}

	@Test
	public void oneMilisecondIsBiggerThanZeroMiliseconds() throws Exception {
		assertThat(new Miliseconds(1), not(isSmallerThan(new Miliseconds(0))));
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateMilisecondsWithNegativeValue() throws Exception {
		new Miliseconds(-1);
	}

	private Matcher<TimeUnit> isSmallerThan(TimeUnit other) {
		return new SmallerThan(other);
	}

	private Matcher<TimeUnit> equalsTo(TimeUnit other) {
		return new EqualsTo(other);
	}
}
