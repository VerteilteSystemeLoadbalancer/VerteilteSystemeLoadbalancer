package de.dhbw.loadbalancer.network.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public abstract class NetworkConnection {

	private final int port;

	private DatagramSocket socket = null;
	private Thread listenThread = null;

	protected abstract void onMessageReceive(String message, NetworkAddress sender);

	private Thread createThread() {
		listenThread = new Thread(() -> listen());
		return listenThread;
	}

	public void runInBackground() {
		createThread().start();
	}

	@SneakyThrows
	private void listen() {
		socket = new DatagramSocket(port);
		while (true) {
			byte[] buffer = new byte[500]; // TODO max length
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			String data = new String(packet.getData(), 0, packet.getLength());
			System.out.println("IN on port " + port + ": " + data);
			NetworkAddress sender = new NetworkAddress(packet.getAddress(), packet.getPort());
			onMessageReceive(data, sender);
		}
	}

	public boolean send(String message, NetworkAddress receiver) {
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, receiver.getAddress(), receiver.getPort()); // Erzeugen eines Datagram Packets
		try {
			socket.send(packet);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
