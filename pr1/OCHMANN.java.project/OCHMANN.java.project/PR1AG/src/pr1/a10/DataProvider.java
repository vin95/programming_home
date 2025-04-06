package pr1.a10;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileReader;
import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileWriter;

public class DataProvider {
	public static void writeIntNumberFile(String filename, int numberCount) {
		PrintWriter out = new PrintWriter(new FunnyFirstFileWriter("./data/" + filename));
		
		for(int i = 0; i < numberCount; i ++) {
			if (i %10 == 0 && i > 0) out.println();
			out.printf("%10.0f", 1000*Math.random());
		}
		out.close();
	}
	
	public static List<Integer> integerListFrom(String filename){
		List<Integer> numberList = new ArrayList<Integer>();
		Scanner in = new Scanner(new FunnyFirstFileReader("./data/" + filename));
		while (in.hasNextInt()) {
			numberList.add(in.nextInt());
		}
		in.close();
		return numberList;
	}
}
