package de.dhbw.loadbalancer.system;

import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;

public class CalculationServer extends NetworkConnection {

	public CalculationServer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("CalculationServer hat empfangen: " + message);
	}

}
