package pr1.a03;

import java.io.PrintWriter;
import java.util.Locale;

import pr1.a02.Schachbrett;
import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileWriter;

public class TestSchachbrett {
	public static void main (String[] args) {
		Locale.setDefault(Locale.US);
		printSchachbrett("./test/schachbrett.txt" , true);
		printSchachbrett("./test/schachbrett2.txt" , false);
	}
	
	public static void printSchachbrett(String filename, boolean isReverse) {
		PrintWriter fout = new PrintWriter(new FunnyFirstFileWriter(filename));
		
		if (isReverse == true) {
			fout.println(Schachbrett.schachbrettReverse());
		} else {
			fout.println(Schachbrett.schachbrett());
		}
		fout.close();
	}
}
