package pr1.a08;

import java.awt.Color;
import java.awt.Graphics;

import schimkat.berlin.lernhilfe2024ws.graphics.Drawable;

public class EinfacheLandschaft implements Drawable {
	final static Color HORIZONTCOLOR = Color.BLACK;
	final static int HORIZONT_Y = 150;
	final static int HORIZONT_WIDTH = 800;
	final static int SKYHEIGHT = 150;
	final static int HORIZONT_LINEHEIGHT = 1;
	final static int LANDHEIGHT = 100;
	final static int LANDSCAPE_HEIGHT = SKYHEIGHT + HORIZONT_LINEHEIGHT + LANDHEIGHT;
	protected Color skyColor;
	protected Color landColor;
	
	//vollst�ndiger Konstruktor
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
		g.fillRect(0, 0, HORIZONT_WIDTH, 150);
		g.setColor(HORIZONTCOLOR);
		g.drawLine(0, HORIZONT_Y, HORIZONT_WIDTH, HORIZONT_Y);
		g.setColor(landColor);
		g.fillRect(0, HORIZONT_Y + 1, HORIZONT_WIDTH, LANDHEIGHT);
	}
	
	public Color getSkyColor() {
		return skyColor;
	}
	
	public Color getLandColor() {
		return landColor;
	}
}
