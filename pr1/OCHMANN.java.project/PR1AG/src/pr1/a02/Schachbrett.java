package pr1.a02;

import java.io.PrintWriter;

public class Schachbrett {
	public static void main (String[] args) {
		printSchachbrett();
		System.out.println();
		printSchachbrettReverse();
	}
	
	public static String schachbrett() {
		StringBuilder sb = new StringBuilder();
		for (int i = 8; i > 0; i--) {
			for (char c ='A'; c < 'I'; c++) {
				sb.append(c);
				sb.append(i);
				if(c < 'H') {
					sb.append(' ');
				}
				if(c == 'H') {
					sb.append("\n");
				}
			}
		}
		String resultString = sb.toString();
		return resultString;
	}
		
	public static String schachbrettReverse() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 9; i++) {
			for (char c ='H'; c >= 'A'; c--) {
				sb.append(c);
				sb.append(i);
				if(c > 'A') {
					sb.append(' ');
				}
				if(c == 'A') {
					sb.append("\n");
				}
			}
		}
		String resultString = sb.toString();
		return resultString;
	}
	
	public static void printSchachbrett() {
		PrintWriter out = new PrintWriter(System.out);
		out.print(schachbrett());
		out.flush();
	}
	
	public static void printSchachbrettReverse() {
		PrintWriter out = new PrintWriter(System.out);
		out.print(schachbrettReverse());
		out.flush();
	}
}