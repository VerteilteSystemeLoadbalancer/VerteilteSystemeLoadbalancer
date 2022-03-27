package de.dhbw.loadbalancer.calculation;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Calculation {

	private final String calculationInput;
	private final int repeat;

	public String calculate() {
		String currentString = calculationInput;
		for (int i = 0; i < repeat; i++) {
			currentString = hash(currentString);
		}
		return currentString;
	}

	private String hash(String input) {
		return DigestUtils.sha256Hex(input);
	}

}
