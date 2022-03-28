package de.dhbw.loadbalancer.strategy.roundrobin;

import de.dhbw.loadbalancer.strategy.BalanceStrategy;

public class RoundRobin<T> extends BalanceStrategy<T> {

	private int currentIndex = 0;

	public T next() {
		T res = list.get(currentIndex);
		currentIndex = (currentIndex + 1) % list.size();
		return res;
	}

	public int size() {
		return list.size();
	}

}