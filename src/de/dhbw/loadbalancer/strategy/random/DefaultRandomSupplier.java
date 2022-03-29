package de.dhbw.loadbalancer.strategy.random;

public class DefaultRandomSupplier implements RandomSupplier {

	@Override
	public int nextRandom(int min, int max) {
		int maxPlus = max + 1;
		return (int) ((Math.random() * (maxPlus - min) + min));
	}

}
