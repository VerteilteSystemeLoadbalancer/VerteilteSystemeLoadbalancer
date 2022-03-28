package de.dhbw.loadbalancer;

import java.util.List;

import de.dhbw.loadbalancer.gui.ClientGUI;
import de.dhbw.loadbalancer.gui.StringEvent;
import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.strategy.BalanceStrategy;
import de.dhbw.loadbalancer.strategy.random.DefaultRandomSupplier;
import de.dhbw.loadbalancer.strategy.random.RandomBalancer;
import de.dhbw.loadbalancer.system.Client;

public class ClientLauncher {

	private static final BalanceStrategy<NetworkAddress> BALANCER_CLIENT = new RandomBalancer<NetworkAddress>(new DefaultRandomSupplier());

	private ClientGUI gui;

	public void launch(List<NetworkAddress> loadbalancer) {
		StringEvent outputEvent = data -> gui.onEvent(data);
		BALANCER_CLIENT.updateList(loadbalancer);
		Client client = new Client(BALANCER_CLIENT, outputEvent);
		client.runInBackground();
		gui = new ClientGUI(data -> client.onEvent(data));
		gui.open();
	}

}
