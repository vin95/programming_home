package pr1.a08;

import java.awt.Color;
import java.awt.Graphics;

public class Landschaft extends EinfacheLandschaft {
	
	final static Color BROWN = new Color(0.4f, 0.20f, 0.05f);
	final static Color DARKGREEN = Color.GREEN.darker().darker();
	final static Color BLACK = Color.BLACK;
	final static Color WHITE = Color.WHITE;
	final static int VERTICAL_MIDDLE_OF_LAND = LANDHEIGHT / 2;
	final static int WIDTH_TREETRUNKS = 10;
	final static int HEIGHT_TREETRUNKS = 50;
	final static int WIDTH_TREECROWNS = 40;
	final static int HEIGHT_TREECROWNS = HEIGHT_TREETRUNKS;
	final static int OFFSET_TO_CENTER_TREECROWNS = (WIDTH_TREECROWNS - WIDTH_TREETRUNKS) / 2 ;
	final static int OFFSET_TO_OVERLAP_TREEPARTS = 35;
	final static int WIDTH_HILL = 150;
	protected int numberTrees;
	protected int numberHills;
	protected Color treeColor = DARKGREEN;
	
	//vollst�ndiger Konstruktor
	public Landschaft(Color skyColor, Color landColor, int numberTrees, int numberHills ) {
		super(skyColor, landColor);
		this.numberTrees = numberTrees;
		this.numberHills = numberHills;
	}
	
	//Kopierkonstruktor
		public Landschaft(Landschaft landschaft) {
			this(landschaft.skyColor, landschaft.landColor, landschaft.numberTrees, landschaft.numberHills);
		}
		
	//Standardkonstruktor
		public Landschaft() {
			this(Color.BLUE, Color.GREEN, 7, 2);
		}
	
	public void setTreecolor(Color newColor) {
		treeColor = newColor;
	}
	
	public int getNumberTrees() {
		return numberTrees;
	}
	
	public int getNumberHills() {
		return numberTrees;
	}
	
	public Color getNumbertRees() {
		return treeColor;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		int hillsOffsetX = (HORIZONT_WIDTH / numberHills / 2);
		for(int hill = 0; hill < numberHills; hill++) {
			int randomOffsetNegXToX = getRandomNumberInRange(-(-100/3  * numberHills + 1000/3), -100/3 * numberHills + 1000/3);
			int xPosLeft = randomOffsetNegXToX + getRandomNumberInRange(WIDTH_HILL + randomOffsetNegXToX, HORIZONT_WIDTH - WIDTH_HILL - randomOffsetNegXToX);
			int yPosLeft = getRandomNumberInRange(HORIZONT_Y, HORIZONT_Y + LANDHEIGHT - WIDTH_HILL / 2);
			int[] xKoordinatesTriangle = {xPosLeft, xPosLeft + WIDTH_HILL / 2, xPosLeft + WIDTH_HILL}; 
			int[] yKoordinatesTriangle = {yPosLeft, yPosLeft - WIDTH_HILL / 2, yPosLeft}; 
			g.setColor(landColor.darker().darker().darker().darker());
			g.fillPolygon(xKoordinatesTriangle, yKoordinatesTriangle, 3);
			g.setColor(BLACK);
			g.drawPolygon(xKoordinatesTriangle, yKoordinatesTriangle, 3);
		}
		
		int treesOffsetX = (HORIZONT_WIDTH / numberTrees / 2);
		int spaceToLeft = treesOffsetX;
		for(int tree = 0; tree < numberTrees; tree++) {
			int randomOffsetNegXToX = getRandomNumberInRange(-(30 / (int) Math.sqrt(numberTrees)), 30 / (int) Math.sqrt(numberTrees));
			int randomOffsetNegYToY = getRandomNumberInRange(-VERTICAL_MIDDLE_OF_LAND, VERTICAL_MIDDLE_OF_LAND);
			int yBaumunterkante = HORIZONT_Y + VERTICAL_MIDDLE_OF_LAND + randomOffsetNegYToY + HEIGHT_TREECROWNS + HEIGHT_TREETRUNKS;
			int xBaumkronenkanteRechts = spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS + WIDTH_TREECROWNS;
			
			
			// Abfrage1: Ragt der Baum unten und rechts raus?
			// Abfrage 2: Ragt der Baum unten heraus?
			// Abfrage 3: Ragt der Baum rechts heraus?
			// 4: der Baum kann mit seinen initialisierten Werten erzeugt werden
			if (yBaumunterkante > LANDSCAPE_HEIGHT && xBaumkronenkanteRechts > HORIZONT_WIDTH) {
				g.setColor(BROWN);
				g.fillRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				g.setColor(BLACK);
				g.drawRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				
				g.setColor(DARKGREEN);
				g.fillOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
				g.setColor(BLACK);
				g.drawOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
			} else if (yBaumunterkante > LANDSCAPE_HEIGHT) {
				g.setColor(BROWN);
				g.fillRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y + randomOffsetNegYToY, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				g.setColor(BLACK);
				g.drawRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y + randomOffsetNegYToY, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				
				g.setColor(DARKGREEN);
				g.fillOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y + randomOffsetNegYToY - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
				g.setColor(BLACK);
				g.drawOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y + randomOffsetNegYToY - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
			} else if (xBaumkronenkanteRechts > HORIZONT_WIDTH) {
				g.setColor(BROWN);
				g.fillRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				g.setColor(BLACK);
				g.drawRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				
				g.setColor(DARKGREEN);
				g.fillOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
				g.setColor(BLACK);
				g.drawOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, HORIZONT_Y - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
			} else {
				g.setColor(BROWN);
				g.fillRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y + VERTICAL_MIDDLE_OF_LAND + randomOffsetNegYToY, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				g.setColor(BLACK);
				g.drawRect(spaceToLeft + randomOffsetNegXToX, HORIZONT_Y + VERTICAL_MIDDLE_OF_LAND + randomOffsetNegYToY, WIDTH_TREETRUNKS, HEIGHT_TREETRUNKS);
				
				g.setColor(DARKGREEN);
				g.fillOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, (HORIZONT_Y + VERTICAL_MIDDLE_OF_LAND) + randomOffsetNegYToY - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
				g.setColor(BLACK);
				g.drawOval(spaceToLeft + randomOffsetNegXToX - OFFSET_TO_CENTER_TREECROWNS, (HORIZONT_Y + VERTICAL_MIDDLE_OF_LAND) + randomOffsetNegYToY - OFFSET_TO_OVERLAP_TREEPARTS, WIDTH_TREECROWNS, HEIGHT_TREECROWNS);
			}
			
			spaceToLeft += treesOffsetX * 2;
		}
		
	}
	
	public int getRandomNumberInRange(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
}
