/*
 * CSD 402: Java for Programmers
 * Module 10: Abstract Clases and Interfaces
 *   Part 2: Programming Assignment
 *
 * Creates two instances each of our concrete Division subclasses and show that their constructors work.
 * Isaac Ellingson
 * 2/28/2026
 */

package module10;

import java.util.ArrayList;

public class UseDivision {
	public static void main(String... args) {
		ArrayList<Division> divisions = new ArrayList<>();
		divisions.add(new DomesticDivision("Boulder Agency Branch", 12397, "Colorado"));
		divisions.add(new DomesticDivision("Boston Agency Branch", 12965, "Massachusetts"));
		divisions.add(new InternationalDivision("Paris Destination Branch", 9998, "France", "French"));
		divisions.add(new InternationalDivision("S\u00E3o Paulo Destination Branch", 385722, "Brazil", "Portugese"));
		
		
		// Just so you can see the getters working too. The toString in each subclass delegates to the getters.
		System.out.println("Divisions in Company:");
		for(Division division : divisions) {
			System.out.println("  " + division);
		}
	}
}
