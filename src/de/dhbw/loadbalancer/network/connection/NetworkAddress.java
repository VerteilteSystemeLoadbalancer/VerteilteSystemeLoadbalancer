package de.dhbw.loadbalancer.network.connection;

import java.net.InetAddress;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Getter
public class NetworkAddress {

	private final InetAddress address;
	private final int port;

	@SneakyThrows
	public static NetworkAddress local(int port) {
		return new NetworkAddress(InetAddress.getLocalHost(), port);
	}

}
