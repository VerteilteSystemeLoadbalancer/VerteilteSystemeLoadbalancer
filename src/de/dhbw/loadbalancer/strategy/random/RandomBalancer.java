package de.dhbw.loadbalancer.strategy.random;

import de.dhbw.loadbalancer.strategy.BalanceStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RandomBalancer<T> extends BalanceStrategy<T> {

	private RandomSupplier randomSupplier;

	public T next() {
		int randomIndex = randomSupplier.nextRandom(0, list.size() - 1);
		return list.get(randomIndex);
	}

}