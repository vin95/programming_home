package pr2.a08;

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
		
		radiusEye = calculateRadiusEye();
		diameterEye = calculateDiameterEye();
		middlePoint = calculateMiddlePoint();
		diameterPupil = calculateDiameterPupil();
		radiusPupil = calculateRadiusPupil();
		lengthNose = calculateLengthNose();
		widthNose = calculateWidthNose();
		positionEye1 = calculatePositionEye1();
		positionEye2 = calculatePositionEye2();
		positionPupil1 = calculatePositionPupil1();
		positionPupil2 = calculatePositionPupil2();
		positionNose = calculatePositionNose();
	}
	
	public double calculateRadiusEye(){
		setRadiusEye(getRadiusHead() * 0.25);
		return getRadiusHead();
	}
	
	public double calculateDiameterEye(){
		setDiameterEye(getRadiusEye() * 2);
		return getRadiusEye();
	}
	
	public Point2D.Double calculateMiddlePoint(){
		Point2D.Double position = new Point2D.Double (
				getPosition().getX() + getRadiusHead(),
				getPosition().getY() + getRadiusHead()
				);
		setMiddlePoint(position);
		return position;
	}
	
	public double calculateDiameterPupil() {
		setDiameterPupil(getDiameterEye() * 0.5);
		return getDiameterEye();
	}
	
	public double calculateRadiusPupil() {
		setRadiusPupil(getDiameterPupil() / 2);
		return getDiameterPupil();
	}
	
	public double calculateLengthNose() {
		setLengthNose(getDiameterEye() * 0.8);
		return getDiameterEye();
	}
	
	public double calculateWidthNose() {
		setWidthNose(getLengthNose() * 0.08);
		return getLengthNose();
	}
	
	public Point2D.Double calculatePositionEye1(){
		Point2D.Double position = new Point2D.Double (
				getMiddlePoint().getX() - getDiameterEye() - getRadiusHead() * 0.2,
				getPosition().getY() + getRadiusHead() / 2
				);
		setPositionEye1(position);
		return position;
	}
	
	public Point2D.Double calculatePositionEye2(){
		Point2D.Double position = new Point2D.Double (
				getMiddlePoint().getX() + getRadiusHead() * 0.2,
				getPositionEye1().getY()
				);
		setPositionEye2(position);
		return position;
	}
	
	public Point2D.Double calculatePositionPupil1(){
		Point2D.Double position = new Point2D.Double (
				getPositionEye1().getX() + getRadiusEye() - getRadiusPupil(),
				getPositionEye1().getY() + getRadiusEye() * 0.8 - getRadiusPupil()
				);
		setPositionPupil1(position);
		return position;
	}
	
	public Point2D.Double calculatePositionPupil2(){
		Point2D.Double position =  new Point2D.Double (
				getPositionEye2().getX() + getRadiusEye() - getRadiusPupil(),
				getPositionEye2().getY() + getRadiusEye() * 0.8 - getRadiusPupil()
				);
		setPositionPupil2(position);
		return position;
	}
	
	public Point2D.Double calculatePositionNose(){
		Point2D.Double position = new Point2D.Double(
				getMiddlePoint().getX() - getWidthNose() / 2,
				getMiddlePoint().getY() - getLengthNose() / 2
				);
		setPositionNose(position);
		return position;
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
	
	public Point2D getPositionEye1(){
		return positionEye1;
	}
	
	public void setPositionEye1(Point2D newPosition) {
		positionEye1 = newPosition;
	}
	
	public Point2D getPositionEye2(){
		return positionEye2;
	}
	
	public void setPositionEye2(Point2D newPosition) {
		positionEye2 = newPosition;
	}
	
	public double getDiameterEye() {
		return diameterEye;
	}
	
	public void setDiameterEye(double diameter) {
		diameterEye = diameter;
	}
	
	public Point2D getPositionPupil1(){
		return positionPupil1;
	}
	
	public void setPositionPupil1(Point2D position) {
		positionPupil1 = position;
	}
	
	public Point2D getPositionPupil2(){
		return positionPupil2;
	}
	
	public void setPositionPupil2(Point2D position) {
		positionPupil2 = position;
	}

	public double getDiameterPupil() {
		return diameterPupil;
	}
	
	public void setDiameterPupil(double diameter) {
		diameterPupil = diameter;
	}
	
	public double getLengthNose(){
		return lengthNose;
	}
	
	public void setLengthNose(double length){
		lengthNose = length;
	}

	public double getWidthNose(){
		return widthNose;
	}
	
	public void setWidthNose(double width){
		widthNose = width;
	}
	
	public Point2D getPositionNose(){
		return positionNose;
	}
	
	private void setPositionNose(Point2D position) {
		positionNose = position;
	}
	
	public Point2D getMiddlePoint(){
		return middlePoint;
	}
	
	public void setMiddlePoint(Point2D position) {
		middlePoint = position;
	}
	
	public void changeSize(double factor) {
		setRadiusHead(getRadiusHead() + getRadiusHead() * factor);
	}
	
	public void changeEyes(double factor) {
		setRadiusEye(getRadiusEye() + getRadiusEye() * factor);
		calculateDiameterEye();
		calculateDiameterPupil();
		calculateRadiusPupil();
	}
	
	public void update() {
		calculateRadiusEye();
		calculateDiameterEye();
		calculateMiddlePoint();
		calculateDiameterPupil();
		calculateRadiusPupil();
		calculateLengthNose();
		calculateWidthNose();
		calculatePositionEye1();
		calculatePositionEye2();
		calculatePositionPupil1();
		calculatePositionPupil2();
		calculatePositionNose();
	}
}
