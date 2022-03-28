package de.dhbw.loadbalancer.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class RoundRobinTest {

	private RoundRobin<String> roundRobin = new RoundRobin<String>(Arrays.asList("abc", "test", "last"));
	
	@Test
	void testSize() {
		assertThat(roundRobin.size(), is(3));
	}
	
	@Test
	void testNext() {
		for (int i = 0; i < 20; i++) {
			assertThat(roundRobin.next(), is("abc"));
			assertThat(roundRobin.next(), is("test"));
			assertThat(roundRobin.next(), is("last"));
		}
	}

}
