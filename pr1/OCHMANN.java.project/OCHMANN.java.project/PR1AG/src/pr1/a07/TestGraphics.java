package pr1.a07;

import java.awt.Color;

import schimkat.berlin.lernhilfe2024ws.graphics.FunnyFirstPainter;

public class TestGraphics {
	public static void main(String[] args) {
		FunnyFirstPainter painter1 = new FunnyFirstPainter();
		painter1.add(new Gitter(Color.LIGHT_GRAY, 10));
		painter1.add(new Gitter(Color.GRAY, 50));
		painter1.add(new Gitter(Color.DARK_GRAY, 100));
		
		painter1.add(new Muster(Color.MAGENTA));
		painter1.showDrawing();
	}
}
