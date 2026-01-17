/*
 * CSD 402: Java for Programmers
 * Module 3: Loops & Methods
 *   Part 2: Programming Assignment
 * Isaac Ellingson
 * 1/17/2026
 */

package blue.endless.csd402.module3;

public class Module3_2 implements Runnable {
	public void run() {
		printPyramid(7);
	}
	
	/**
	 * Prints a pyramid of n, n*2, ..., n*2, n. Parameterized to as many lines as you need, but things get pretty wide
	 * after 12 lines or so, as we also need to adjust the width of each column to accommodate larger numbers.
	 * @param numLines The number of lines of this pyramid pattern to print.
	 */
	public void printPyramid(int numLines) {
		if (numLines == 0) return;
		if (numLines < 0) throw new IllegalArgumentException("Cannot print a negative number of lines.");
		
		// If we look logically at the diagram, we can discover a lot of metrics:
		
		// Number of empty cells on either side of the first line
		int gutter = numLines - 1;
		// Total number of cells wide at the bottom
		//int baseWidth = (2 * gutter) + 1;
		// Largest number we'll find in the pyramid, at the bottom middle
		int largestNumber = (int) Math.pow(2, numLines - 1);
		// How wide should we make each cell if we want to line everything up
		int cellWidth = Integer.toString(largestNumber).length() + 1;
		// String to print when there's no number present
		String emptyCell = ""; for(int i=0; i<cellWidth; i++) emptyCell += " ";
		// The total number of characters wide a line is
		//int lineLength = cellWidth * baseWidth;
		
		
		
		
		int curGutter = gutter;
		int numValuesUp = 1;
		for(int line = 0; line < numLines; line++) {
			StringBuilder curLine = new StringBuilder();
			
			// Indent
			for(int i = 0; i < curGutter; i++) {
				curLine.append(emptyCell);
			}
			
			int curValue = 1;
			
			// Add the first half - the ascending values, plus the middle
			for(int i = 0; i < numValuesUp; i++) {
				String toPrint = Integer.toString(curValue);
				while(toPrint.length() < cellWidth) toPrint = " " + toPrint;
				curLine.append(toPrint);
				curValue *= 2;
			}
			
			// Get rid of that tailing * 2 from the last iteration above
			curValue /= 2;
			
			// Add the back half, the descending values
			for(int i = 0; i < numValuesUp - 1; i++) {
				curValue /= 2;
				String toPrint = Integer.toString(curValue);
				while(toPrint.length() < cellWidth) toPrint = " " + toPrint;
				curLine.append(toPrint);
			}
			
			// Pad out the line with spaces to the same width for each line
			for(int i = 0; i < curGutter; i++) {
				curLine.append(emptyCell);
			}
			
			curGutter--;
			numValuesUp++;
			
			// Add the @ sign as required, to make it clear the lines have been padded out correctly
			curLine.append(' ');
			curLine.append('@');
			
			// The line is complete, emit it.
			System.out.println(curLine);
		}
	}
}
