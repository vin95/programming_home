package pr2.a07;

import java.awt.geom.Point2D;

public class SmileyModel {
	protected Point2D position;
	protected double radiusHead;
	protected Point2D middlePoint;
	protected double radiusEye;
	protected double diameterEye;
	protected Point2D positionEye1;
	protected Point2D positionEye2;
	protected double radiusPupil;
	protected double diameterPupil;
	protected Point2D positionPupil1;
	protected Point2D positionPupil2;
	protected double lengthNose;
	protected double widthNose;
	protected Point2D positionNose;
	protected boolean smile;
	
	public SmileyModel(Point2D position, double radiusHead, boolean smile) {
		this.position = position;
		this.radiusHead = radiusHead;
		this.smile = smile;
		
		radiusEye = radiusHead * 0.25;
		diameterEye = radiusEye * 2;
		middlePoint = new Point2D.Double (
				position.getX() + radiusHead,
				position.getY() + radiusHead
				);
		diameterPupil = diameterEye * 0.5;
		radiusPupil = diameterPupil / 2;
		lengthNose = diameterEye * 0.8;
		widthNose = lengthNose * 0.08;
		positionEye1 = new Point2D.Double (
				middlePoint.getX() - diameterEye - radiusHead * 0.2,
				position.getY() + radiusHead / 2
				);
		positionEye2 = new Point2D.Double (
				middlePoint.getX() + radiusHead * 0.2,
				positionEye1.getY()
				);
		positionPupil1 = new Point2D.Double (
				positionEye1.getX() + radiusEye - radiusPupil,
				positionEye1.getY() + radiusEye * 0.8 - radiusPupil
				);
		positionPupil2 = new Point2D.Double (
				positionEye2.getX() + radiusEye - radiusPupil,
				positionEye2.getY() + radiusEye * 0.8 - radiusPupil
				);
		positionNose = new Point2D.Double (
				middlePoint.getX() - widthNose / 2,
				middlePoint.getY() - lengthNose / 2
				);
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
	
	public boolean getSmil(){
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
	
	public Point2D getPositionEye1(){
		return positionEye1;
	}
	
	public Point2D getPositionEye2(){
		return positionEye2;
	}
	
	public double getDiameterEye() {
		return diameterEye;
	}
	
	public Point2D getPositionPupil1(){
		return positionPupil1;
	}
	
	public Point2D getPositionPupil2(){
		return positionPupil2;
	}

	public double getDiameterPupil() {
		return diameterPupil;
	}
	
	public double getLengthNose(){
		return lengthNose;
	}

	public double getWidthNose(){
		return widthNose;
	}
	
	public Point2D getPositionNose(){
		return positionNose;
	}
	
	public void setLengthNose(double newLenghtNose) {
		lengthNose = newLenghtNose;
	}
	
	public Point2D getMiddlePoint(){
		return middlePoint;
	}
}
