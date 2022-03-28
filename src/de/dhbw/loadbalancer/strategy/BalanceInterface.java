package de.dhbw.loadbalancer.strategy;

public interface BalanceInterface<T> {

	public T next();
	
}
