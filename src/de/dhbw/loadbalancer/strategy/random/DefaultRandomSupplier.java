package de.dhbw.loadbalancer.strategy.random;

public class DefaultRandomSupplier implements RandomSupplier {

	@Override
	public int nextRandom(int min, int max) {
		max++;
		return (int) ((Math.random() * (max - min) + min));
	}

}
