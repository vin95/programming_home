package pr2.a10;

import java.awt.geom.Point2D;

public class SmileyModel {
	protected Point2D position;
	protected boolean smile;
	protected double radiusHead;
	protected double radiusEye;
	protected double radiusPupil;
	
	public SmileyModel() {
		position = new Point2D.Double(0, 0);
		radiusHead = 100;
		smile = true;
		radiusEye = getRadiusHead() * 0.25;
		radiusPupil = getRadiusEye() * 0.3;
	}
	
	public SmileyModel(Point2D position, double radiusHead, boolean smile) {
		this.position = position;
		this.radiusHead = 100;
		this.smile = true;
		radiusEye = getRadiusHead() * 0.25;
		radiusPupil = getRadiusEye() * 0.5 / 2;
	}
	
	public double getRadiusEye(){
		return radiusEye;
	}
	
	public void setRadiusEye(double newRadiusEye) {
		radiusEye = newRadiusEye;
	}
	
	public double getRadiusPupil(){
		return radiusPupil;
	}
	
	public void setRadiusPupil(double newRadiusPupil) {
		radiusPupil = newRadiusPupil;
	}
	
	public double getRadiusHead(){
		return radiusHead;
	}
	
	public void setRadiusHead(double newRadiusHead) {
		radiusHead = newRadiusHead;
	}
	
	public boolean getSmile(){
		return smile;
	}
	
	public void setSmile(boolean newSmile) {
		smile = newSmile;
	}
	
	public Point2D getPosition(){
		return position;
	}
	
	public void setPosition(Point2D newPosition) {
		position = newPosition;
	}	
}
