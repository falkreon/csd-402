/**
 * CSD 402: Java for Programmers
 * Module 9: Exception Handling & Text I/O
 *   Part 2: Programming Assignment
 *   
 * Write or append ten integers to a desginated file. Then read them back and display them.
 * 
 * Isaac Ellingson
 * 2/24/2026
 */

package module9;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Program2 {
	
	// Path.of(String, String...) won't be available till Java 11, but Paths are much easier to control
	private static final Path DATA_PATH = new File(".", "data.file").toPath();
	
	/**
	 * Opens the specified file and writes ten space-delimited random integers into it.
	 * @param path The file to write out numbers to. The file will be created if it doesn't exist, or appended to if it does.
	 * @throws IOException if there was a problem writing the file.
	 */
	public static void writeOutNumbers(Path path) throws IOException {
		try (PrintWriter out = new PrintWriter(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND))) {
			Random random = new Random();
			
			for(int i=0; i<10; i++) {
				int val = random.nextInt(100); // I thought numbers from 0-99 would make for better display
				out.write(Integer.toString(val));
				out.write(" "); // This will leave a trailing space in the file, and make it easy to do subsequent appends
			}
			
			out.flush(); // Just in case
		}
	}
	
	/**
	 * Opens the specified file and reads in integers until the end of the file or non-integer data is found.
	 * @param path The file to read numbers from. If the file does not exist, this method will fail.
	 * @return The data read from the file
	 * @throws IOException if there was a problem reading numbers from the file - note that IOException will not be
	 *                     thrown if the file exists but there is no integer data to be read. An empty list will be
	 *                     returned instead.
	 */
	public static ArrayList<Integer> readInNumbers(Path path) throws IOException {
		ArrayList<Integer> result = new ArrayList<>();
		
		try (Scanner in = new Scanner(Files.newInputStream(DATA_PATH, StandardOpenOption.READ))) {
			// We're going to buffer all the ints into memory because the file's small and then we can separate the file read from the 
			
			while(in.hasNextInt()) {
				result.add(in.nextInt());
			}
		}
		
		return result;
	}
	
	
	public static void main(String... args) {
		
		// Normally we don't stack the exceptions like this with System.exit()'s. I chose to do things this way for
		// readability, because the *desired* result here is to fail/exit with a message that the user could understand,
		// rather than a large stack trace. In prod we'd also want to combine this with logging the error separately.
		
		try {
			System.out.println("Writing out some random numbers to \"data.file\"...");
			writeOutNumbers(DATA_PATH);
			System.out.println("Data written.");
			System.out.println();
		} catch (IOException ex) {
			System.out.println("There was a problem writing to the file.");
			System.exit(-1);
		}
			
		try {
			System.out.println("Reading in all data from the file...");
			ArrayList<Integer> fileData = readInNumbers(DATA_PATH);
			System.out.println("Read complete. The data is:");
			System.out.println(fileData);
		} catch (IOException ex) {
			System.out.println("There was a problem reading in the file with the new numbers added.");
			System.exit(-1);
		}
	}
}
