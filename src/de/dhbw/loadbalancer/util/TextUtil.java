package de.dhbw.loadbalancer.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TextUtil {

	public static final String MARKS = "\"";

	public boolean isStringInMarks(String check) {
		return check.startsWith(MARKS) && check.endsWith(MARKS);
	}

	public String putStringInMarks(String input) {
		return MARKS + input.replace(MARKS, "") + MARKS;
	}

	public static String randomString() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * alphabet.length());
			sb.append(alphabet.charAt(index));
		}
		return sb.toString();
	}

}
