package pr1.a03;

import java.io.PrintWriter;
import java.util.Locale;

import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileWriter;

public class Aufgabe0310 {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		writeIntNumberFile("./test/intNumberFile.txt", 1000);
		writeMixedNumberFile("./test/mixedNumberFile.txt", 1000, 10);
	}
	
	public static void writeIntNumberFile(String filename, int numberCount) {
		if (numberCount %2 == 0) numberCount ++;
		PrintWriter out = new PrintWriter(new FunnyFirstFileWriter(filename));
		
		for(int i = 0; i < numberCount +1; i ++) {
			if (i %10 == 0 && i > 0) out.println();
			out.printf("%10.0f", 1000*Math.random());
		}
		out.close();
	}
	
	public static void writeMixedNumberFile(String filename, int numberCount, int width) {
		if (numberCount %2 == 0) numberCount ++;
		PrintWriter out = new PrintWriter(new FunnyFirstFileWriter(filename));
		
		for(int i = 0; i < numberCount +1; i ++) {
			if (i %10 == 0 && i > 0) out.println();
			if (Math.random() < 0.5) {
				out.printf("%10.0f", 1000*Math.random());
			} else {
				out.printf("%" + width + ".1f", 1000*Math.random());
			}
		}
		out.close();
	}
}
