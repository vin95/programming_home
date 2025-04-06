package pr1.a04.vb;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileReader;

public class VorbereitungInput {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		scannerAusprobieren();
	}
	
	public static void scannerAusprobieren(){
		String intNumbers = "0 1 2 3 4 5 6 7 8 9";
		String doubleNumbers = "0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1.0";
		String mixedNumbers = "1 2 1.3 4 3.6 9.9";
		
		Scanner inInt = new Scanner(intNumbers);
		PrintWriter out = new PrintWriter(System.out);
		
		while(inInt.hasNextInt()) {
			out.print("Zahl: " + inInt.nextInt() + "    ");
		}
		
		inInt.close();
		
		Scanner inDouble = new Scanner(doubleNumbers);
		out.println();
		while(inDouble.hasNextDouble()) {
			out.print("Zahl: " + inDouble.nextDouble() + "    ");
		}
		
		inDouble.close();
		
		Scanner inMixedNumbers = new Scanner(mixedNumbers);
		out.println();
		while(inMixedNumbers.hasNext()) {
			if(inMixedNumbers.hasNextInt()) {
				out.print("Zahl: " + inMixedNumbers.nextInt() + "    ");
			}
			if(inMixedNumbers.hasNextDouble()) {
				out.print("Zahl: " + inMixedNumbers.nextDouble() + "    ");
			}
		}
		
		Scanner in = new Scanner(new FunnyFirstFileReader("./data/testfiles/zahlen01.txt"));
		out.println();
		while(in.hasNext()) {
			if(in.hasNextInt()) {
				out.print("Zahl: " + in.nextInt() + "    ");
			}
			if(in.hasNextDouble()) {
				out.print("Zahl: " + in.nextDouble() + "    ");
			}
		}
		
		in.close();
		out.flush();
	}
}
