package de.dhbw.loadbalancer.network.connection;

import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@AllArgsConstructor
@Getter
public class NetworkAddress {

	private InetAddress address;
	private int port;

	@SneakyThrows
	public static NetworkAddress local(int port) {
		return new NetworkAddress(InetAddress.getLocalHost(), port);
	}

	public void addCalculationServer(NetworkAddress calculationServer) {
		// TODO
	}

}
