package pr1.a10;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;

import pr1.a07.Gitter;
import schimkat.berlin.lernhilfe2024ws.graphics.FunnyFirstPainter;

public class PointPainter {
	public static void main(String[] args) {
		
		Set<Ellipse2D.Double> allPoints = Convert.mapIntsToEllipses(DataProvider.integerListFrom("manyNumbers"));
		Set<Ellipse2D.Double> mengeA = new HashSet<>();
		
		for (Ellipse2D.Double point : allPoints) {
			mengeA.add(point);
		}
		
		FunnyFirstPainter painter1 = new FunnyFirstPainter();
		painter1.add(new VisiblePoints(Color.BLUE, mengeA));
		painter1.showDrawing();
		
		PointFilter circleFilter = new CircleFilter();
		Set<Ellipse2D.Double> mengeB = Convert.filtered(mengeA, circleFilter);
		
		FunnyFirstPainter painter2 = new FunnyFirstPainter();
		painter2.add(new Gitter(Color.LIGHT_GRAY, 10));
		painter2.add(new Gitter(Color.DARK_GRAY, 100));
		painter2.add(new VisiblePoints(Color.YELLOW, mengeB));
		painter2.showDrawing();
		
		PointFilter squareFilter = new SquareFilter();
		Set<Ellipse2D.Double> mengeC = Convert.filtered(mengeA, squareFilter);
		
		FunnyFirstPainter painter3 = new FunnyFirstPainter();
		painter3.add(new Gitter(Color.LIGHT_GRAY, 10));
		painter3.add(new Gitter(Color.DARK_GRAY, 100));
		painter3.add(new VisiblePoints(Color.RED, mengeC));
		painter3.showDrawing();
		
		FunnyFirstPainter painter4 = new FunnyFirstPainter();
		painter4.add(new VisiblePoints(Color.RED, mengeC));
		painter4.add(new VisiblePoints(Color.YELLOW, mengeB));
		painter4.showDrawing();
		
		Set<Ellipse2D.Double> mengeE = new HashSet<>();
		
		for (Ellipse2D.Double point : mengeC) {
			if (mengeB.contains(point)) {
				mengeE.add(point);
			}
		}
		
		FunnyFirstPainter painter5 = new FunnyFirstPainter();
		painter5.add(new VisiblePoints(Color.ORANGE, mengeE));
		painter5.showDrawing();
		
		
	}
}
