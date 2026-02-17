/**
 * CSD 402: Java for Programmers
 * Module 8: Inheritance and Polymorphism
 *   Part 2: Programming Assignment
 *   Take a collection of numbers from input and display the maximum value.
 *   
 *   I'm struggling to understand what this has to do with inheritance and polymorphism,
 *   but sure, let's do it.
 *   
 * Isaac Ellingson
 * 2/16/2026
 */

package module8;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IsaacArrayListTest {
	
	// I like to use a single Scanner instance here because it's harder to accidentally "close" or confuse System.in this way
	// Style note: Since the Scanner is mutable, 'in' is not a constant, and is not written in captials.
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * Prompts the user for integer input, validates the input, and retries till the user enters valid input.
	 * @param prompt The message that will prompt the user for input. (e.g. "Please enter a number."
	 * @return The integer value the user entered.
	 */
	public static int getIntInput(String prompt) {
		System.out.println(prompt);
		while(true) {
			try {
				System.out.print("> ");
				int result = in.nextInt();
				in.nextLine();
				return result;
			} catch (InputMismatchException ex) {
				System.out.println("Input not recognized. Please enter a number.");
				in.nextLine();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
	
	/**
	 * Take in a list of integers and return the max value. If an empty list is provided, returns zero.
	 * @param list the integer list to find the maximum value in
	 * @return the largest element of the list, or zero if there are no elements
	 */
	public static Integer max(ArrayList<Integer> list) {
		if (list.isEmpty()) return 0;
		
		// We use the smallest possible (extremely negative) value as a sentinel here instead of zero,
		// so that if the list contains only negative numbers, we won't mistakenly return 0.
		int result = Integer.MIN_VALUE;
		for(int i : list) { // Auto-unboxing happens here each loop
			if (i > result) result = i;
		}
		
		return result; // Auto-boxing happens here
	}
	
	public static void main(String... args) {
		ArrayList<Integer> userInput = new ArrayList<>();
		
		int valueEntered = -1;
		while (valueEntered != 0) {
			valueEntered = getIntInput("Please enter a number (or 0 to finish entering numbers.");
			userInput.add(valueEntered);
		}
		
		System.out.println();
		System.out.println("The list you entered is " + userInput);
		System.out.println("The max value is " + max(userInput));
	}
}
