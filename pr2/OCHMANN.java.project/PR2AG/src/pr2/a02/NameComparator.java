package pr2.a02;

import java.io.File;
import java.util.Comparator;

public abstract class NameComparator implements Comparator<File>{

	@Override
	public int compare(File f1, File f2) {
		return f1.getName().compareToIgnoreCase(f2.getName());
	}
}
