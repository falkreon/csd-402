package module11;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Demoscene fire is an elementary cellular automation where we splat fully-on cells at the bottom each frame. During
 * the cellular automation, we sample ourselves and the three pixels below us to produce our new value, which creates a
 * blur-and-darken effect that spreads values up and out. We can then use sine waves varying over time to bias that
 * sampling and create a believable flicker. Finally, we map the double values to brightness values to turn the
 * scalar field into an image.
 */
public class FireEffect implements SceneEffect {
	private final Random random = new Random();
	private final ScalarField field = new ScalarField(128, 65); // 1 extra "pixel" of Y that isn't drawn
	private final double flickerAmplitude = 0.3;
	private final double flickerFrequency = 1.0;
	private double flickerPhase = 0.0;
	private double flickerPhaseDelta = 0.06;
	
	@Override
	public void run(GraphicsContext context, long deltaTime) {
		// Splat bright cells at the bottom
		for(int x=0; x<128; x++) {
			double pixelValue = (random.nextInt(100) < 20) ? 4096 : 0;
			field.put(x, 64, pixelValue);
		}
		
		// Update flicker
		flickerPhase += flickerPhaseDelta;
		
		
		// Blur and darken
		for(int y=0; y<64; y++) { // Skips row 64 - Don't blur the bottom row
			
			double flicker = Math.sin(flickerPhase * flickerFrequency + (y / 8)) * flickerAmplitude;
			flicker = flicker * 0.5 + 0.5;
			if (flicker < 0.0) flicker = 0.0;
			if (flicker > 1.0) flicker = 1.0;
			
			for(int x=0; x<128; x++) {
				double cur = field.get(x, y) * 0.95;
				double sw = field.get(x-1, y+1);
				double s = field.get(x, y+1);
				double se = field.get(x+1, y+1);
				
				// We weight sw, s, and se based on a quadratic bezier whose t value is sampled from a time-varying sine wave.
				double t = flicker;
				double bsw = (1-t) * (1-t) * sw;
				double bs = 2 * (1-t) * t * s;
				double bse = t * t * se;
				
				double result = bsw + bs + bse;
				
				// Mix bezier result with existing pixel - we leave about 3% out of the bezier side so that the fire
				// darkens as it spreads upwards.
				result = (result * 0.92) + (cur * 0.05);
				
				
				field.put(x, y, result);
			}
		}
		
		// No gradient, just map 0..128 to 0..255 and drive pixel brightness for now
		for(int y=0; y<65; y++) {
			for(int x=0; x<128; x++) {
				double cell = field.get(x, y);
				
				//Scale from 4096 down to 0..1
				double intensity = cell / 1024.0;
				if (intensity < 0) intensity = 0;
				if (intensity > 1.0) intensity = 1.0;
				
				Color col = Color.hsb(340, 1.0, intensity);
				context.setFill(col);
				context.fillRect(x*3, y*6, 3, 6);
			}
		}
	}
	
	
	
	
	/**
	 * Represents a finite scalar field with double values at each point. Out-of-bounds cells are considered empty,
	 * and out-of-bounds access is allowed. Setting out-of-bounds cells is a no-op but likewise does not throw.
	 */
	public static class ScalarField {
		private int width;
		private int height;
		private double[] data;
		
		public ScalarField(int width, int height) {
			this.width = width;
			this.height = height;
			this.data = new double[width * height];
		}
		
		/**
		 * Gets the scalar value at the specified coordinates.
		 * @param x the x coordinate we should read from
		 * @param y the y coordinate we should read from
		 * @return the scalar value at these coordinates if they're inside the scalar field, otherwise zero.
		 */
		public double get(int x, int y) {
			if (x < 0 || y < 0 || x >= width || y >= height) return 0.0;
			return data[y * width + x];
		}
		
		/**
		 * Sets the scalar value at the specified coordinates if they're inside the scalar field, otherwise does nothing.
		 * @param x the x coordinate where we should place the value
		 * @param y the y coordinate where we should place the value
		 * @param value the new value for this location in the scalar field
		 */
		public void put(int x, int y, double value) {
			if (x < 0 || y < 0 || x >= width || y >= height) return;
			data[y * width + x] = value;
		}
	}
}
