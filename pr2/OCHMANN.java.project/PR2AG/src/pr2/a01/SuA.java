package pr2.a01;

public class SuA {
	public static String gespeert(String normal) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < normal.length(); i++) {
			sb.append(' ');
			sb.append(normal.charAt(i));
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}
	
//	public static String[] createTestStrings(int Anzahl){
//		String[] strings = {"Hallo", "Guten", "Guten", "Guten", "Guten", "Guten", "Guten", "Guten", "Guten", "Guten"};
//	}
}
