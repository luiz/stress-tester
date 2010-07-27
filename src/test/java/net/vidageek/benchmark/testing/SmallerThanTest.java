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
public class SmallerThanTest {

	private @Mock TimeUnit smaller, bigger;
	private SmallerThan matcher;

	@Before
	public void setUp() {
		matcher = new SmallerThan(bigger);
	}

	@Test
	public void matchesTimeUnitThatIsSmallerThanTheReceivedInConstructor() throws Exception {
		when(smaller.smallerThan(bigger)).thenReturn(true);
		when(bigger.smallerThan(smaller)).thenReturn(false);

		assertThat(matcher.matches(smaller), is(true));
	}

	@Test
	public void doesNotMatchTimeUnitThatIsBiggerThanTheReceivedInConstructor() throws Exception {
		when(smaller.smallerThan(bigger)).thenReturn(false);
		when(bigger.smallerThan(smaller)).thenReturn(true);

		assertThat(matcher.matches(smaller), is(false));
	}
}
