package blue.endless.csd402;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

/**
 * Static utility class to simplify reading various kinds of input from stdin.
 */
public class Input {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Reads a double value from stdin. No validation checks are made except that the input is a valid floating-point
	 * number which fits in a double. For example, negative values and NaN are accepted.
	 * @return The user-selected value.
	 */
	public static double getDouble() {
		return getDouble((it) -> true, "");
	}
	
	/**
	 * Reads a double from stdin. Ensures that the value is greater than or equal to zero.
	 * @return The user-selected value.
	 */
	public static double getNonnegativeDouble() {
		return getDouble((it) -> it >= 0.0, "Input must not be negative. Please enter a value of at least zero.");
	}
	
	/**
	 * Reads a double value from stdin. The value is validated using the provided Predicate, and if validation fails,
	 * the predicateFailureMessage provided is shown to the user to prompt them to retry.
	 * @param predicate A function used to validate input, returning true if valid and false otherwise.
	 * @param predicateFailureMessage A message to display if the input is invalid.
	 * @return The validated, user-selected value.
	 */
	public static double getDouble(DoublePredicate predicate, String predicateFailureMessage) {
		while(true) {
			try {
				System.out.print("> ");
				double d = scanner.nextDouble();
				scanner.nextLine();
				
				if (Double.isNaN(d)) {
					System.out.println("NaN is not a number. Please enter a number.");
					continue;
				}
				
				if (predicate.test(d)) {
					return d;
				} else {
					System.out.println(predicateFailureMessage);
					continue;
				}
				
			} catch (InputMismatchException ex) {
				System.out.println("Input not recognized. Please enter a number.");
				scanner.nextLine();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
	
	/**
	 * Reads an integer value from stdin. No validation checks are made except that the input is a valid integer. For
	 * example, negative values are accepted.
	 * @return The user-selected value.
	 */
	public static int getInt() {
		return getInt((it) -> true, "");
	}
	
	/**
	 * Reads an integer value from stdin. The value must be *greater than zero*.
	 * @return The user-selected value.
	 */
	public static int getPositiveInt() {
		return getInt((it) -> it > 0, "Input must be positive. Please enter a whole number greater than zero.");
	}
	
	/**
	 * Reads an integer value from stdin. The value is validated using the provided predicate, and if validation fails,
	 * the predicateFailureMessage provided is shown to the user to prompt them to retry.
	 * @param predicate A function used to validate input, returning true if valid and false otherwise.
	 * @param predicateFailureMessage A message to display if input is invalid.
	 * @return The validated, user-selected value.
	 */
	public static int getInt(IntPredicate predicate, String predicateFailureMessage) {
		while(true) {
			try {
				System.out.print("> ");
				int i = scanner.nextInt();
				scanner.nextLine();
				
				if (predicate.test(i)) {
					return i;
				} else {
					System.out.println(predicateFailureMessage);
					continue;
				}
				
			} catch (InputMismatchException ex) {
				System.out.println("Input not recognized. Please enter a whole number.");
				scanner.nextLine();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
	
	/**
	 * Reads an enum value from stdin. Will also accept numeric values, but those numeric values will start from one
	 * instead of zero.
	 * @param <T> The enum type in question.
	 * @param prompt The initial text prompting for user input.
	 * @param enumClass The enum type in question.
	 * @return The user-selected enum value.
	 */
	public static <T extends Enum<T>> T getEnum(Class<T> enumClass) {
		T[] values = enumClass.getEnumConstants();
		
		while(true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
			
			// Try to interpret it as a numeric index
			try {
				int index = Integer.parseInt(input) - 1;
				if (index < 0 || index >= values.length) {
					System.out.println("Input out of bounds. Please enter a number between 1 and " + values.length + ".");
					continue;
				} else {
					return values[index];
				}
			} catch (NumberFormatException ex) {}
			
			// It's not a number. Try to interpret it as an enum constant name
			for(T constant : enumClass.getEnumConstants()) {
				if (input.equals(constant.name().toLowerCase(Locale.ROOT))) {
					return constant;
				}
			}
			
			System.out.println("Input not recognized. Please enter a number between 1 and " + values.length + ".");
		}
	}
}
