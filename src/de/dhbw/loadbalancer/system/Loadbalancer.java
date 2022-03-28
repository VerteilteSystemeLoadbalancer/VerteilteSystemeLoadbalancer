package de.dhbw.loadbalancer.system;

import java.util.HashMap;
import java.util.Map;
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

			String text = message.substring(Action.CALCULATE.toString().length()).trim();
			if (text.startsWith("\"") && text.endsWith("\"")) {
				String uuid = nextUUID(sender);
				send(Response.WAIT + " " + uuid, sender);
				send(Action.CALCULATE + " " + uuid + " " + text, balancer.next());
			} else {
				send(Response.ERROR.toString(), sender);
			}
		} else if (action == Action.RESULT) {
			String uuid = message.split(" ")[1];
			NetworkAddress address = getByUUID(uuid);
			send(message, address);
		} else {
			send(Response.ERROR + " unknown command", sender);
		}

	}

	private Map<String, NetworkAddress> map = new HashMap<>();

	private NetworkAddress getByUUID(String uuid) {
		return map.get(uuid);
	}

	private String nextUUID(NetworkAddress address) {
		String uuid = UUID.randomUUID().toString();
		map.put(uuid, address);
		return uuid;
	}

}
