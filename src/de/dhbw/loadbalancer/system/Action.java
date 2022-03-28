package de.dhbw.loadbalancer.system;

public enum Action {

	CALCULATE, RESULT, ERROR, WAIT;

	@Override
	public String toString() {
		return name();
	}

}
