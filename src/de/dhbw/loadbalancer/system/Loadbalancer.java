package de.dhbw.loadbalancer.system;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;
import de.dhbw.loadbalancer.strategy.BalanceInterface;
import de.dhbw.loadbalancer.util.TextUtil;
import lombok.Setter;

public class Loadbalancer extends NetworkConnection {

	@Setter
	private BalanceInterface<NetworkAddress> balancer;

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		Action action = Action.valueOf(message.split(" ")[0]);
		if (action == Action.CALCULATE) {
			String text = message.substring(Action.CALCULATE.toString().length()).trim();
			if (TextUtil.isStringInMarks(text)) {
				String uuid = nextUUID(sender);
				send(Action.CALCULATE + " " + uuid + " " + text, balancer.next());
			} else {
				send(Action.ERROR.toString(), sender);
			}
		} else if (action == Action.RESULT) {
			NetworkAddress address = getByUUID(message.split(" ")[1], true);
			send(message, address);
		} else if (action == Action.WAIT) {
			NetworkAddress address = getByUUID(message.split(" ")[1], false);
			send(message, address);
		}
	}

	private Map<String, NetworkAddress> map = new HashMap<>();

	private NetworkAddress getByUUID(String uuid, boolean delete) {
		if (delete) {
			return map.remove(uuid);
		} else {
			return map.get(uuid);
		}
	}

	private String nextUUID(NetworkAddress address) {
		String uuid = UUID.randomUUID().toString();
		map.put(uuid, address);
		return uuid;
	}

}
