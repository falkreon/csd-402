package module11;

import javafx.scene.canvas.GraphicsContext;

public interface SceneEffect {
	void run(GraphicsContext context, long deltaTime);
}
