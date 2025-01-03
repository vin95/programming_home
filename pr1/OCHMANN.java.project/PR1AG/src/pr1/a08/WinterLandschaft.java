package pr1.a08;

import java.awt.Color;
import java.awt.Graphics;

public class WinterLandschaft extends Landschaft {
	final static int NUMBER_SCHNEEFLOCKEN = 100;

	public WinterLandschaft(Color skyColor, Color landColor, int numberTrees, int numberHills) {
		super(skyColor, landColor, numberTrees, numberHills);
	}

	public WinterLandschaft(WinterLandschaft winterLandschaft) {
		this(winterLandschaft.skyColor, winterLandschaft.landColor, winterLandschaft.numberTrees, winterLandschaft.numberHills);
	}

	public WinterLandschaft() {
		super();
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for (int schneeflocke = 0; schneeflocke < NUMBER_SCHNEEFLOCKEN; schneeflocke++) {
			int diameter = getRandomNumberInRange(10, 20);
			int x = getRandomNumberInRange(0, HORIZONT_WIDTH - diameter);
			int y = getRandomNumberInRange(0, LANDSCAPE_HEIGHT - diameter);
			g.setColor(Color.WHITE);
			
			for (int line = 0; line < 4; line++) {
				g.drawLine(x, y, x + diameter, y + diameter);
				g.drawLine(x + diameter / 2, y, x + diameter / 2, y + diameter );
				g.drawLine(x + diameter, y, x, y + diameter);
				g.drawLine(x, y + diameter / 2, x + diameter, y + diameter / 2);
			}
		}
		
		int xKoordinateOffset = 0;
		int widthSnowman = 30;
		int widthSnowball = widthSnowman;
		int heightSnowball = widthSnowball;
		int xSnowman = getRandomNumberInRange(100, HORIZONT_WIDTH - widthSnowman - 100 );
		int ySnowman = LANDSCAPE_HEIGHT - heightSnowball;
		int xSnowball = xSnowman;
//		int ySnowman = LANDSCAPE_HEIGHT;
		for (int schneekugel = 0; schneekugel < 3; schneekugel++) {
			
			g.fillOval(xSnowball, ySnowman, widthSnowball, heightSnowball);
			g.setColor(BLACK);
			g.drawOval(xSnowball, ySnowman, widthSnowball, heightSnowball);
			g.setColor(WHITE);
			
			int widthSnowballtmp = widthSnowball;
			widthSnowball -= (widthSnowball * 0.2);
			heightSnowball = widthSnowball;
			ySnowman -= heightSnowball;
			xKoordinateOffset += (widthSnowballtmp - widthSnowball) / 2;
			xSnowball = xSnowman + xKoordinateOffset;
		}
		// TODO Schneemann zeichnen einfach hardcodiert vorne in die Mitte
		// TODO Hügel in Landschaft zeichnen
	}

}
