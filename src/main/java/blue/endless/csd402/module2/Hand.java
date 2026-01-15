package blue.endless.csd402.module2;

import java.util.Random;

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
