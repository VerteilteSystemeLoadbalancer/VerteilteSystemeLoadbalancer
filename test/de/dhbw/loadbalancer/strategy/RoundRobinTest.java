package de.dhbw.loadbalancer.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.loadbalancer.strategy.roundrobin.RoundRobin;

public class RoundRobinTest {

	private RoundRobin<String> roundRobin = new RoundRobin<String>();

	@Before
	public void init() {
		roundRobin.updateList(Arrays.asList("abc", "test", "last"));
	}

	@Test
	public void testSize() {
		assertThat(roundRobin.size(), is(3));
	}

	@Test
	public void testNext() {
		for (int i = 0; i < 20; i++) {
			assertThat(roundRobin.next(), is("abc"));
			assertThat(roundRobin.next(), is("test"));
			assertThat(roundRobin.next(), is("last"));
		}
	}

}
