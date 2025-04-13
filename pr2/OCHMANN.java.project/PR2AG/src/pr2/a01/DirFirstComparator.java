package pr2.a01;

import java.io.File;
import java.util.Comparator;

public class DirFirstComparator implements Comparator<File>{

	@Override
	public int compare(File f1, File f2) {
		if((f1.isFile() && f2.isFile()) || (f1.isDirectory() && f2.isDirectory())) {
			return 0;
		} else if(f1.isFile()) {
			return 1;
		} else {
			return -1;
		}
	}
}