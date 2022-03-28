package de.dhbw.loadbalancer.network;

import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;

public class CalculationServer extends NetworkConnection {

	public CalculationServer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("CalculationServer hat empfangen: " + message);
	}

}
