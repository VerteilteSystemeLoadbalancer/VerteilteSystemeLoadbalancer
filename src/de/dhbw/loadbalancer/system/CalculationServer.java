package de.dhbw.loadbalancer.system;

import de.dhbw.loadbalancer.calculation.Calculation;
import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;
import de.dhbw.loadbalancer.system.queue.PendingCalculation;
import de.dhbw.loadbalancer.system.queue.Queue;
import de.dhbw.loadbalancer.util.TextUtil;

public class CalculationServer extends NetworkConnection {

	private Queue<PendingCalculation> queue = new Queue<>();

	private Thread thread;

	public CalculationServer() {
		super();
		launchQueue();
	}

	private void launchQueue() {
		thread = new Thread(() -> {
			while (!Thread.interrupted()) {
				PendingCalculation next = queue.next();
				String result = next.execute();

				send(Action.RESULT + " " + next.getUuid() + " " + TextUtil.putStringInMarks(result), next.getSender());
			}
		});
		thread.start();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("CalculationServer " + getId() + " hat empfangen: " + message);

		String split[] = message.split(" ");
		Action action = Action.valueOf(split[0]);

		if (action == Action.CALCULATE) {
			String uuid = split[1];
			String text = message.substring(message.indexOf(uuid) + uuid.length()).trim();

			if (TextUtil.isStringInMarks(text)) {
				text = text.substring(1, text.length() - 1);
				queueCalculation(text, uuid, sender);
				int currentPosition = queue.size();
				send(Action.WAIT + " " + uuid + " " + currentPosition, sender);
			} else {
				send(Action.ERROR.toString(), sender);
			}

		} else {
			send(Action.ERROR + " unknown command", sender);
		}

	}

	private static final int REPEAT = 10000000;

	private void queueCalculation(String text, String uuid, NetworkAddress sender) {
		Calculation calculation = new Calculation(text, REPEAT);
		PendingCalculation pendingCalculation = new PendingCalculation(calculation, uuid, sender);
		queue.add(pendingCalculation);
	}

}
