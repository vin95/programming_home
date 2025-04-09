package pr2.a02;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DirListing {
	public static void printLines(PrintWriter out, List<String> lines) {
		for (String line : lines) {
			out.println(line);
		}
		out.flush();
	}
	
	public static List<String> contentOfDir(String dirPathString, Comparator<File> comparator){
		List<String> contentList = new ArrayList<>();
		for(file in dirPathString) {
			for (String line : contentList) {
				if(comparator())
					contentList.add(file in dirPathString);
			}
		}
	}
}
