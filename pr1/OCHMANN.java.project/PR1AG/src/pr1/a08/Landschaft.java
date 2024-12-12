package pr1.a08;

import java.awt.Color;
import java.awt.Graphics;

public class Landschaft extends EinfacheLandschaft {
	
	protected int numberTrees;
	protected int numberHills;
	protected Color treeColor = Color.GREEN.darker();
	
	//vollständiger Konstruktor
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
		for(int tree = 0; tree < numberTrees; tree ++) {
			g.drawOval(, y, 20, 50);;
			g.drawRect(numberHills, numberHills, numberTrees, numberHills);
		}
	}
}
