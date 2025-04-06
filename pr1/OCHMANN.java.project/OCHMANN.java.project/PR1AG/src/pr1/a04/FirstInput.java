package pr1.a04;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileReader;
import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileWriter;

public class FirstInput {
	public static void main (String[] args) {
		Locale.setDefault(Locale.US);
		PrintWriter out = new PrintWriter(System.out);
		
		String stringDoubles = ("0.300 0.200 glggkg 1.60");
		String stringFilename = ("./data/testfiles/zahlen02.txt");
		
		Scanner in = new Scanner(stringDoubles);
		out.println(sumOfNumbersIn(in));
		out.println(sumOfNumbersIn(stringFilename));
		in.close();
		
		Scanner in2 = new Scanner(new FunnyFirstFileReader("./data/testfiles/zahlen03.txt"));
		int width = 8;
		int precision = 1;
		prettyPrintNumbersFrom(in2, width, precision, out);
		
		Scanner in3 = new Scanner(new FunnyFirstFileReader("./test/intNumberFile.txt"));
		out.println();
		out.println();
		prettyPrintNumbersFrom(in3, width, precision, out);
		
		Scanner in4 = new Scanner(new FunnyFirstFileReader("./test/mixedNumberFile.txt"));
		out.println();
		out.println();
		prettyPrintNumbersFrom(in4, width, precision, out);
		
		//Aufgabe Datei kopieren
		int widthCopy = 7;
		int precisionCopy = 2;
		copyNumberFile("./test/mixedNumberFile.txt", "./test/mixedNumberFileCopy.txt", widthCopy, precisionCopy);
		
		out.flush();
	}
	
	public static double sumOfNumbersIn(Scanner in) {
		double sum = 0.0;
		while(in.hasNext()) {
			if(in.hasNextDouble()) {
				sum += in.nextDouble();
				continue;
			}
			if(in.hasNext()) {
				in.next();
			}
		}
		return sum;
	}
	
	public static double sumOfNumbersIn(String filename) {
		double sum;
		Scanner in = new Scanner(new FunnyFirstFileReader(filename));
		sum = sumOfNumbersIn(in);
		in.close();
		return sum;
	}
	
	public static void prettyPrintNumbersFrom(Scanner in, int width, int precision, PrintWriter out) {
		int i = 0;
		while (in.hasNext()) {
			if (i %10 == 0 && i > 0) out.println();
			if(in.hasNextInt()) {
				out.printf("%-" + width + "d", in.nextInt());
				i++;
				continue;
			}
			if(in.hasNextDouble()) {
				out.printf("%-" + width + "." + precision + "f", in.nextDouble());
				i++;
				continue;
			}
			in.next();
			i ++;
		}
	}
	
	public static void copyNumberFile(String filenameInput, String filenameOutput, int width, int precision) {
		Scanner in = new Scanner(new FunnyFirstFileReader(filenameInput));
		PrintWriter out = new PrintWriter(new FunnyFirstFileWriter(filenameOutput));
		prettyPrintNumbersFrom(in, width, precision, out);
	}
}
