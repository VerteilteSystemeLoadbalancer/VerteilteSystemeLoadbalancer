package de.dhbw.loadbalancer.system;

import de.dhbw.loadbalancer.calculation.Calculation;
import de.dhbw.loadbalancer.network.NetworkAddress;
import de.dhbw.loadbalancer.network.NetworkConnection;

public class CalculationServer extends NetworkConnection {

	public CalculationServer() {
		super();
	}

	@Override
	protected void onMessageReceive(String message, NetworkAddress sender) {
		System.out.println("CalculationServer hat empfangen: " + message);

		String split[] = message.split(" ");
		Action action = Action.valueOf(split[0]);

		if (action == Action.CALCULATE) {
			String uuid = split[1];
			String text = message.substring(message.indexOf(uuid) + uuid.length()).trim();

			if (text.startsWith("\"") && text.endsWith("\"")) {
				text = text.substring(1, text.length() - 1);

				String result = calc(text);
				send(Action.RESULT + " " + uuid + " " + "\"" + result + "\"", sender);

			} else {
				send(Response.ERROR.toString(), sender);
			}

		} else {
			send(Response.ERROR + " unknown command", sender);
		}

	}

	private static final int REPEAT = 50000;

	private String calc(String text) {
		Calculation calculation = new Calculation(text, REPEAT);
		return calculation.calculate();
	}

}
