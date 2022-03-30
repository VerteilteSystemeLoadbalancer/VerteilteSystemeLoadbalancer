package de.dhbw.loadbalancer.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.loadbalancer.strategy.random.RandomBalancer;

public class RandomBalancerTest {

	private int nextMockedRandom = 1;
	private RandomBalancer<String> randomBalancer = new RandomBalancer<String>((min, max) -> nextMockedRandom);

	@Before
	public void setup() {
		randomBalancer.updateList(Arrays.asList("abc", "test", "last"));
	}

	@Test
	public void testSize() {
		assertThat(randomBalancer.size(), is(3));
	}

	@Test
	public void testNext() {
		nextMockedRandom = 0;
		assertThat(randomBalancer.next(), is("abc"));
		nextMockedRandom = 1;
		assertThat(randomBalancer.next(), is("test"));
		nextMockedRandom = 2;
		assertThat(randomBalancer.next(), is("last"));
	}

}
