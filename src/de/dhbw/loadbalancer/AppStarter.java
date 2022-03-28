package de.dhbw.loadbalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;
import de.dhbw.loadbalancer.strategy.BalanceStrategy;
import de.dhbw.loadbalancer.strategy.roundrobin.RoundRobin;
import de.dhbw.loadbalancer.system.CalculationServer;
import de.dhbw.loadbalancer.system.Loadbalancer;
import lombok.SneakyThrows;

public class AppStarter {

	private static final BalanceStrategy<NetworkAddress> BALANCER_LOADBALANCER = new RoundRobin<NetworkAddress>();

	private static final int AMOUNT_CALCULATIONSERVER = 1;
	private static final int AMOUNT_LOADBALANCER = 1;
	private static final int AMOUNT_CLIENT = 1;

	public static void main(String[] args) {
		AppStarter app = new AppStarter();
		app.start();
	}

	private void start() {
		List<Loadbalancer> loadbalancerList = launch(Loadbalancer.class, AMOUNT_LOADBALANCER);
		launchAllClients(loadbalancerList.stream().map(l -> l.toAddress()).collect(Collectors.toList()));

		List<CalculationServer> calculationserverList = launch(CalculationServer.class, AMOUNT_CALCULATIONSERVER);
		BALANCER_LOADBALANCER.updateList(calculationserverList.stream().map(cs -> cs.toAddress()).collect(Collectors.toList()));
		loadbalancerList.stream().forEach(l -> l.setBalancer(BALANCER_LOADBALANCER));
	}

	private void launchAllClients(List<NetworkAddress> loadbalancer) {
		for (int i = 0; i < AMOUNT_CLIENT; i++) {
			ClientLauncher clientLauncher = new ClientLauncher();
			clientLauncher.launch(loadbalancer);
		}
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
