/**
 * CSD 402: Java for Programmers
 * Module 9: Exception Handling & Text I/O
 *   Part 2: Programming Assignment
 *   
 * Demonstrate usage of the enhanced for loop, auto-boxing and unboxing, and Exception handling.
 * 
 * It's super weird to ask for boxing in a program about ArrayLists, Strings, and Exceptions.
 * There's no boxing for reference types. I *guess* you want us to use integer indices into
 * the list, and then I can box *those*? Let's do that.
 * 
 * Isaac Ellingson
 * 2/23/2026
 */

package module9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program1 {
	
	private static Scanner in = new Scanner(System.in);
	
	/* The ArrayList requirement is super weird but here you go.
	 * Source: https://dailypassport.com/most-visited-us-states/
	 */
	private static ArrayList<String> mostVisited = new ArrayList<>(List.of(
			"New York", "California", "Ohio", "Pennsylvania", "Georgia",
			"Tennessee", "Florida", "Michigan", "Texas", "New Jersey"
			));
	
	/**
	 * Gets the state by its position in the list (1-10).
	 * @param index The position of the state in the list. For example, 2 yields "California".
	 * @return The state in that position of the list (1-indexed)
	 * @throws IndexOutOfBoundsException If the number is not between 1 and 10, inclusive.
	 */
	public static String getStateFromList(Integer index) throws IndexOutOfBoundsException {
		// The exception is already thrown by ArrayList.get(), so I'm not doing precondition checks here.
		
		return mostVisited.get(index - 1); // Auto-unboxing happens here with index when the subtraction is done
	}
	
	public static void main(String... args) {
		System.out.println("The top ten most-visited states:");
		System.out.println();
		
		/* I would have liked to use a traditional for loop here and
		 * print out index numbers from 1-10, but you (kind of) asked
		 * for enhanced for here, so the user will get no help.
		 */
		for(String state : mostVisited) {
			System.out.println("  " + state);
		}
		
		System.out.println("-------------------------");
		System.out.println("Which state would you like to see again? (1 - 10)");
		try {
			System.out.print("> ");
			int index = in.nextInt();
			in.nextLine(); // Consume the newline character to be nice and clean.
			String selectedState = getStateFromList(index); // At this moment index is auto-boxed
			
			System.out.println();
			System.out.println("The state you selected was \"" + selectedState + "\".");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("Out of Bounds");
		}
	}
}
