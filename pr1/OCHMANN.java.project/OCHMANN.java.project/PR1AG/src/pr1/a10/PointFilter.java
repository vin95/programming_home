package pr1.a10;

import java.awt.geom.Ellipse2D;

public interface PointFilter {
	public abstract boolean accept(Ellipse2D.Double e);
}
