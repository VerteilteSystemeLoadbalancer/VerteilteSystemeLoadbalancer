package de.dhbw.loadbalancer.network;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;

public class Loadbalancer extends NetworkConnection {

	public Loadbalancer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("Loadbalancer hat empfangen: " + message);
	}

	private List<NetworkAddress> calculationServer = new ArrayList<>();

	public void addCalculationServer(NetworkAddress networkAddress) {
		calculationServer.add(networkAddress);
	}

}
