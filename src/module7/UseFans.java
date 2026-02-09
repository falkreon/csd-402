/**
 * CSD 402: Java for Programmers
 * Module 7: Oriented Class and Thinking
 *   Part 2: Programming Assignment
 *   Create a collection of fans and manipulate them together.
 *   
 * Isaac Ellingson
 * 2/4/2026
 */

package module7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UseFans {
	
	/* Helper stuff */
	
	private static <T> T selectRandom(T[] values) {
		int index = (int) (Math.random() * values.length);
		index %= values.length; // Shouldn't have to, but let's be safe.
		return values[index];
	}
	
	private static <T extends Enum<T>> T selectRandom(Class<T> t) {
		T[] values = t.getEnumConstants();
		return selectRandom(values);
	}
	
	private static String[] AVAILABLE_FAN_COLORS = { "white", "black", "red", "blue", "silver", "olive", "teal", "brown" };
	
	
	
	
	/* Required methods */
	
	public static void printFan(Fan fan) {
		System.out.println("{ speed = " + fan.getSpeed() +
				", radius = " + fan.getRadius() +
				", color = \"" + fan.getColor() +
				", isOn? = " + fan.isOn() +
				"\" }");
	}
	
	public static void printFans(Collection<Fan> fans) {
		System.out.println("[");
		for(Fan fan : fans) {
			System.out.print("   ");
			printFan(fan);
		}
		System.out.println("]");
	}
	
	
	public static void main(String... args) {
		List<Fan> fanCollection = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			fanCollection.add(new Fan(selectRandom(FanSpeed.class), (int) (Math.random() * 28) + 4, selectRandom(AVAILABLE_FAN_COLORS)));
		}
		
		System.out.println("Initial state:");
		printFans(fanCollection);
		
		System.out.println();
		System.out.println("Increasing everything's radius...");
		for(Fan fan : fanCollection) { fan.setRadius(fan.getRadius() + 2); }
		printFans(fanCollection);
		
		System.out.println();
		System.out.println("Speeding everything up...");
		for(Fan fan : fanCollection) { fan.setSpeed(fan.getSpeed().faster()); }
		printFans(fanCollection);
		
		System.out.println();
		System.out.println("Making all the colors more exiting...");
		for(Fan fan : fanCollection) { fan.setColor(fan.getColor() + "!"); }
		printFans(fanCollection);
		
		System.out.println();
		System.out.println("Turning all the fans off...");
		for(Fan fan : fanCollection) { fan.setSpeed(FanSpeed.STOPPED); }
		printFans(fanCollection);
	}
}
