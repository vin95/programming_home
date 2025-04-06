package pr2.a01;

import java.io.PrintWriter;

public class SuA {
	public static String gesperrt(String normal) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < normal.length(); i++) {
			sb.append(' ');
			sb.append(normal.charAt(i));
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}
	
	public static String[] createTestStrings(int Anzahl){
		if (Anzahl <= 0) {
			return new String[0];
		}
		String[] strings = {"Hallo", "guten", "Morgen", "Wie", "geht", "es", "dir", "am", "heutigen", "Tag?"};
		if (Anzahl > 9) {
			return strings;
		}
		String[] resultarray = new String[Anzahl];
		for (int i = 0; i < Anzahl; i++) {
			resultarray[i] = strings[i];
		}
		return resultarray;
	}
	
	public static void printGesperrt(PrintWriter out, String[] values) {
		for (int i = 0; i < values.length; i++) {
			out.println(gesperrt(values[i]));
		}
		out.println();
		out.flush();
	}
	
	public static int[] minMax(int[] values) {
		if (values == null || values.length == 0) {
			int[] intArray = {Integer.MIN_VALUE, Integer.MAX_VALUE};
			return intArray;
		}
		int[] intArray = new int[2];
		intArray[0] = values[0];
		intArray[1] = values[0];
		if(values.length == 1) {
			return intArray;
		}
		for (int i = 1; i < values.length; i++) {
			if (values[i] < intArray[0]) {
				intArray[0] = values[i];
			}
			if (values[i] > intArray[1]) {
				intArray[1] = values[i];
			}
		}
		return intArray;
	}
	
	public static int[] minMax(int[][] values) {
		if (values == null || values.length == 0) {
			int[] intArray = {Integer.MIN_VALUE, Integer.MAX_VALUE};
			return intArray;
		}
		int[] intArray = new int[2];
		intArray[0] = values[0][0];
		intArray[1] = values[0][0];
		if(values.length == 1 && values[0].length == 1) {
			return intArray;
		}
		for (int i = 1; i < values.length; i++) {
			for( int j = 0; j < values[i].length; j++) {
				if (values[i][j] < intArray[0]) {
					intArray[0] = values[i][j];
				}
				if (values[i][j] > intArray[1]) {
					intArray[1] = values[i][j];
				}
			}	
		}
		return intArray;
	}
	
	public static int[] createTestwerte(int count, int min, int max) {
		int[] intArray = new int[count];
		for (int i = 0; i < count; i++) {
			intArray[i] = (int) (Math.random() * (max - min + 1)) + min;
		}
		return intArray;
	}
	
	public static int[][] createTestwerte2dim(){
		int[][] intArray = {
				{20,  8, 33,  9, 11, 36,  1, 20,  8, 33,  9, 11, 36,  1, 10},
				{21,  9, 34, 19,  1, 23,  2, 21,  9, 34, 19,  1, 23,  2, 20},
				{22, 10, 35, 29, 11, 78,  3, 22, 10, 35, 29, 11, 78,  3, 30},
				{23, 11, 36, 39,  1, 39,  4, 23, 11, 36, 39,  1, 39,  4, 40},
				{24, 12, 37, 49, 11, 15,  5, 24, 12, 37, 49, 11, 15,  5, 50},
				{25, 13, 38, 59,  1, 37,  6, 25, 13, 38, 59,  1, 37,  6, 60},
				{26, 14, 39, 69, 11, 23,  7, 26, 14, 39, 69, 11, 23,  7, 70},
				{27, 15, 30, 79,  1, 58,  8, 27, 15, 30, 79,  1, 58,  8, 80},
				{28, 16, 31, 89, 11, 51,  9, 28, 16, 31, 89, 11, 51,  9, 90},
				{29, 17, 32, 99,  1, 63,  1, 29, 17, 32, 99,  1, 63,  1, 99},
				{30, 18, 43, 19, 11, 83,  2, 30, 18, 43, 19, 11, 83,  2,  1},
				{31, 19, 34, 29,  1, 22,  3, 31, 19, 34, 29,  1, 22,  3, 11},
				{32, 20, 35, 39, 11,  4,  4, 32, 20, 35, 39, 11,  4,  4, 12},
				{33, 21, 36, 49,  1, 45,  5, 33, 21, 36, 49,  1, 45,  5, 13},
				{34, 22, 37, 59, 11, 83,  6, 34, 22, 37, 59, 11, 83,  6, 14}
		};
		return intArray;
	}
}
