package de.dhbw.loadbalancer.strategy;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BalanceStrategy<T> implements BalanceInterface<T> {

	protected final List<T> list = new ArrayList<>();

	public int size() {
		return list.size();
	}

	public void updateList(List<T> newList) {
		list.clear();
		list.addAll(newList);
	}

}
