/*
 * CSD 402: Java for Programmers
 * Module 2: Selections and Functions
 *   Part 2: Programming Assignment
 * Isaac Ellingson
 * 1/15/2026
 *   Adapted on 2/5/2026
 */


package module2;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Module2_2 {

	private static Scanner scanner = new Scanner(System.in);

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


	/**
	 * Represents a hand of Rock-Paper-Scissors - that is, a value of Rock, Paper, or Scissors. Each value of this enum
	 * is convertible back and forth between the assignment-mandated values.
	 */
	public enum Hand {
		ROCK,
		PAPER,
		SCISSORS;

		private static Random random = new Random();

		/**
		 * Returns a 1-based ordinal for this enum. ROCK is 1, PAPER is 2, and SCISSORS is 3.
		 * @return The integer "value" for this hand.
		 */
		public int value() {
			return this.ordinal() + 1;
		}

		/**
		 * Returns true if this hand beats the passed-in opposing hand. Returns false on a loss or a tie.
		 * @param other An opposing hand of Rock-Paper-Scissors
		 * @return True if this hand beats the supplied one, otherwise false.
		 */
		public boolean beats(Hand other) {
			switch(this) {
				case ROCK:
					return other == SCISSORS;
				case PAPER:
					return other == ROCK;
				case SCISSORS:
					return other == PAPER;
				default:
					throw new IllegalArgumentException("Unknown hand \"" + other.name() + "\".");
			}
		}

		/**
		 * Turns a number from 1-3, inclusive, into a Hand. 1 will yield ROCK, 2 yields PAPER, and 3 yields SCISSORS.
		 * @param value The integer "value" of the hand
		 * @return The Hand corresponding to that value
		 * @throws IllegalArgumentException if the number provided is outside the three known values.
		 */
		public static Hand of(int value) {
			int ordinal = value - 1;
			if (ordinal < 0 || ordinal >= values().length) throw new IllegalArgumentException();
			return values()[ordinal];
		}

		/**
		 * Creates a random computer hand.
		 * @return A random value from this Enum
		 */
		public static Hand random() {
			return values()[random.nextInt(values().length)];
		}
	}

	public static void main(String... args) {
		Hand computerHand = Hand.random();
		
		System.out.println("Rock - Paper - Scissors");
		System.out.println("Please enter a number from 1-3 to select your hand.");
		System.out.println("(You can also enter \"rock\", \"paper\", or \"scissors\")");
		Hand userHand = getEnum(Hand.class);
		
		System.out.println();
		System.out.println("Your hand was " + userHand.name().toLowerCase(Locale.ENGLISH)+".");
		System.out.println("Computer's hand was " + computerHand.name().toLowerCase(Locale.ENGLISH)+".");
		
		if (userHand == computerHand) { // enums are equals-comparable
			System.out.println("It was a tie!");
		} else if (userHand.beats(computerHand)) {
			System.out.println("You win!");
		} else {
			System.out.println("Computer wins!");
		}
	}
}
