package pr1.a02;

import java.io.PrintWriter;

public class SomeFunctions {
	public static void main (String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		out.print("Der Kreisumfang betr\u00E4gt:");
		out.println(" " + kreisUmfang(3.5));
		out.print("Der Umfang des Rechtecks betr\u00E4gt: ");
		out.println(rechteckUmfang(3, 4));
		out.print("Die Rechteckfläche betr\u00E4gt: ");
		out.println(rechteckFlaeche(3, 4));
		out.flush();
	}
	
	public static double kreisUmfang (double radius) {
		
		double kreisUmfang = 2 * Math.PI * radius;
		return kreisUmfang;
	}
	
	public static double rechteckUmfang (double a, double b) {
		
		double rechteckUmfang = 2*a + 2*b;
		return rechteckUmfang;
	}
	
	public static double rechteckFlaeche (double a, double b) {
		
		double rechteckFlaeche = a*b;
		return rechteckFlaeche;
	}
}