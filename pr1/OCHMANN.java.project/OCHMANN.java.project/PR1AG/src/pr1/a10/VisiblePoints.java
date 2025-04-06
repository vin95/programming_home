package pr1.a10;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.Set;
import java.awt.Color;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class VisiblePoints implements Drawable{
	Set<Ellipse2D.Double> points;
	Color color;
	
	public VisiblePoints(Color color, Set<Ellipse2D.Double> points) {
		this.points = points;
		this.color = color;
	}
	
	@Override
	public void draw(Graphics g) {
		for (Ellipse2D.Double point : points) {
			g.setColor(color);
			g.fillOval((int)point.getCenterX(), (int)point.getCenterY(), (int)point.getHeight(), (int)point.getWidth());
		}
	}

}
