package module11;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Metaballs are kind of an SDF effect. Each frame, we add up the inverse squares of the distance to each metaball -
 * you can think of it as the magnitude of the gravity you'd feel at that point - and then we threshold that function
 * somewhere greater than zero.
 * 
 * <p>When you threshold further away from the "surface" isoline of a signed distance field, what you'll experience is
 * a softening or rounding of edges, it smooths over the differences between things, and at infinite distance,
 * everything is a sphere. We use this threshold rounding in the metaball effect to create ligatures between spheres
 * when they get close together. It looks goopy and weird and we love that.
 */
public class MetaballEffect implements SceneEffect {
	private Random random = new Random();
	private Ball[] balls = new Ball[15];
	
	public MetaballEffect() {
		for(int i=0; i<balls.length; i++) {
			Ball ball = new Ball();
			ball.x = random.nextDouble(128);
			ball.y = random.nextDouble(128);
			ball.vx = random.nextGaussian() * 0.2;
			ball.vy = random.nextGaussian() * 0.2;
			ball.radius = random.nextDouble(8) + 2;
			balls[i] = ball;
		}
	}
	
	@Override
	public void run(GraphicsContext context, long deltaTime) {
		for(Ball ball : balls) {
			ball.x += ball.vx;
			ball.y += ball.vy;
			if (ball.x < 0) {
				ball.x = 0;
				if (ball.vx < 0) ball.vx = -ball.vx;
			}
			if (ball.y < 0) {
				ball.y = 0;
				if (ball.vy < 0) ball.vy = -ball.vy;
			}
			if (ball.x >= 128) {
				ball.x = 127;
				if (ball.vx > 0) ball.vx = -ball.vx;
			}
			if (ball.y >= 128) {
				ball.y = 127;
				if (ball.vy > 0) ball.vy = -ball.vy;
			}
		}
		
		context.setFill(Color.BLACK);
		context.fillRect(0, 0, 384, 384);
		
		context.setFill(Color.BLUE);
		
		// Perform the thresholding
		for(int y=0; y<128; y++) {
			for(int x=0; x<128; x++) {
				double sum = 0;
				for(Ball ball : balls) {
					double invD2 = ball.invD2(x, y) * 3;
					if (invD2 > 0.000001) sum += invD2;
				}
				
				if (sum > 0.05) {
					context.fillRect(x * 3, y * 3, 3, 3);
				}
				
			}
		}
		
		/*
		 * An additional complication with how I'm creating metaballs of different radii is that I'm discarding the sign
		 * of the squared distance. This means that the *interiors* of the balls are hollow. Since this is a quick and
		 * dirty effect, we just paste a circle over top. But this is avoidable if we really wanted to.
		 */
		context.setFill(Color.BLUE);
		
		for(Ball ball : balls) {
			context.fillOval((ball.x - ball.radius) * 3, (ball.y - ball.radius) * 3, (ball.radius * 2 + 1) * 3, (ball.radius * 2 + 1) * 3);
		}
	}
	
	/**
	 * Represents a moving circle with a particular size, location, and velocity.
	 */
	public class Ball {
		public double x = 0;
		public double y = 0;
		public double vx = 0.0;
		public double vy = 0.0;
		public double radius;
		
		/**
		 * Gets the reciprocal of the squared distance to the surface of the ball.
		 * @param x the x coordinate that distance is being "observed" from
		 * @param y the y coordinate that distance is being "observed" from
		 * @return 1/d^2, or the reciprocal of the squared distance to the surface of the ball.
		 */
		public double invD2(double x, double y) {
			double dx = Math.abs(x - this.x);
			double dy = Math.abs(y - this.y);
			double d2 = (dx * dx) + (dy * dy) - (radius * radius);
			return 1 / d2;
		}
	}
}
