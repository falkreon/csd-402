/*
 * CSD 402: Java for Programmers
 * Module 6: Objects and Classes
 *   Part 2: Programming Assignment
 *   Create and manage a very simple state machine
 *   
 * Isaac Ellingson
 * 2/4/2026
 */

package blue.endless.csd402.module6;

public class Module6_2 {
	/**
	 * Represents the states or speeds that a fan is permitted to be in.
	 */
	public static enum FanSpeed {
		STOPPED,
		SLOW,
		MEDIUM,
		FAST;
		
		/**
		 * Gets the next slower speed for fans. If the fan is STOPPED, also returns STOPPED.
		 * @return the next slower speed, if possible, otherwise STOPPED.
		 */
		public FanSpeed slower() {
			if (this == STOPPED) return STOPPED;
			return values()[ this.ordinal() - 1 ];
		}
		
		/**
		 * Gets the next faster speed for fans. If the fan is FAST, also returns FAST.
		 * @return the next faster speed, if possible, otherwise FAST.
		 */
		public FanSpeed faster() {
			if (this == FAST) return FAST;
			return values()[ this.ordinal() + 1];
		}
	}
	
	public static class Fan {
		/* The world's largest fan is currently somewhere between 24-30 feet in diameter, it's a High-Volume-Low-Speed
		 * fan. There was allegedly a 100-meter fan in development by the Ural State Mining University, but it's unclear
		 * whether that was ever real. Either way, it never materialized, and it's a decent maximum value.
		 */
		private static final int LARGEST_FAN = 3937; // We assume here that radius is in inches.
		
		// Fields
		private FanSpeed speed = FanSpeed.STOPPED;
		// We do not have a backing boolean field for "on", because that would make invalid states representable.
		// Instead, "off" is the same as STOPPED.
		private double radius = 6.0;
		private String color = "white";
		
		
		//Constructors
		public Fan() {}
		
		public Fan(FanSpeed speed, double radius, String color) {
			this.speed = speed;
			this.radius = radius;
			this.color = color;
		}
		
		
		// Accessors
		public FanSpeed getSpeed() { return speed; }
		public boolean isOn() { return speed != FanSpeed.STOPPED; }
		public double getRadius() { return radius; }
		public String getColor() { return color; }
		
		
		// Mutators
		public void setSpeed(FanSpeed speed) {
			if (speed == null) throw new IllegalArgumentException("Speed must not be null.");
			this.speed = speed;
		}
		
		public void setRadius(double radius) {
			if (radius <= 0.0) throw new IllegalArgumentException("Radius must be greater than zero.");
			if (radius > LARGEST_FAN) throw new IllegalArgumentException("Radius must be at most " + LARGEST_FAN + ".");
			this.radius = radius;
		}
		
		public void setColor(String color) {
			if (color == null || color.isEmpty()) throw new IllegalArgumentException("Color must not be null or blank.");
			this.color = color;
		}
		
		@Override
		public String toString() {
			return "{ speed = " + speed + ", radius = " + radius + ", color = \"" + color + "\" }";
		}
	}
	
	public static void main(String... args) {
		Fan defaultFan = new Fan();
		System.out.println("No-Arg Constructor: " + defaultFan);
		System.out.println("Default fan is on?: " + defaultFan.isOn());
		System.out.println("Adjusting default fan's color to \"red\"...");
		defaultFan.setColor("red");
		System.out.println("Adjusting default fan's radius to 12...");
		defaultFan.setRadius(12.0);
		System.out.println("New state of default fan: " + defaultFan);
		
		System.out.println();
		
		Fan explicitFan = new Fan(FanSpeed.SLOW, 8.0, "blue");
		System.out.println("Explicit Constructor Arguments: " + explicitFan);
		System.out.println("Explicit fan is on?: " + explicitFan.isOn());
		System.out.println("Explicit fan color: \"" + explicitFan.getColor() + "\"");
		
		System.out.println("Adjusting explicit fan to go faster...");
		explicitFan.setSpeed(explicitFan.getSpeed().faster());
		
		System.out.println("New state of explicit fan: " + explicitFan);
	}
}
