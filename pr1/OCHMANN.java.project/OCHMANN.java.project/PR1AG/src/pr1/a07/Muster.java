package pr1.a07;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class Muster implements Drawable {
	protected List<java.awt.Rectangle> rectangles;
	protected Color color;
	
	public Muster(java.awt.Color color) {
		this.color = color;
		rectangles = new ArrayList<>();
		int numberOfRectangles = 7;
		int x = 0;
		
		for (int i = 0; i < numberOfRectangles; i++) {
			double width_tmp = 10 + 0.0725 * x;
			int width = (int) width_tmp;
			int height = width;
			double y_tmp = 400 - 0.01 * Math.pow(x -100, 2);
			int y = (int) y_tmp;
			rectangles.add(new Rectangle(x, y, width, height));
			x += 50; 
		}
	}

	public void draw(Graphics g) {
		for (int rectangle_i = 0; rectangle_i < rectangles.size(); rectangle_i++) {
			g.setColor(color);
			g.fillRect(
				rectangles.get(rectangle_i).x,
				rectangles.get(rectangle_i).y,
				rectangles.get(rectangle_i).width,
				rectangles.get(rectangle_i).height
			);
			g.drawRect(
				rectangles.get(rectangle_i).x,
				rectangles.get(rectangle_i).y, 
				rectangles.get(rectangle_i).width, 
				rectangles.get(rectangle_i).height
			);
		}
	}
}
