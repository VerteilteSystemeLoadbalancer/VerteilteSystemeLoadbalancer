package de.dhbw.loadbalancer.system;

import de.dhbw.loadbalancer.gui.StringEvent;
import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;
import de.dhbw.loadbalancer.strategy.BalanceStrategy;
import de.dhbw.loadbalancer.util.TextUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Client extends NetworkConnection implements StringEvent {

	private final BalanceStrategy<NetworkAddress> loadbalancer;
	private final StringEvent outputEvent;

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		outputEvent.onEvent(message);
	}

	@Override
	public void onEvent(String data) {
		NetworkAddress address = loadbalancer.next();
		String prepared = prepare(data);
		send(Action.CALCULATE + " " + prepared, address);
	}

	private String prepare(String input) {
		return TextUtil.MARKS + input.replace(TextUtil.MARKS, "") + TextUtil.MARKS;
	}

}
