/*
 * CSD 402: Java for Programmers
 * Module 1: Introduction and Elementary Programming
 *   Part 3: Programming Assignment
 * Isaac Ellingson
 * 1/14/2026
 */


package blue.endless.csd402.module1;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Module1_3 implements Runnable {
	// Might vary for different temperatures. Some people also list 4190 Seawater is much lower, about 3930.
	public static final double WATER_SPECIFIC_HEAT = 4184.0; // in J/KgK
	
	private static double getDouble(Scanner s, String prompt) {
		System.out.println(prompt);
		while(true) {
			try {
				
				System.out.print("> ");
				double d = s.nextDouble();
				s.nextLine();
				return d;
			} catch (InputMismatchException ex) {
				System.out.println("Input not recognized. Please enter a number.");
				s.nextLine();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Water Heating Energy Calculator");
		System.out.println();
		double waterAmount = getDouble(scanner, "Please enter the water weight in kg.");
		System.out.println();
		double startTemp = getDouble(scanner, "Please enter the starting temperature for the water, in degrees celsius.");
		System.out.println();
		double endTemp = getDouble(scanner, "Please enter the ending temperature for the water, in degrees celsius.");
		System.out.println();
		
		double qt = (endTemp - startTemp) * waterAmount * WATER_SPECIFIC_HEAT;
		NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);
		System.out.println("The energy difference is " + formatter.format(qt) + " joules.");
		
	}
}
