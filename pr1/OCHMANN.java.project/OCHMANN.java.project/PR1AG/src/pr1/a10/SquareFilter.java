package pr1.a10;

import java.awt.geom.Ellipse2D.Double;

public class SquareFilter implements PointFilter{
	protected static final double MIDDLEPOINT_X = 400;
	protected static final double MIDDLEPOINT_Y = 500;
	
	@Override
	public boolean accept(Double e) {
		if (
			deltaToMiddleKoordinate(e.getCenterX(), MIDDLEPOINT_X) <= 150 &&
			deltaToMiddleKoordinate(e.getCenterY(), MIDDLEPOINT_Y) <= 150
			) {
			return true;
		}
		return false;
	}
	
	public static double deltaToMiddleKoordinate(double a, double middleKoordinate) {
		if (a >= middleKoordinate) {
			return a - middleKoordinate;
		}
		return middleKoordinate - a;
	}
}
