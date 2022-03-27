package de.dhbw.loadbalancer.network.connection;

import java.net.InetAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NetworkAddress {

	private InetAddress address;
	private int port;

}
