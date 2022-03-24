package de.dhbw.loadbalancer.network;

import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;

public class Client extends NetworkConnection {

	public Client(int port) {
		super(port);
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {

	}

}
