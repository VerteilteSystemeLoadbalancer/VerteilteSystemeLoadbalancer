package de.dhbw.loadbalancer.network;

import java.util.List;

import de.dhbw.loadbalancer.gui.StringEvent;
import de.dhbw.loadbalancer.network.connection.NetworkAddress;
import de.dhbw.loadbalancer.network.connection.NetworkConnection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Client extends NetworkConnection implements StringEvent {

	private final List<NetworkAddress> loadbalancer;
	private final StringEvent outputEvent;

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("Client hat empfangen: " + message);
	}

	@Override
	public void onEvent(String data) {
		System.out.println("Client Eingabe: " + data);
		outputEvent.onEvent("Eingabe erkannt");
	}

}
