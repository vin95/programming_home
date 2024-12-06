package pr1.a03;

import java.io.PrintWriter;

public class SomeFormats {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		out.print(prettyInt(2 +3*4, 5));
		out.println();
		out.print(prettyNumber(40.0/3.0, 8, 3));
		out.println();
		printPretty(out, 70.0/8.0, 9, 4);
		out.flush();		
	}
	
	public static String prettyInt(int n, int width) {
		String prettyInt = String.format("%" + width + "d", n);
		return prettyInt; 
	}
	
	public static String prettyNumber(double n, int width, int precision) {
		String prettyNumber = String.format("%" + width + "." + precision + "f", n);
		return prettyNumber;
	}
	
	public static void printPretty(PrintWriter out, double n, int width, int precision) {
		out.printf("%" + width + "." + precision + "f", n);
	}
}
