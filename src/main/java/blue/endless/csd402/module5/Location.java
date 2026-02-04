package blue.endless.csd402.module5;

import java.util.Objects;

/**
 * Represents a location on the coordinate plane. Location will increase left-to-right with increasing X, and
 * top-to-bottom with increasing Y. Locations start at 0, with 0,0 being the left-most and top-most value. Negative
 * values are allowed, but should be considered purely sentinel values.
 */
public class Location {
	private final int x;
	private final int y;
	
	/**
	 * Creates a new Location
	 * @param x The x coordinate (left to right) of the new Location
	 * @param y The y coordinate (top to bottom) of the new Location
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate of this Location
	 * @return the x coordinate
	 */
	public int x() { return this.x; }
	
	/**
	 * Gets the y coordinate of this Location
	 * @return the y coordinate
	 */
	public int y() { return this.y; }
	
	public String toString() {
		return "( " + x + ", " + y + " )";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Location)) return false; // Also filters out null
		Location other = (Location) obj;
		return other.x() == this.x && other.y() == this.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
