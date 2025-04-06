package pr1.a10;

import java.awt.geom.Ellipse2D.Double;

public class CircleFilter implements PointFilter{
	protected static final double MIDDLEPOINT_X = 300;
	protected static final double MIDDLEPOINT_Y = 400;
	
	@Override
	public boolean accept(Double e) {
		if (deltaPointToMiddle(e.getCenterX(), e.getCenterY()) <= 200) {
			return true;
		}
		return false;
	}
	
	public static double deltaPointToMiddle(double x, double y) {
		double b = x - MIDDLEPOINT_X;
		double c = y - MIDDLEPOINT_Y;
		
		return Math.sqrt(b * b + c * c);
	}
}
