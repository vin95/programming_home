package pr2.a01;

import java.io.PrintWriter;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		PrintWriter out = new PrintWriter(System.out);
		out.println(SuA.gesperrt("hallo"));
		out.println();
		out.flush();
		
		PrintWriter out2 = new PrintWriter(System.out);
		out2.println(Arrays.toString(SuA.createTestStrings(-2)));
		out2.println(Arrays.toString(SuA.createTestStrings(6)));
		out2.println(Arrays.toString(SuA.createTestStrings(12)));
		out2.println();
		out2.flush();
		
		PrintWriter out3 = new PrintWriter(System.out);
		SuA.printGesperrt(out3, SuA.createTestStrings(3));
		

		PrintWriter out4 = new PrintWriter(System.out);
		int[] intArray = SuA.createTestwerte(10, 55, 77);
		out4.println("Original: " + Arrays.toString(intArray));
		Arrays.sort(intArray);
		out4.println("Sortiert: " + Arrays.toString(intArray));
		out4.println("Min/Max: " + Arrays.toString(SuA.minMax(intArray)));
		out4.println();
		out4.flush();
		

		PrintWriter out5 = new PrintWriter(System.out);
		int[][] intArray2dim= SuA.createTestwerte2dim();
		for (int[] row : intArray2dim) {
			out5.print("[");
	        for (int value : row) {
	            out5.printf("%3d", value);
	        }
	        out5.println("]");
	    }
		out5.println();
		out5.flush();
		
		PrintWriter out6 = new PrintWriter(System.out);
		int[] intArray2 = SuA.createTestwerte(10, 33, 77);
		out6.println("Min/Max: " + Arrays.toString(SuA.minMax(intArray2)));
		int[] intArray3 = SuA.createTestwerte(2000, 44, 77);
		out6.println("Min/Max: " + Arrays.toString(SuA.minMax(intArray3)));
		int[] intArray4 = SuA.createTestwerte(10000, 55, 77);
		out6.println("Min/Max: " + Arrays.toString(SuA.minMax(intArray4)));
		out6.flush();
	}
}
