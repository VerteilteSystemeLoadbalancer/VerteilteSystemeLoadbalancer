package de.dhbw.loadbalancer.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.CountDownLatch;

import de.dhbw.loadbalancer.util.TextUtil;
import lombok.Getter;
import lombok.SneakyThrows;

public abstract class NetworkConnection {

	@Getter
	private String id;

	public NetworkConnection() {
		id = TextUtil.randomString();
	}

	private DatagramSocket socket = null;
	private Thread listenThread = null;

	protected abstract void onMessageReceive(String message, NetworkAddress sender);

	private Thread createThread() {
		listenThread = new Thread(() -> listen());
		return listenThread;
	}

	private CountDownLatch blocker = new CountDownLatch(1);

	@SneakyThrows
	public void runInBackground() {
		createThread().start();
		blocker.await();
	}

	@SneakyThrows
	private void listen() {
		socket = new DatagramSocket();
		blocker.countDown();
		while (true) {
			byte[] buffer = new byte[500];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			String data = new String(packet.getData(), 0, packet.getLength());
			NetworkAddress sender = new NetworkAddress(packet.getAddress(), packet.getPort());
			System.out.println(getClass().getSimpleName() + " " + getId() + " hat empfangen: " + data);
			onMessageReceive(data, sender);
		}
	}

	public boolean send(String message, NetworkAddress receiver) {
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, receiver.getAddress(), receiver.getPort());
		try {
			socket.send(packet);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public NetworkAddress toAddress() {
		return NetworkAddress.local(socket.getLocalPort());
	}

}
