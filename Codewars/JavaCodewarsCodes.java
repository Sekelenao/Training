import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaCodewarsCodes {

	/*
	 * QUESTION : returns true if there are as many "o" as "x" in a word. False
	 * otherwise.
	 */

	public static boolean getXO(String str) {
		Objects.requireNonNull(str);
		var sum = 0;
		for (var c : str.split("")) {
			sum += switch (c.toLowerCase(Locale.ROOT)) {
			case "o" -> 1;
			case "x" -> -1;
			default -> 0;
			};
		}
		return sum == 0;
	}

	public static boolean getXOWithStream(String str) {
		Objects.requireNonNull(str);
		return Stream.of(str.split("")).mapToInt(l -> {
			switch (l) {
			case "x":
				return 1;
			case "o":
				return -1;
			default:
				return 0;
			}
		}).sum() == 0;
	}

	/*
	 * QUESTION : Write a function that accepts an array of 10 integers (between 0
	 * and 9), that returns a string of those numbers in the form of a phone number.
	 */

	public static String createPhoneNumber(int[] numbers) {
		/* We box because formatted takes Objects not primitive type */
		Objects.requireNonNull(numbers);
		if (numbers.length != 10) {
			throw new IllegalArgumentException("Only accepts an array of 10 integers");
		}
		return "(%d%d%d) %d%d%d-%d%d%d%d".formatted(IntStream.of(numbers).boxed().toArray());
	}

	/*
	 * QUESTION : Delete the elements of the first array that are contained in the
	 * second.
	 */

	public static int[] arrayDiff(int[] a, int[] b) {
		return IntStream.of(a).filter(ea -> IntStream.of(b).noneMatch(eb -> eb == ea)).toArray();
	}

	/*
	 * QUESTION : Transform a variable into standard C (this_is_a_variable) to
	 * camelCase.
	 */

	static String toCamelCase(String str) {
		Objects.requireNonNull(str);
		String[] words = str.split("[-_]");
		return Arrays.stream(words, 1, words.length).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
				.reduce(words[0], String::concat);
	}

	/* 
	 * QUESTION : Remove vowels. 
	 * 
	 */

	public static String disemvowel(String str) {
		/* (?i) enables case-insensitivity */
		return str.replaceAll("(?i)[aeiou]", "");
	}

}
