package de.dhbw.loadbalancer.strategy.random;

public interface RandomSupplier {

	public int nextRandom(int min, int max);
	
}
