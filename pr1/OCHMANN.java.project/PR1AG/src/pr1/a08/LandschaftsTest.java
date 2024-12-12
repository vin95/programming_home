package pr1.a08;

import schimkat.berlin.lernhilfe2024ws.graphics.FunnyFirstPainter;

public class LandschaftsTest {
	public static void main(String[] args) {
		FunnyFirstPainter painter1 = new FunnyFirstPainter();
		painter1.add(new EinfacheLandschaft());
		painter1.add(new Landschaft());
		painter1.showDrawing();
	}
	
	
}
