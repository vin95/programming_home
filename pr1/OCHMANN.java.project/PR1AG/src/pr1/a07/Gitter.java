package pr1.a07;

import java.awt.Graphics;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class Gitter implements Drawable {
	
	java.awt.Color color;
	int dist;
	
	public Gitter(java.awt.Color color, int dist ) {
		this.color = color;
		this.dist = dist;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void draw(Graphics g) {
		final int STARTVALUE = -100;
		final int ENDVALUE = 1000;
		
		g.setColor(color);
		
		for(int x = STARTVALUE; x <= ENDVALUE; x += dist  ) {
			g.drawLine(x, STARTVALUE, x, ENDVALUE);
		}
		
		for(int y = STARTVALUE; y <= ENDVALUE; y+= dist  ) {
			g.drawLine(STARTVALUE, y, ENDVALUE, y);
		}
	}
}
