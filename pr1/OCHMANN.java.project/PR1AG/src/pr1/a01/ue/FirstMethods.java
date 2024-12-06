package pr1.a01.ue;

import java.io.PrintWriter;
import java.lang.Math;

public class FirstMethods {
	public static void main (String[] args) {
		
		PrintWriter out = new PrintWriter(System.out);
		
		printDecorated(out, "Hallo ich bin ein Text");
		out.flush();
		
		printValue(out, value(1));
		out.flush();
		
		printFlaecheSiebeneck (out, 7);
		out.flush();
	}

	public static void printDecorated(PrintWriter out, String text) {
		
		out.println("**********************");
		out.println(text);
		out.println("**********************");
	}
	
	public static int value(int a) {
		int value;
		value = (a + 1 + 5 + 7) * 9 - a;		
		return value;
	}
	
	public static void printValue(PrintWriter out, int a) {
		out.println(a);
	}
	
	public static double flaecheSiebeneck(double a) {
		// Quelle: https://www.mathespass.at/formeln/regelm%C3%A4%C3%9Figes-siebeneck-formeln-und-eigenschaften
		
		double gesamtflaeche;
		gesamtflaeche = 7.0/4.0 * a*a / Math.tan(Math.PI/7.0);
		return gesamtflaeche;
	}
	
	public static void printFlaecheSiebeneck (PrintWriter out, double a) {
		double gesamtFlaeche;
		gesamtFlaeche = flaecheSiebeneck(a);
		out.println(gesamtFlaeche);
	}
}
