package module11;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The demoscene plasma effect is a matter of stacking sine waves. Each "plane" has a set of sine waves that vary along
 * the x axis, and a set of sine waves that vary along the y axis. The sine waves on a given axis are stacked with
 * reduced frequency and amplitude to create a nonlinear function with fractal ripples. When the horizontal and vertical
 * sine waves are stacked, they create a tiled, blobby shape. Using time to drive slow changes in the phase of each wave
 * causes the shape to evolve in interesting ways over time. We have a very zoomed-in view of this shape as is typical
 * for the effect.
 * 
 * Typically we remap the values out into a gradient, but I chose to use two "planes" of plasma to create a translucent,
 * layered effect that I find pleasing. Finally, we hue-shift each layer independently. The overall result has a lovely
 * lava-lamp feel to it.
 */
public class PlasmaEffect implements SceneEffect {
	Random random = new Random();
	Plane fg = new Plane(2, random);
	Plane bg = new Plane(2, random);
	
	public PlasmaEffect() {
		
	}
	
	@Override
	public void run(GraphicsContext context, long deltaTime) {
		fg.step();
		bg.step();
		
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, 128*3, 128*3);
		
		for(int y=0; y<128; y++) {
			for(int x=0; x<128; x++) {
				context.setFill(bg.colorAt(x, y));
				context.fillRect(x*3, y*3, 3, 3);
				
				context.setFill(fg.colorAt(x, y));
				context.fillRect(x*3, y*3, 3, 3);
			}
		}
		
	}
	
	
	/**
	 * Represents one octave of the function which varies continuously over an axis. Additionally, the
	 * phase of this sine wave varies over time, and there is a  facility for stepping through this phase evolution.
	 */
	public static class Octave {
		public double phase = 0.0;
		public double frequency = 0.1;
		public double amplitude = 1.0;
		public double dt = 0.1; // Radians per 60th of a sec
		
		/**
		 * Advance the phase by one "step". Call this at 60Hz.
		 */
		public void step() {
			phase += dt;
		}
		
		/**
		 * Gets the intensity at this x or y value. If you think of the nonlinear function as having a local X, or
		 * input value, and a local Y, or output value, we're asking, "what is the Y at this X?".
		 * @param i X or Y, depending on which axis this octave is affected by
		 * @return the intensity (local Y value) at the specified point (local X value) along the curve
		 */
		public double intensityAt(int i) {
			return Math.sin(i * frequency + phase);
		}
	}
	
	/**
	 * Represents a plane of plasma. Think of this as combining the nonlinear function of stacked sine waves on each
	 * axis into a single two-dimensional nonlinear function of solid noise. Formally,
	 * <p>p(x, y) = fx1(x) + fx2(x) ... + fy1(y) + fy2(y) ...
	 * <p>Additionally, a Plane has facilities for stepping its constituent functions and hue shifting over time.
	 */
	public static class Plane {
		Octave[] xOctaves;
		Octave[] yOctaves;
		
		public double hue = 260;        // Degrees
		public double sat = 1.0;        // 0..1
		public double lightness = 0.75; // 0..1
		public double hueDt = 1;        // Degrees per 60th of a sec
		
		
		public Plane(int octaveCount, Random random) {
			xOctaves = new Octave[octaveCount];
			fillOctaves(xOctaves, random);
			yOctaves = new Octave[octaveCount];
			fillOctaves(yOctaves, random);
			
			hue = random.nextInt(360);
			hueDt = random.nextGaussian() * 1.0;
		}
		
		/**
		 * Advances the plasma simulation by one "step". Call this at 60Hz.
		 */
		public void step() {
			for(Octave octave : xOctaves) octave.step();
			for(Octave octave : yOctaves) octave.step();
			
			hue += hueDt;
			if (hue < 0) hue += 360.0;
			if (hue >= 360) hue -= 360.0;
		}
		
		/**
		 * Samples the intensity at a particular pixel. Intensity is the sum of the nonlinear functions on each axis.
		 * @param x The x location to sample at
		 * @param y The y location to sample at
		 * @return The intensity value (that is, the sum of nonlinear functions) at these coordinates.
		 */
		public double intensityAt(int x, int y) {
			double intensity = 0;
			
			for(Octave octave : xOctaves) intensity += octave.intensityAt(x);
			for(Octave octave : yOctaves) intensity += octave.intensityAt(y);
			
			return intensity;
		}
		
		/**
		 * Samples the color at a particular pixel. This is the intensity, mapped to the alpha of the hue-shifted color.
		 * @param x The x location to sample at
		 * @param y The y location to sample at
		 * @return The color at these coordinates
		 */
		public Color colorAt(int x, int y) {
			double intensity = intensityAt(x, y);
			
			intensity *= 0.25; // scaling it down just a tiny bit helps avoid hard clamping
			intensity += 0.4;
			
			if (intensity > 1.0) intensity = 1.0;
			if (intensity < 0) intensity = 0.0;
			
			return Color.hsb(hue, sat, lightness, intensity);
		}
		
		/**
		 * Fills the provided array with new Octave functions, each with a random frequency and a random phase delta.
		 * Each successive octave's frequency and phase delta are lower so that the octave functions add up into a
		 * nonlinear fractal function.
		 * @param octaves The array of octaves. It's okay if this array contains nulls.
		 * @param random A random that will be used to populate each Octave's settings.
		 */
		private static void fillOctaves(Octave[] octaves, Random random) {
			for(int i=0; i<octaves.length; i++) {
				Octave octave = new Octave();
				octaves[i] = octave;
				
				octave.frequency = random.nextDouble() * 0.02 + 0.015;
				octave.frequency *=  1.0 / (i+1);
				octave.dt = random.nextGaussian() * 0.04 * (1.0 / (i+1));
			}
		}
	}
	
	
}