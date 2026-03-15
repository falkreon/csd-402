/**
 * CSD402: Java for Programmers
 * Mopdule 11: JavaFX Basics
 *     Assignment 2: Written Assignment
 * Isaac Ellingson
 * 3/14/2026
 * 
 * This is an example program created to illustrate concepts from the written assignment - specifically, a hierarchial
 * component layout, combining VBox and HBox into something not purely one-dimensional. It also highlights how these
 * layout containers can be declared in FXML and CSS instead of code, although some pretty stinky workarounds needed to
 * be done to accommodate the lack of text blocks in Java 10, and JavaFX's difficulty of ingesting a String resource as
 * CSS.
 */

package module11;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Example extends Application {
	
	@FXML
	private Canvas canvas;
	
	// Sadly we do not have access to text blocks in Java 10. In modern Java this could be inlined more cleanly.
	// But this is the declarative representation of our GUI.
	public static final String GUI_FXML = String.join("\n",
		"<?import javafx.scene.control.*?>",
		"<?import javafx.scene.layout.*?>",
		"<?import javafx.scene.canvas.Canvas?>",
		"<VBox xmlns='http://javafx.com/javafx/17' xmlns:fx='http://javafx.com/fxml/1' prefWidth='384'>",
		"  <children>",
		"    <Canvas fx:id='canvas' width='384' height='384'/>",
		"    <HBox xmlns:fx='http://javafx.com/fxml'>",
		"      <children>",
		"        <Button text='Plasma' onAction='#choosePlasma'/>",
		"        <Button text='Fire' onAction='#chooseFire'/>",
		"        <Button text='Metaball' onAction='#chooseMetaball'/>",
		"      </children>",
		"    </HBox>",

		"  </children>",
		"</VBox>"
		);
	
	/*
	 * See https://stackoverflow.com/a/24754262.
	 * As of JavaFX 17 (which I am using as it is the one I'm testing with), data URIs are supported. I have a strong
	 * suspicion that trying to apply this CSS will fail with a log message in JDK 10. However, my tests of css failures
	 * indicate that it gracefully degrades with a logged warning, and the program is usable without the stylesheet.
	 * The primary objective here is to avoid external resources, and then to make everything as declarative and as
	 * pleasant as possible, so this is the best I can do for now.
	 */
	public static final String GUI_CSS = String.join("\n",
		"data:text/css;charset=utf-8,",
		".root {",
		"  ",
		"}",
		"",
		"VBox {",
		"  -fx-background-color: #444;",
		"  -fx-alignment: top-center;",
		"  -fx-spacing: 10px;",
		"  -fx-padding: 10px;",
		"}",
		"",
		"HBox {",
		"  -fx-alignment: top-center;",
		"  -fx-spacing: 10px;",
		"  -fx-padding: 10px;",
		"}",
		"",
		"Button {",
		"  -fx-pref-width: 200px;",
		"  -fx-pref-height: 64px;",
		"  -fx-font-size: 14pt;",
		"}"
		);
	
	public static final SceneEffect PLASMA = new PlasmaEffect();
	public static final SceneEffect FIRE = new FireEffect();
	public static final SceneEffect METABALL = new MetaballEffect();
	
	private static CustomAnimationTimer animationTimer = new CustomAnimationTimer();
	
	/**
	 * Switches the effect the animationTimer drives to the "plasma" demoscene effect
	 */
	public void choosePlasma() {
		animationTimer.setEffect(PLASMA);
	}
	
	/**
	 * Switches the effect the animationTimer drives to the "fire" demoscene effect
	 */
	public void chooseFire() {
		animationTimer.setEffect(FIRE);
	}
	
	/**
	 * Switches the effect the animationTimer drives to the "metaball" demoscene effect
	 */
	public void chooseMetaball() {
		animationTimer.setEffect(METABALL);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setMaxHeight(490);
		primaryStage.setMinHeight(490);
		primaryStage.setMinWidth(430);
		// Stage can be dragged wider. Doesn't help, but illustrates how everything repositions nicely.
		
		//Load the component hierarchy from the string constant above, and attempt to add the stylesheet to it
		FXMLLoader loader = new FXMLLoader(StandardCharsets.UTF_8);
		loader.setController(this);
		try {
			Parent root = loader.load(new ByteArrayInputStream(GUI_FXML.getBytes(StandardCharsets.UTF_8)));
			root.getStylesheets().add(GUI_CSS);
			
			// Configure our scene based on the loaded data and hardcode a few important things
			Scene scene = new Scene(root, 500, 480);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaFX Demoscene Effect Examples");
			
			animationTimer.setEffect(PLASMA);
			animationTimer.setCanvas(canvas);
			
			// Now that everything's configured, show the window and start the animation loop
			primaryStage.show();
			animationTimer.start();
		} catch (IOException e) {
			// ByteArrayInputStream never throws, so this code will never execute
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	// Not necessary in typical JavaFX; Helps launch more easily from Eclipse
	public static void main(String... args) {
		launch(args);
	}

}
