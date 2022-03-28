package de.dhbw.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.dhbw.loadbalancer.gui.ClientGUI;
import de.dhbw.loadbalancer.gui.StringEvent;
import de.dhbw.loadbalancer.network.CalculationServer;
import de.dhbw.loadbalancer.network.Client;
import de.dhbw.loadbalancer.network.Loadbalancer;
import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;
import lombok.SneakyThrows;

public class AppStarter {

	private static final int AMOUNT_CALCULATIONSERVER = 15;
	private static final int AMOUNT_LOADBALANCER = 5;

	public static void main(String[] args) {
		AppStarter app = new AppStarter();
		app.start();
	}

	private void start() {
		List<Loadbalancer> loadbalancerList = launch(Loadbalancer.class, AMOUNT_LOADBALANCER);
		launchClientWithGui(loadbalancerList.stream().map(l -> convertConnectionToAddress(l)).collect(Collectors.toList()));

		List<CalculationServer> calculationserver = launch(CalculationServer.class, AMOUNT_CALCULATIONSERVER);
		loadbalancerList.stream().forEach(l -> calculationserver.stream().forEach(c -> l.addCalculationServer(convertConnectionToAddress(c))));
	}

	private NetworkAddress convertConnectionToAddress(NetworkConnection connection) {
		return NetworkAddress.local(connection.getPort());
	}

	private ClientGUI gui;

	private void launchClientWithGui(List<NetworkAddress> loadbalancer) {
		StringEvent outputEvent = data -> gui.onEvent(data);
		Client client = new Client(loadbalancer, outputEvent);
		client.runInBackground();
		gui = new ClientGUI(data -> client.onEvent(data));
		gui.open();
	}

	@SneakyThrows
	private <T extends NetworkConnection> List<T> launch(Class<T> c, int amount) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			T networkConnection = c.newInstance();
			networkConnection.runInBackground();
			list.add(networkConnection);
		}
		return list;
	}

}
