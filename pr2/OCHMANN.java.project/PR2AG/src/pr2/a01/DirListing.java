package pr2.a01;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DirListing {
	private static final Comparator<File> DIR_FIRST_COMPARATOR = new DirFirstComparator();
	private static final Comparator<File> NameComparator = new NameComparator();

	public static void printLines(PrintWriter out, List<String> lines) {
		for (String line : lines) {
			out.println(line);
		}
		out.flush();
	}
	
	public static List<String> contentOfDir(String dirPathString, Comparator<File> comparator){
		File mainDirectory = new File(dirPathString);
		List<String> directoryList = new ArrayList<>();
		
		return contentOfDir(directoryList, mainDirectory, 0, comparator);
	}
	
	public static List<String> contentOfDir(List<String> list, File dir, int level, Comparator<File> comparator){
		List<File> files = new ArrayList<File>(List.of(dir.listFiles()));
		files.sort(comparator);
		for(File file : files) {
			if(file.isFile()) {
				list.add(" ".repeat(level) + file.getName());
			}
			if(file.isDirectory() && !file.isHidden()) {
				list.add(" ".repeat(level) + file.getName());
				contentOfDir(list, file, 3 + level, comparator);
			}
		}		
		return list;
	}
	
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		String path = "test";
		printLines(out, contentOfDir(path, NameComparator));
	}
}
