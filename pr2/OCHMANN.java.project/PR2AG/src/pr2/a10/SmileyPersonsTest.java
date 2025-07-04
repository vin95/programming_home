package pr2.a10;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SmileyPersonsTest {
	public static void main(String[] args) {
		test01();
		test02();
	}
	
	public static void test01() {
	    List<SmileyPerson> personList = SmileyPersons.createTestSmileyPesons();

	    PrintWriter out = new PrintWriter(System.out);
	    personList.sort(SmileyPerson::compareByName);
	    personList.forEach(out::println);
	    out.println();
	    out.flush();
	}
	
	public static void test02() {
		List<SmileyPerson> personList = SmileyPersons.createTestSmileyPesons();
		Predicate<SmileyPerson> isOld = SmileyPerson::isOld;
		Predicate<SmileyPerson> hasBigEyes = SmileyPerson::hasBigEyes;
		Predicate<SmileyPerson> isYoung = SmileyPerson::isYoung;
		Comparator <SmileyPerson> lastname = SmileyPerson::compareByName;

	    PrintWriter out = new PrintWriter(System.out);
	    personList.removeIf(isYoung);
	    personList.removeIf(isOld.and(hasBigEyes));	    
	    personList.sort(lastname);
	    personList.forEach(out::println);
	    out.println();
	    out.flush();
	}
}
