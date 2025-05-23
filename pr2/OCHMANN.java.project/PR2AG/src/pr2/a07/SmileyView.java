package pr2.a07;

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
	protected Point2D position = new Point2D.Double(100, 50);
	protected SmileyModel model = new SmileyModel(position, 100, false);
	
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
		if (model.smile) {
			g2.setPaint(Color.GREEN);
		} else {
			g2.setPaint(Color.RED);
		}
		g2.fill(head);
	}
	
	protected void drawEyes(Graphics2D g2) {
		Ellipse2D auge1 = new Ellipse2D.Double(
				model.getPositionEye1().getX(),
				model.getPositionEye1().getY(),
				model.getDiameterEye(),
				model.getDiameterEye() * 1.2
				);
		Ellipse2D auge2 = new Ellipse2D.Double(
				model.getPositionEye2().getX(),
				model.getPositionEye2().getY(),
				model.getDiameterEye(),
				model.getDiameterEye() * 1.2
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
				model.getPositionPupil1().getX(),
				model.getPositionPupil1().getY(),
				model.getDiameterPupil(),
				model.getDiameterPupil() * 1.2
				);
		Ellipse2D pupil2 = new Ellipse2D.Double(
				model.getPositionPupil2().getX(),
				model.getPositionPupil2().getY(),
				model.getDiameterPupil(),
				model.getDiameterPupil() * 1.2
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
					model.getMiddlePoint().getX() - model.getRadiusHead() * 0.6,
					model.getMiddlePoint().getY() + model.getRadiusHead() * 0.4,
					model.getMiddlePoint().getX(),
					model.getMiddlePoint().getY() + model.getRadiusHead() * 0.9,
					model.getMiddlePoint().getX() + model.getRadiusHead() * 0.6,
					model.getMiddlePoint().getY() + model.getRadiusHead() * 0.4
					);
			g2.setPaint(Color.BLACK);
			g2.draw(mouth);
		} else {
			//folgende Drawmethode aus ChatGPT
			// Anti-Aliasing für glattere Linien
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Start-, End- und Kontrollpunkt für die Kurve
	        QuadCurve2D mouth = new QuadCurve2D.Double(
					model.getMiddlePoint().getX() - model.getRadiusHead() * 0.5,
					model.getMiddlePoint().getY() + model.getRadiusHead() * 0.7,
					model.getMiddlePoint().getX(),
					model.getMiddlePoint().getY() + model.getRadiusHead() * -0.1,
					model.getMiddlePoint().getX() + model.getRadiusHead() * 0.5,
					model.getMiddlePoint().getY() + model.getRadiusHead() * 0.7
					);
			g2.setPaint(Color.BLACK);
	        g2.draw(mouth);
		}
	}
	
	protected void drawNose(Graphics2D g2) {
		Rectangle2D nose = new Rectangle2D.Double(
				model.getPositionNose().getX(),
				model.getPositionNose().getY(),
				model.getWidthNose(),
				model.getLengthNose()
				);
		g2.setPaint(Color.BLACK);
		g2.fill(nose);
	}
}
