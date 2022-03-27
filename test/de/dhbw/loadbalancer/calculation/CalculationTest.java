package de.dhbw.loadbalancer.calculation;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

class CalculationTest {

	@Test
	void testZero() {
		Calculation calculation = new Calculation("test", 0);
		assertThat(calculation.calculate(), is("test"));
	}

	@Test
	void testOneTime() {
		Calculation calculation = new Calculation("test", 1);
		assertThat(calculation.calculate(), is("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"));
	}

	@Test
	void testTwice() {
		Calculation calculation = new Calculation("test", 2);
		assertThat(calculation.calculate(), is("7b3d979ca8330a94fa7e9e1b466d8b99e0bcdea1ec90596c0dcc8d7ef6b4300c"));
	}
	
	@Test
	void test500() {
		Calculation calculation = new Calculation("test", 500000);
		assertThat(calculation.calculate(), is("c3b1e75452b416a9546275849d38c1f23999db824970d22f928066d6b63d55f6"));
	}

}
