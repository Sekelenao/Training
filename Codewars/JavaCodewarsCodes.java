import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	 * sum of a contiguous subsequence in an array or list of integers.
	 */

	public static int maximumSumSubArray(int[] arr) {
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

	public static String rgbToHex(int r, int g, int b) {
		return Stream.of(r, g, b).map(c -> { // First map is to round incorrect values.
			if (c < 0) // Round to zero
				return 0;
			if (c > 255) // Round to max
				return 255;
			return c; // Else keep value
		}).map(x -> "%02X".formatted(x)).collect(Collectors.joining(""));
	}

	/*
	 * QUESTION : Write a function that takes an array of numbers (integers for the
	 * tests) and a target number. It should find two different items in the array
	 * that, when added together, give the target value. The indices of these items
	 * should then be returned in a tuple / list.
	 */

	public static int[] twoSum(int[] numbers, int target) {
		var previousValues = new HashMap<Integer, Integer>();
		for (int index = 0; index < numbers.length; index++) {
			var currentValue = numbers[index];
			var neededValue = target - currentValue;
			var matchingValue = previousValues.get(neededValue);
			if (matchingValue != null) {
				return new int[] { matchingValue, index };
			}
			previousValues.put(currentValue, index);
		}
		return null;
	}

	/*
	 * QUESTION : A format for expressing an ordered list of integers is to use a
	 * comma separated list of either individual integers or a range of integers
	 * denoted by the starting integer separated from the end integer in the range
	 * by a dash, '-'. The range includes all integers in the interval including
	 * both end points. It is not considered a range unless it spans at least 3
	 * numbers.
	 * 
	 * For example {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14,
	 * 15, 17, 18, 19, 20} returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
	 */

	public static ArrayList<String> subResult(List<Integer> buffer) {
		ArrayList<String> subresult = new ArrayList<>();
		if (buffer.size() < 3) { // not a sequence
			buffer.stream().map(String::valueOf).forEach(e -> subresult.add(e));
		} else { // is a sequence
			subresult.add("%d-%d".formatted(buffer.get(0), buffer.get(buffer.size() - 1)));
		}
		return subresult;
	}

	public static String rangeExtraction(int[] arr) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<Integer> buffer = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (buffer.isEmpty() || ((arr[i] - 1) == buffer.get(buffer.size() - 1))) {
				buffer.add(arr[i]); // we have a sequence so we just add to the buffer
			} else {
				result.addAll(subResult(buffer)); // add our sub-result to the result
				buffer = new ArrayList<>(); // new buffer (faster than remove with the garbage collector)
				buffer.add(arr[i]); // add the current element to the buffer after cleaning it
			}
		}
		result.addAll(subResult(buffer)); // add last element to the result
		return result.stream().collect(Collectors.joining(","));
	}

	/*
	 * QUESTION : We need to sum big numbers and we require your help.
	 * 
	 * Write a function that returns the sum of two numbers. The input numbers are
	 * strings and the function must return a string.
	 */

	public static String bigAdd(String a, String b) {
		int tmp = 0;
		var carrying = 0;
		var result = new StringBuilder();
		var firstNumber = new StringBuilder(a).reverse();
		var secondNumber = new StringBuilder(b).reverse();
		for (int i = 0; i < firstNumber.length(); i++) {
			if (!secondNumber.isEmpty()) {
				tmp = Character.getNumericValue(firstNumber.charAt(i))
						+ Character.getNumericValue(secondNumber.charAt(0)) + carrying;
				carrying = (tmp > 9) ? 1 : 0;
				result.append(tmp % 10);
				secondNumber.deleteCharAt(0);
			} else {
				tmp = Character.getNumericValue(firstNumber.charAt(i)) + carrying;
				carrying = (tmp > 9) ? 1 : 0;
				result.append(tmp % 10);
			}
		}
		for (int i = 0; i < secondNumber.length(); i++) {
			tmp = Character.getNumericValue(secondNumber.charAt(i)) + carrying;
			carrying = (tmp > 9) ? 1 : 0;
			result.append(tmp % 10);
		}
		return (carrying != 0) ? result.append(1).reverse().toString().replaceFirst("^0+(?!$)", "")
				: result.reverse().toString().replaceFirst("^0+(?!$)", "");
	}

}
