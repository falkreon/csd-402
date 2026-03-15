package module11;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

/**
 * Concrete implementation of the abstract AnimationTimer class that triggers a request to paint a SceneEffect into the
 * provided Canvas. Calculates the delta-time. Experimentally this doesn't ever seem to vary from 60Hz. I
 * nonetheless implement a fixed timestep here to prevent the frame rate from exceeding 60 on other platforms.
 */
public class CustomAnimationTimer extends AnimationTimer {
	private static final long NANOS_PER_MSEC = 1_000_000;
	
	private boolean firstTick = true;
	private long lastNanos = 0L;
	private long partialTicks = 0L;
	private Canvas canvas = null;
	private SceneEffect currentEffect = null;
	
	/**
	 * Sets the SceneEffect that will be drawn each animation frame
	 * @param effect The effect to run from now on
	 */
	public void setEffect(SceneEffect effect) {
		this.currentEffect = effect;
	}
	
	/**
	 * Sets the canvas the SeneEffect should be drawn to
	 * @param canvas The canvas to draw effects on
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void handle(long curNanos) {
		if (firstTick) {
			lastNanos = curNanos;
			firstTick = false;
		}
		final long deltaMsec = (curNanos - lastNanos) / NANOS_PER_MSEC;
		partialTicks += deltaMsec;
		lastNanos = curNanos;
		
		if (currentEffect == null) return;
		if (canvas == null) return;
		
		if (partialTicks >= 17) {
			partialTicks -= 17;
			currentEffect.run(canvas.getGraphicsContext2D(), partialTicks);
		}
		
	}
	
}
