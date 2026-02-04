/*
 * CSD 402: Java for Programmers
 * Module 5: Multi-Dimensional Arrays
 *   Part 2: Programming Assignment
 *   Locate the largest element of several kinds of 2d grid.
 *   
 *   This version ditches the 2d arrays that shouldn't be used,
 *   creates a rectangular guarantee by using a custom Grid class,
 *   and takes a much DRYer approach, but until valhalla flattens
 *   boxed primitives, this won't perform any better.
 *   
 *   I also rolled an immutable Location class to handle return
 *   values, as int arrays are VERY non-idiomatic for this. Ideally,
 *   I would use a record, but this is java-10-safe code.
 *   
 * Isaac Ellingson
 * 2/4/2026
 */

package blue.endless.csd402.module5;

public class Module5_2b implements Runnable {
	
	public static <T extends Number> Location locateLargest(Grid<T> grid) {
		double max = Double.MIN_VALUE;
		Location maxLocation = new Location(-1, -1);
		
		for(int y = 0; y < grid.height(); y++) {
			for(int x = 0; x < grid.width(); x++) {
				T cur = grid.get(x, y);
				if (cur == null) continue;
				double curValue = cur.doubleValue();
				if (curValue > max) {
					max = curValue;
					maxLocation = new Location(x, y);
				}
			}
		}
		
		return maxLocation;
	}
	
	public static <T extends Number> Location locateSmallest(Grid<T> grid) {
		double min = Double.MAX_VALUE;
		Location minLocation = new Location(-1, -1);
		
		for(int y = 0; y < grid.height(); y++) {
			for(int x = 0; x < grid.width(); x++) {
				T cur = grid.get(x, y);
				if (cur == null) continue;
				double curValue = cur.doubleValue();
				if (curValue < min) {
					min = curValue;
					minLocation = new Location(x, y);
				}
			}
		}
		
		return minLocation;
	}
	
	@Override
	public void run() {
		Grid<Double> doubleTest = Grid.of(new double[][] {
			{ 12.6,   9.8,  4.2 },
			{  7.1, 125.21,83.37 }
		});
		
		System.out.println("Double grid:");
		System.out.println(doubleTest.toString());
		System.out.println("Location of largest value ((x, y), zero-indexed): "+locateLargest(doubleTest));
		System.out.println("Location of smallest value ((x, y), zero-indexed): "+locateSmallest(doubleTest));
		System.out.println();
		
		Grid<Integer> intTest = Grid.of(new int[][] {
			{ 10, 65, 26, 212, 32 },
			{ 398, 233, 312, 6, 3734 }, // Edited slightly so it's not ragged; ragged arrays would leave zeroes which will always be the minimum value
			{ (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024), (int) (Math.random() * 1024) }
		});
		
		System.out.println("Int grid:");
		System.out.println(intTest.toString());
		System.out.println("Location of largest value ((x, y), zero-indexed): "+locateLargest(intTest));
		System.out.println("Location of smallest value ((x, y), zero-indexed): "+locateSmallest(intTest));
	}
	
}
