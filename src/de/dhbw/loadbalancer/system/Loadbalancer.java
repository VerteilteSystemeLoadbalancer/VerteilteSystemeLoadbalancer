package de.dhbw.loadbalancer.system;

import java.util.UUID;

import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;
import de.dhbw.loadbalancer.strategy.BalanceInterface;
import lombok.Setter;

public class Loadbalancer extends NetworkConnection {

	@Setter
	private BalanceInterface<NetworkAddress> balancer;

	public Loadbalancer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("Loadbalancer hat empfangen: " + message);

		Action action = Action.valueOf(message.split(" ")[0]);
		if (action == Action.CALCULATE) {
			String uuid = nextCalculationUUID();

			String text = message.substring(Action.CALCULATE.toString().length()).trim();
			if (text.startsWith("\"") && text.endsWith("\"")) {
				send(Response.WAIT + " " + uuid, sender);
				send(Action.CALCULATE + " " + uuid + " " + text, balancer.next());
			} else {
				send(Response.ERROR.toString(), sender);
			}
		} else {
			send(Response.ERROR.toString(), sender);
		}

	}

	private String nextCalculationUUID() {
		return UUID.randomUUID().toString();
	}

}
