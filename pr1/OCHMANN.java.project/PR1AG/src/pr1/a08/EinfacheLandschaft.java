package pr1.a08;

import java.awt.Color;
import java.awt.Graphics;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class EinfacheLandschaft implements Drawable {
	final static Color HORIZONTCOLOR = Color.BLACK;
	final static Color HORIZONTKOORDINATE = Color.BLACK;
	protected Color skyColor;
	protected Color landColor;
	
	//vollständiger Konstruktor
	public EinfacheLandschaft(Color skyColor, Color landColor) {
		this.skyColor = skyColor;
		this.landColor = landColor;
	}
	
	//Kopierkonstruktor
	public EinfacheLandschaft(EinfacheLandschaft einfacheLandschaft) {
		this(einfacheLandschaft.skyColor, einfacheLandschaft.landColor);
	}
	
	//Standardkonstruktor
	public EinfacheLandschaft() {
		this(Color.BLUE, Color.GREEN);
	}
	
	public void draw(Graphics g) {
		g.setColor(skyColor);
		g.fillRect(0, 0, 500, 150);
		g.setColor(HORIZONTCOLOR);
		g.drawLine(0, 150, 500, 150);
		g.setColor(landColor);
		g.fillRect(0, 151, 500, 100);
	}
	
	public Color getSkyColor() {
		return skyColor;
	}
	
	public Color getLandColor() {
		return landColor;
	}
}
