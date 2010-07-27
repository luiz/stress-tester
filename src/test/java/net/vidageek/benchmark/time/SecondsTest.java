package net.vidageek.benchmark.time;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import net.vidageek.benchmark.testing.EqualsTo;
import net.vidageek.benchmark.testing.SmallerThan;
import net.vidageek.benchmark.time.Miliseconds;
import net.vidageek.benchmark.time.Seconds;
import net.vidageek.benchmark.time.TimeUnit;

import org.hamcrest.Matcher;
import org.junit.Test;

public class SecondsTest {
	@Test
	public void secondsSinceCreatesASecondWithElapsedTime() throws Exception {
		long startTimeMillis = System.currentTimeMillis();
		Thread.sleep(1000);
		assertThat(Seconds.since(startTimeMillis), equalsTo(new Seconds(1)));
	}

	@Test
	public void secondsAreEqualWhenTheirValuesAreEqual() throws Exception {
		assertThat(new Seconds(10), equalsTo(new Seconds(10)));
		assertThat(new Seconds(10), is(new Seconds(10)));
	}

	@Test
	public void secondsAreNotEqualWhenTheirValuesAreDifferent() throws Exception {
		assertThat(new Seconds(10), not(equalsTo(new Seconds(11))));
		assertThat(new Seconds(10), not(is(new Seconds(11))));
	}

	@Test
	public void oneSecondIsSmallerThanTwoSeconds() throws Exception {
		assertThat(new Seconds(1), isSmallerThan(new Seconds(2)));
	}

	@Test
	public void oneSecondIsBiggerThanZeroSeconds() throws Exception {
		assertThat(new Seconds(1), not(isSmallerThan(new Seconds(0))));
	}

	@Test(expected = IllegalArgumentException.class)
	public void cannotCreateSecondsWithNegativeValue() throws Exception {
		new Seconds(-1);
	}

	@Test
	public void oneSecondIsAThousandMiliseconds() throws Exception {
		assertThat(new Seconds(1).asMiliseconds(), is(new Miliseconds(1000)));
		assertThat(new Seconds(1), equalsTo(new Miliseconds(1000)));
	}

	private Matcher<TimeUnit> isSmallerThan(Seconds seconds) {
		return new SmallerThan(seconds);
	}

	private Matcher<TimeUnit> equalsTo(TimeUnit seconds) {
		return new EqualsTo(seconds);
	}
}
