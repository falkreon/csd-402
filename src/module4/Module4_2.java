/*
 * CSD 402: Java for Programmers
 * Module 4: Single-Dimensional Arrays
 *   Part 2: Programming Assignment
 *   Take the average of several differently-typed arrays
 *   
 * Isaac Ellingson
 * 1/17/2026
 *    Adapted 2/5/2026
 */

package blue.endless.csd402.module4;

import java.util.Arrays;

public class Module4_2 {
	
	public static void main(String... args) {
		short[] shortArray = new short[] { Short.MAX_VALUE, 0, -1, 12, 17 }; // Sum won't overflow
		System.out.println("Short Array: " + Arrays.toString(shortArray));
		System.out.println("Average: " + average(shortArray));
		System.out.println();
		
		int[] intArray = new int[] { 12, 15, 27, 96, 128, -6 };
		System.out.println("Int Array: " + Arrays.toString(intArray));
		System.out.println("Average: " + average(intArray));
		System.out.println();
		
		long[] longArray = new long[] { Long.MAX_VALUE, 1260 }; // Sum WILL overflow
		System.out.println("Long Array: " + Arrays.toString(longArray));
		System.out.println("Exact Average (May Overflow): " + average(longArray));
		System.out.println("Approximate Average (Won't Overflow): " + approximateAverage(longArray));
		System.out.println();
		
		double[] doubleArray = new double[] { 8.0, 8.0, 8.0, 8.0, 12.0, 8.0, 0.0 };
		System.out.println("Double Array: " + Arrays.toString(doubleArray));
		System.out.println("Average: " + average(doubleArray));
		System.out.println();
	}
	
	
	/*
	 * Functions below exhibit a classic Java problem: we can't parameterize over primitive types. Ideally we would
	 * write a single method, like
	 * 
	 * public static <any T extends Number> T average(T[] array) {
	 *   T sum = 0;
	 *   for(T t : array) {
	 *     sum += t;
	 *   }
	 *   
	 *   return sum / array.length;
	 * }
	 * 
	 * In other languages with more robust type systems (haskell, scala, possibly kotlin, all the lisps) this would be
	 * possible. The closest we can get is:
	 * 
	 * public static <T extends Number> double average(T[] array) {
	 *   double sum = 0.0;
	 *   for(T t : array) {
	 *     sum += t.doubleValue();
	 *   }
	 *
	 *   return sum / array.length;
	 * }
	 * 
	 * But even this requires the array to contain *boxed* types instead of primitives, and will only give an
	 * approximate answer, because we must decide on a type / precision to compute in.
	 * 
	 * Therefore what follows are the requested function overloads. But it would be nice to be able to do this better.
	 */
	
	
	
	
	/**
	 * Takes in an array of 16-bit shorts and computes the average value of all cells in the array.
	 * @param array The array whose values should be averaged.
	 * @return The mean average of the values in the array.
	 */
	public static short average(short[] array) {
		long sum = 0L;
		for(short s : array) {
			sum += s;
		}
		
		return (short) (sum / array.length);
	}
	
	/**
	 * Takes in an array of 32-bit ints and computes the average value of all cells in the array.
	 * @param array The array whose values should be averaged.
	 * @return The mean average of the values in the array.
	 */
	public static int average(int[] array) {
		long sum = 0L;
		for(int i : array) {
			sum += i;
		}
		
		return (int) (sum / array.length);
	}
	
	/**
	 * Takes in an array of 64-bit longs and computes the average value of all cells in the array.
	 * 
	 * <p>Note: This method is prone to overflow if the array elements are large. 
	 * @param array The array whose values should be averaged.
	 * @return The mean average of the values in the array.
	 */
	public static long average(long[] array) {
		long sum = 0L;
		for(long l : array) {
			sum += l;
		}
		
		return (long) (sum / array.length);
	}
	
	/**
	 * Takes in an array of 64-bit longs and computes the average value of all cells in the array.
	 * 
	 * <p>Note: This may produce an approximate answer, but should not overflow.
	 * @param array The array whose values should be averaged.
	 * @return The mean average of the values in the array.
	 */
	public static long approximateAverage(long[] array) {
		double sum = 0.0;
		for(long l : array) {
			sum += l;
		}
		
		return (long) (sum / array.length);
	}
	
	/**
	 * Takes in an array of 64-bit double-precision floating point values and computes the average value of all cells in
	 * the array.
	 * @param array The array whose values should be averaged.
	 * @return The mean average of the values in the array.
	 */
	public static double average(double[] array) {
		double sum = 0.0;
		for(double d : array) {
			sum += d;
		}
		
		return (double) (sum / array.length);
	}
}
