package de.dhbw.loadbalancer.system.queue;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {

	private List<T> list = new ArrayList<>();

	public synchronized void add(T s) {
		list.add(s);
		notifyAll();
	}

	private T current;

	public synchronized T next() {
		if (current != null) {
			list.remove(current);
		}
		while (list.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		current = list.get(0);
		return current;
	}

	public int size() {
		return list.size();
	}

}