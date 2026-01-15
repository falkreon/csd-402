/*
 * CSD 402: Java for Programmers
 * Module 2: Selections and Functions
 *   Part 2: Programming Assignment
 * Isaac Ellingson
 * 1/15/2026
 */


package blue.endless.csd402.module2;

import java.util.Locale;

import blue.endless.csd402.Input;

public class Module2_2 implements Runnable {
	public void run() {
		Hand computerHand = Hand.random();
		
		System.out.println("Rock - Paper - Scissors");
		System.out.println("Please enter a number from 1-3 to select your hand.");
		System.out.println("(You can also enter \"rock\", \"paper\", or \"scissors\")");
		Hand userHand = Input.getEnum(Hand.class);
		
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
