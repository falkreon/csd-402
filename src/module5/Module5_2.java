/*
 * CSD 402: Java for Programmers
 * Module 5: Multi-Dimensional Arrays
 *   Part 2: Programming Assignment
 *   Locate the largest element of several kinds of array-of-array.
 *   
 * Isaac Ellingson
 * 2/4/2026
 *    Adapted on 2/5/2026
 */

package module5;

import java.text.NumberFormat;
import java.util.Arrays;

public class Module5_2 {
	/*
	 * For this assignment I had to make a call, because it's unspecified whether the array indices are row/col or x/y.
	 * For code simplicity I decided on [row][col], which is equivalent to [y][x].
	 * 
	 * When reporting values, on the other hand, I'm using zero-indexed [x, y] for clarity, to make it easy to visually
	 * check results.
	 */
	
	/**
	 * Locates the largest value in the array. If there are multiple cells containing the largest value, the first row
	 * containing a maximum value is selected, and then the location of the first maximum value in that row is selected.
	 * Coordinates are returned as [x, y].
	 * @param arr The array of arrays to search
	 * @return The location of the largest value
	 */
	public static int[] locateLargest(double[][] arr) {
		if (arr.length == 0) throw new IllegalArgumentException("Cannot return coordinates into an empty array.");
		// Due to the ragged arrays problem, we cannot realistically validate the width here.
		
		double max = Double.MIN_VALUE;
		int maxX = -1;
		int maxY = -1;
		
		for(int y = 0; y < arr.length; y++) {
			double[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				double cur = row[x];
				if (cur > max) {
					maxX = x;
					maxY = y;
					max = cur;
				}
			}
		}
		
		return new int[] { maxX, maxY };
	}
	
	// Once again, we can't really be very DRY here
	/**
	 * Locates the largest value in the array. If there are multiple cells containing the largest value, the first row
	 * containing a maximum value is selected, and then the location of the first maximum value in that row is selected.
	 * Coordinates are returned as [x, y].
	 * @param arr The array of arrays to search
	 * @return The location of the largest value
	 */
	public static int[] locateLargest(int[][] arr) {
		if (arr.length == 0) throw new IllegalArgumentException("Cannot return coordinates into an empty array.");
		// Due to the ragged arrays problem, we cannot realistically validate the width here.
		
		int max = Integer.MIN_VALUE;
		int maxX = -1;
		int maxY = -1;
		
		for(int y = 0; y < arr.length; y++) {
			int[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				int cur = row[x];
				if (cur > max) {
					maxX = x;
					maxY = y;
					max = cur;
				}
			}
		}
		
		return new int[] { maxX, maxY };
	}
	
	/**
	 * Locates the smallest value in the array. If there are multiple cells containing the smallest value, the first row
	 * containing a minimum value is selected, and then the location of the first minimum value in that row is selected.
	 * Coordinates are returned as [x, y].
	 * @param arr The array of arrays to search
	 * @return The location of the smallest value
	 */
	public static int[] locateSmallest(double[][] arr) {
		if (arr.length == 0) throw new IllegalArgumentException("Cannot return coordinates into an empty array.");
		// Due to the ragged arrays problem, we cannot realistically validate the width here.
		
		double min = Double.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		
		for(int y = 0; y < arr.length; y++) {
			double[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				double cur = row[x];
				if (cur < min) {
					minX = x;
					minY = y;
					min = cur;
				}
			}
		}
		
		return new int[] { minX, minY };
	}
	
	/**
	 * Locates the smallest value in the array. If there are multiple cells containing the smallest value, the first row
	 * containing a minimum value is selected, and then the location of the first minimum value in that row is selected.
	 * Coordinates are returned as [x, y].
	 * @param arr The array of arrays to search
	 * @return The location of the smallest value
	 */
	public static int[] locateSmallest(int[][] arr) {
		if (arr.length == 0) throw new IllegalArgumentException("Cannot return coordinates into an empty array.");
		// Due to the ragged arrays problem, we cannot realistically validate the width here.
		
		int min = Integer.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		
		for(int y = 0; y < arr.length; y++) {
			int[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				int cur = row[x];
				if (cur < min) {
					minX = x;
					minY = y;
					min = cur;
				}
			}
		}
		
		return new int[] { minX, minY };
	}
	
	/**
	 * Prints out an array for diagnostics. Treats the first (outer) array as an array of lines or rows, and the second
	 * (inner) array as a row containing values across the columns.
	 * @param arr The array of arrays to print out.
	 */
	public static void print(double[][] arr) {
		NumberFormat fmt = NumberFormat.getNumberInstance();
		fmt.setMinimumFractionDigits(2);
		fmt.setMaximumFractionDigits(2);
		
		for(int y = 0; y < arr.length; y++) {
			double[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				String s = fmt.format(row[x]);
				while(s.length() < 7) s = " " + s;
				
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints out an array for diagnostics. Treats the first (outer) array as an array of lines or rows, and the second
	 * (inner) array as a row containing values across the columns.
	 * @param arr The array of arrays to print out.
	 */
	public static void print(int[][] arr) {
		
		for(int y = 0; y < arr.length; y++) {
			int[] row = arr[y];
			for(int x = 0; x < row.length; x++) {
				String s = Integer.toString(row[x]);
				while(s.length() < 5) s = " " + s;
				
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
	
	public static void main(String... args) {
		double[][] doubleTest = new double[][] {
			{ 12.6,   9.8,  4.2 },
			{  7.1, 125.21,83.37 }
		};
		
		System.out.println("Array of double arrays:");
		print(doubleTest);
		System.out.println("Location of largest value ([x, y], zero-indexed): "+Arrays.toString(locateLargest(doubleTest)));
		System.out.println("Location of smallest value ([x, y], zero-indexed): "+Arrays.toString(locateSmallest(doubleTest)));
		System.out.println();
		
		int[][] intTest = new int[][] {
			{ 10, 65, 26, 212, 32 },
			{ 398, 233, 312, 6, 3734, 21 }, // This array is ragged to demonstrate proper handling of this case
			// Also slightly randomized to help fuzz out any errors
			{ (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024) }
		};
		
		System.out.println("Array of int arrays:");
		print(intTest);
		System.out.println("Location of largest value ([x, y], zero-indexed): "+Arrays.toString(locateLargest(intTest)));
		System.out.println("Location of smallest value ([x, y], zero-indexed): "+Arrays.toString(locateSmallest(intTest)));
	}
}
