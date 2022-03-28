package de.dhbw.loadbalancer.system.queue;

import de.dhbw.loadbalancer.calculation.Calculation;
import de.dhbw.loadbalancer.network.NetworkAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PendingCalculation {

	private Calculation calculation;

	@Getter
	private String uuid;

	@Getter
	private NetworkAddress sender;

	public String execute() {
		return calculation.calculate();
	}

}
