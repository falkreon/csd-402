package blue.endless.csd402.module5;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Represents a rectangular table or grid of values of the same type.
 * @param <T> The type of each cell
 */
public class Grid<T> {
	private int width;
	private int height;
	private T[] data;
	
	/**
	 * Creates a new Grid. All cells will contain null.
	 * @param cellType The type of each cell.
	 * @param width  The width of the grid, in cells.
	 * @param height The height of the grid, in cells.
	 */
	@SuppressWarnings("unchecked")
	public Grid(Class<T> cellType, int width, int height) {
		if (width < 0 || height < 0) throw new IllegalArgumentException("Width and height must be nonnegative.");
		this.width = width;
		this.height = height;
		data = (T[]) Array.newInstance(cellType, width * height);
	}
	
	/**
	 * Creates a new Grid from existing data. The grid will be perfectly sized to fit the data in the array. If the
	 * array is ragged, "missing" cells will be filled with zero.
	 * @param arr The data to initialize the Grid with.
	 * @return A new Grid initialized with the specified data.
	 */
	public static Grid<Integer> of(int[][] arr) {
		// Figure out a width and height that will fit
		int height = arr.length;
		if (height == 0)  return new Grid<>(Integer.class, 0, 0);
		int width = 0;
		
		for(int y = 0; y < height; y++) {
			int[] row = arr[y];
			width = Math.max(width, row.length);
		}
		
		Grid<Integer> result = new Grid<>(Integer.class, width, height);
		result.fill(0);
		for(int y = 0; y < height; y++) {
			int[] row = arr[y];
			for(int x = 0; x < width; x++) {
				result.put(x, y, row[x]);
			}
		}
		
		return result;
	}
	
	/**
	 * Creates a new Grid from existing data. The grid will be perfectly sized to fit the data in the array. If the
	 * array is ragged, "missing" cells will be filled with zero.
	 * @param arr The data to initialize the Grid with.
	 * @return A new Grid initialized with the specified data.
	 */
	public static Grid<Double> of(double[][] arr) {
		// Figure out a width and height that will fit
		int height = arr.length;
		if (height == 0)  return new Grid<>(Double.class, 0, 0);
		int width = 0;
		
		for(int y = 0; y < height; y++) {
			double[] row = arr[y];
			width = Math.max(width, row.length);
		}
		
		Grid<Double> result = new Grid<>(Double.class, width, height);
		result.fill(0.0);
		for(int y = 0; y < height; y++) {
			double[] row = arr[y];
			for(int x = 0; x < width; x++) {
				result.put(x, y, row[x]);
			}
		}
		
		return result;
	}
	
	/**
	 * Creates a new Grid from existing data. The grid will be perfectly sized to fit the data in the array. If the
	 * array is ragged, "missing" cells will be filled with nulls.
	 * 
	 * <p>This fully-generic version may not necessarily fill all cells. Be sure to guard against nulls in this case!
	 * @param arr The data to initialize the Grid with.
	 * @return A new Grid initialized with the specified data.
	 */
	public static <T> Grid<T> of(T[][] arr, Class<T> elementType) {
		// Figure out a width and height that will fit
		int height = arr.length;
		if (height == 0)  return new Grid<>(elementType, 0, 0);
		int width = 0;
		
		for(int y = 0; y < height; y++) {
			T[] row = arr[y];
			width = Math.max(width, row.length);
		}
		
		Grid<T> result = new Grid<>(elementType, width, height);
		for(int y = 0; y < height; y++) {
			T[] row = arr[y];
			for(int x = 0; x < width; x++) {
				result.put(x, y, row[x]);
			}
		}
		
		return result;
	}
	
	/**
	 * Fills all cells in this Grid with the specified value.
	 * @param value The value to fill all cells with.
	 */
	public void fill(T value) {
		Arrays.fill(this.data, value);
	}
	
	/**
	 * Gets the width of this Grid, in cells.
	 * @return the width of this Grid.
	 */
	public int width() { return this.width; }
	
	/**
	 * Gets the height of this Grid, in cells.
	 * @return the height of this Grid.
	 */
	public int height() { return this.height; }
	
	/**
	 * Gets the value of the specified cell.
	 * @param x The x coordinate of the cell. Must be in the range 0 <= x < width.
	 * @param y The y coordinate of the cell. Must be in the range 0 <= y < height.
	 * @return The value of the specified cell. May be null!
	 */
	public T get(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) throw new IndexOutOfBoundsException("x and y must be nonnegative and less than ("+width+", "+height+").");
		return data[y * width + x];
	}
	
	/**
	 * Sets the value of the specified cell.
	 * @param x The x coordinate of the cell. Must be in the range 0 <= x < width.
	 * @param y The y coordinate of the cell. Must be in the range 0 <= y < height.
	 * @param value The new value for the specified cell.
	 */
	public void put(int x, int y, T value) {
		if (x < 0 || y < 0 || x >= width || y >= height) throw new IndexOutOfBoundsException("x and y must be nonnegative and less than ("+width+", "+height+").");
		data[y * width + x] = value;
	}

	public String toString() {
		int[] columnWidths = new int[width];
		Arrays.fill(columnWidths, 0);
		// Figure column widths
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				T cur = get(x, y);
				String s = (cur == null) ? "null" : cur.toString();
				int w = s.length();
				if (w > columnWidths[x]) columnWidths[x] = w;
			}
		}
		
		StringBuilder result = new StringBuilder();
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				T cur = get(x, y);
				String s = (cur == null) ? "null" : cur.toString();
				while (s.length() < columnWidths[x]) s = " " + s;
				result.append(s);
				result.append(", ");
			}
			result.append('\n');
		}
		
		return result.toString();
	}
}
