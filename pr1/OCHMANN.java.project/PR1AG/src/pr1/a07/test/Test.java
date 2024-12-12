package pr1.a07.test;

import java.awt.Color;

import pr1.a07.Gitter;
import schimkat.berlin.lernhilfe2024ws.graphics.FunnyFirstPainter;

public class Test {
	public static void main(String[] args) {
		FunnyFirstPainter painter = new FunnyFirstPainter();
		painter.add(new FirstGraphics());
		painter.showDrawing();
		
		FunnyFirstPainter painter1 = new FunnyFirstPainter();
		painter1.add(new Gitter(Color.GREEN, 10));
		painter1.add(new Gitter(Color.BLUE, 100));
		painter1.showDrawing();
	}
}
