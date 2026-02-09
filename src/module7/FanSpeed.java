/**
 * CSD 402: Java for Programmers
 * FanSpeed class from module6
 * Isaac Ellingson
 * 2/4/2026
 */

package module7;

/**
 * Represents the states or speeds that a fan is permitted to be in.
 */
public enum FanSpeed {
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