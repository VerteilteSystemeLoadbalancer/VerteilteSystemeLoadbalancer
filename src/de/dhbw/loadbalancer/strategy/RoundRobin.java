package de.dhbw.loadbalancer.strategy;

import java.util.List;

public class RoundRobin<T> {

	private List<T> list;

	public RoundRobin(List<T> list) {
		this.list = list;
	}

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