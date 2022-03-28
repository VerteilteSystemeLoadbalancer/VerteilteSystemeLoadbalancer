package de.dhbw.loadbalancer.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.dhbw.loadbalancer.strategy.random.RandomBalancer;

class RandomBalancerTest {

	private int nextMockedRandom = 1;
	private RandomBalancer<String> randomBalancer = new RandomBalancer<String>((min, max) -> nextMockedRandom);
	
	@BeforeEach
	public void setup() {
		randomBalancer.updateList(Arrays.asList("abc", "test", "last"));
	}
	
	@Test
	void testSize() {
		assertThat(randomBalancer.size(), is(3));
	}
	
	@Test
	void testNext() {
		nextMockedRandom = 0;
		assertThat(randomBalancer.next(), is("abc"));
		nextMockedRandom = 1;
		assertThat(randomBalancer.next(), is("test"));
		nextMockedRandom = 2;
		assertThat(randomBalancer.next(), is("last"));
	}

}
