package pr1.a10;

import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Convert {
	public static Set<Ellipse2D.Double> mapIntsToEllipses(List<Integer> ints){
		Set<Ellipse2D.Double> numbersset = new HashSet<>();
		for (int i = 0; i < ints.size(); i += 2 ) {
			numbersset.add(new Ellipse2D.Double(ints.get(i), ints.get(i+1), 4, 4));
		}
		return numbersset; 
	}
	
	public static Set<Ellipse2D.Double> filtered(Set<Ellipse2D.Double> points, PointFilter filter){
		Set<Ellipse2D.Double> filteredset = new HashSet<>();
		for (Ellipse2D.Double point : points) {
			if (filter.accept(point))
			filteredset.add(point);
		}
		return filteredset;
	}
}
