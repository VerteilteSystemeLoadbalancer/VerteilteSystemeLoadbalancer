package de.dhbw.loadbalancer.system;

public enum Response {

	ERROR, WAIT;

	@Override
	public String toString() {
		return name();
	}

}
