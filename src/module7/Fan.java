/**
 * CSD 402: Java for Programmers
 * Fan class from module6
 * Isaac Ellingson
 * 2/4/2026
 */

package module7;

public class Fan {
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