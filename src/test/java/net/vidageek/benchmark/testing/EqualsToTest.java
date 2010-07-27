package net.vidageek.benchmark.testing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import net.vidageek.benchmark.time.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EqualsToTest {

	private @Mock TimeUnit unit, toEqualize;
	private EqualsTo matcher;

	@Before
	public void setUp() {
		matcher = new EqualsTo(toEqualize);
	}

	@Test
	public void matchesTwoEqualUnits() throws Exception {
		when(toEqualize.equalsTo(unit)).thenReturn(true);
		when(unit.equalsTo(toEqualize)).thenReturn(true);

		assertThat(matcher.matches(unit), is(true));
	}

	@Test
	public void doesNotMatchTwoUnequalUnits() throws Exception {
		when(toEqualize.equalsTo(unit)).thenReturn(false);
		when(unit.equalsTo(toEqualize)).thenReturn(false);

		assertThat(matcher.matches(unit), is(false));
	}
}
