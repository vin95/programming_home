package pr2.a01;

import java.io.PrintWriter;

public class TestClasses {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		out.println(SuA.gespeert("hallo"));
		out.flush();
	}
}
