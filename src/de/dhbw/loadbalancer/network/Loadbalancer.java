package de.dhbw.loadbalancer.network;

import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;
import de.dhbw.loadbalancer.strategy.BalanceInterface;

public class Loadbalancer extends NetworkConnection {

	public Loadbalancer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("Loadbalancer hat empfangen: " + message);
	}

	private BalanceInterface<NetworkAddress> balancer;

	public void setBalancer(BalanceInterface<NetworkAddress> balancer) {
		this.balancer = balancer;
	}

}
