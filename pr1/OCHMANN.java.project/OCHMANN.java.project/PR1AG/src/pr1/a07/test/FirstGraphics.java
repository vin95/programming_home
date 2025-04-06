package pr1.a07.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class FirstGraphics implements Drawable {
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(11, 22, 222, 111);
		g.drawLine(77, 44, 333, 444);
		
		Line2D line = new Line2D.Double(11, 555, 22, 55);
		Rectangle2D rect = new Rectangle2D.Double(11, 11, 50, 90);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.CYAN);
		g2d.draw(line);
		g2d.fill(rect);
	}
}
