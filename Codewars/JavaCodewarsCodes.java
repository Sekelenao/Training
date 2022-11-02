import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
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

	/*
	 * QUESTION : The maximum sum sub-array problem consists in finding the maximum
	 * sum of a contiguous subsequence in an array or list of integers
	 */

	public static int sequence(int[] arr) {
		// Kadane's algorithm
		var sum = 0;
		var best = 0;
		for (var i : arr) {
			sum = (sum < 0) ? i : sum + i; /*
											 * If previous sum is negative, it's a penalty so we start our new sum from
											 * current element, else we take previous sum + current element
											 */
			if (sum > best) { /* If the current sum is greater than best of all previous value */
				best = sum;
			}
		}
		return best;
	}

	/*
	 * QUESTION : Complete it so that passing in RGB decimal values will result in a
	 * hexadecimal representation being returned. Valid decimal values for RGB are 0
	 * - 255. Any values that fall out of that range must be rounded to the closest
	 * valid value.
	 */

	public class RgbToHex {

		public static String rgb(int r, int g, int b) {
			return Stream.of(r, g, b).map(c -> { // First map is to round incorrect values.
				if (c < 0) // Round to zero
					return 0;
				if (c > 255) // Round to max
					return 255;
				return c; // Else keep value
			}).map(x -> "%02X".formatted(x)).collect(Collectors.joining(""));
		}

	}

}
