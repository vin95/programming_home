package pr1.a08;

import java.awt.Color;

import schimkat.berlin.lernhilfe2024ws.graphics.FunnyFirstPainter;

public class LandschaftsTest {
	public static void main(String[] args) {
		testEinfacheLandschft();
		testLandschaft();
		testWinterLandschaft();
	}
	
	public static void testEinfacheLandschft() {
		FunnyFirstPainter painter = new FunnyFirstPainter();
		painter.add(new EinfacheLandschaft(Color.BLUE, Color.GREEN.brighter()));
		painter.showDrawing();
	}
	
	public static void testLandschaft() {
		FunnyFirstPainter painter = new FunnyFirstPainter();
		painter.add(new Landschaft(Color.BLUE, Color.GREEN, 15, 4));
		painter.showDrawing();
	}

	public static void testWinterLandschaft() {
		FunnyFirstPainter painter = new FunnyFirstPainter();
		painter.add(new WinterLandschaft(Color.BLUE, new Color(0.85f, 0.85f, 0.85f), 10, 3));
		painter.showDrawing();
	}
}
