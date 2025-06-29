package pr2.a10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SmileyView extends JPanel {
	protected SmileyModel model;
	protected Point2D position;
	protected Boolean smile;
	
	protected Point2D middlePoint;
	protected double diameterEye;
	protected Point2D positionEye1;
	protected Point2D positionEye2;
	protected double diameterPupil;
	protected Point2D positionPupil1;
	protected Point2D positionPupil2;
	protected double lengthNose;
	protected double widthNose;
	protected Point2D positionNose;
	
	public SmileyView (SmileyModel model) {
		this.model = model;
		position = model.getPosition();
		smile = model.getSmile();
		prepareDrawing();
	}
	
	public void prepareDrawing() {
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
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawHead(g2);
		drawEyes(g2);
		drawPupils(g2);
		drawMouth(g2);
		drawNose(g2);
	}
	
	protected void drawHead(Graphics2D g2) {
		Ellipse2D head = new Ellipse2D.Double(
				model.getPosition().getX(),
				model.getPosition().getY(),
				2 * model.getRadiusHead(),
				2 * model.getRadiusHead()
				);
		g2.setPaint(Color.BLACK);
		g2.draw(head);
		if (model.getSmile()) {
			g2.setPaint(Color.GREEN);
		} else {
			g2.setPaint(Color.RED);
		}
		g2.fill(head);
	}
	
	protected void drawEyes(Graphics2D g2) {
		Ellipse2D auge1 = new Ellipse2D.Double(
				getPositionEye1().getX(),
				getPositionEye1().getY(),
				getDiameterEye(),
				getDiameterEye() * 1.2
				);
		Ellipse2D auge2 = new Ellipse2D.Double(
				getPositionEye2().getX(),
				getPositionEye2().getY(),
				getDiameterEye(),
				getDiameterEye() * 1.2
				);
		g2.setPaint(Color.BLACK);
		g2.draw(auge1);
		g2.draw(auge2);
		g2.setPaint(Color.WHITE);
		g2.fill(auge1);
		g2.fill(auge2);
	}
	
	protected void drawPupils(Graphics2D g2) {
		Ellipse2D pupil1 = new Ellipse2D.Double(
				getPositionPupil1().getX(),
				getPositionPupil1().getY(),
				getDiameterPupil(),
				getDiameterPupil() * 1.2
				);
		Ellipse2D pupil2 = new Ellipse2D.Double(
				getPositionPupil2().getX(),
				getPositionPupil2().getY(),
				getDiameterPupil(),
				getDiameterPupil() * 1.2
				);
		g2.setPaint(Color.BLACK);
		g2.fill(pupil1);
		g2.fill(pupil2);
	}
	
	protected void drawMouth(Graphics2D g2) {
		if (model.smile) {
			// folgende QuadVurve2D und Anti-Aliasing aus ChatGPT
			// Anti-Aliasing für glattere Linien
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
			// Start-, End- und Kontrollpunkt für die Kurve
			QuadCurve2D mouth = new QuadCurve2D.Double(
					getMiddlePoint().getX() - model.getRadiusHead() * 0.6,
					getMiddlePoint().getY() + model.getRadiusHead() * 0.4,
					getMiddlePoint().getX(),
					getMiddlePoint().getY() + model.getRadiusHead() * 0.9,
					getMiddlePoint().getX() + model.getRadiusHead() * 0.6,
					getMiddlePoint().getY() + model.getRadiusHead() * 0.4
					);
			g2.setPaint(Color.BLACK);
			g2.draw(mouth);
		} else {
			//folgende Drawmethode aus ChatGPT
			// Anti-Aliasing für glattere Linien
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Start-, End- und Kontrollpunkt für die Kurve
	        QuadCurve2D mouth = new QuadCurve2D.Double(
					getMiddlePoint().getX() - model.getRadiusHead() * 0.5,
					getMiddlePoint().getY() + model.getRadiusHead() * 0.7,
					getMiddlePoint().getX(),
					getMiddlePoint().getY() + model.getRadiusHead() * -0.1,
					getMiddlePoint().getX() + model.getRadiusHead() * 0.5,
					getMiddlePoint().getY() + model.getRadiusHead() * 0.7
					);
			g2.setPaint(Color.BLACK);
	        g2.draw(mouth);
		}
	}
	
	protected void drawNose(Graphics2D g2) {
		Rectangle2D nose = new Rectangle2D.Double(
				getPositionNose().getX(),
				getPositionNose().getY(),
				getWidthNose(),
				getLengthNose()
				);
		g2.setPaint(Color.BLACK);
		g2.fill(nose);
	}
	
	public double calculateRadiusEye(){
		model.setRadiusEye(model.getRadiusHead() * 0.25);
		return model.getRadiusHead();
	}
	
	public double calculateDiameterEye(){
		setDiameterEye(model.getRadiusEye() * 2);
		return model.getRadiusEye();
	}
	
	public Point2D.Double calculateMiddlePoint(){
		Point2D.Double position = new Point2D.Double (
				model.getPosition().getX() + model.getRadiusHead(),
				model.getPosition().getY() + model.getRadiusHead()
				);
		setMiddlePoint(position);
		return position;
	}
	
	public double calculateDiameterPupil() {
		setDiameterPupil(getDiameterEye() * 0.5);
		return getDiameterEye();
	}
	
	public double calculateRadiusPupil() {
		model.setRadiusPupil(getDiameterPupil() / 2);
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
				getMiddlePoint().getX() - getDiameterEye() - model.getRadiusHead() * 0.2,
				model.getPosition().getY() + model.getRadiusHead() / 2
				);
		setPositionEye1(position);
		return position;
	}
	
	public Point2D.Double calculatePositionEye2(){
		Point2D.Double position = new Point2D.Double (
				getMiddlePoint().getX() + model.getRadiusHead() * 0.2,
				getPositionEye1().getY()
				);
		setPositionEye2(position);
		return position;
	}
	
	public Point2D.Double calculatePositionPupil1(){
		Point2D.Double position = new Point2D.Double (
				getPositionEye1().getX() + model.getRadiusEye() - model.getRadiusPupil(),
				getPositionEye1().getY() + model.getRadiusEye() * 0.8 - model.getRadiusPupil()
				);
		setPositionPupil1(position);
		return position;
	}
	
	public Point2D.Double calculatePositionPupil2(){
		Point2D.Double position =  new Point2D.Double (
				getPositionEye2().getX() + model.getRadiusEye() - model.getRadiusPupil(),
				getPositionEye2().getY() + model.getRadiusEye() * 0.8 - model.getRadiusPupil()
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
	
	public void changeSize(double factor) {
		model.setRadiusHead(model.getRadiusHead() + model.getRadiusHead() * factor);
	}
	
	public void changeEyes(double factor) {
		model.setRadiusEye(model.getRadiusEye() + model.getRadiusEye() * factor);
		calculateDiameterEye();
		calculateDiameterPupil();
		calculateRadiusPupil();
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
	
	public void setPositionNose(Point2D position) {
		positionNose = position;
	}
	
	public Point2D getMiddlePoint(){
		return middlePoint;
	}
	
	public void setMiddlePoint(Point2D position) {
		middlePoint = position;
	}
}
