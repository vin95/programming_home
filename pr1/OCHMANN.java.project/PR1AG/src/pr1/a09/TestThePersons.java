package pr1.a09;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TestThePersons {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		testDifferentTypes(out);
		testRechnungen(out);
		out.flush();
	}
	
	public static void testDifferentTypes(PrintWriter out) {
		List<Person> personData = PersonFactory.createTestPersons();
		List<Person> persons = new ArrayList<Person>();
		for (int person = 0; person < 3; person ++) {
			persons.add(personData.get(person));
		}
		List<Student> students = new ArrayList<Student>();
		for (int person = 3; person < 6; person ++) {
			students.add(new Student(personData.get(person), "Informatik", 00001 + person));
		}
		List<Handwerker> handwerker = new ArrayList<Handwerker>();
		for (int person = 6; person < personData.size(); person ++) {
			handwerker.add(new Handwerker(personData.get(person), "Maurer", -40 + person * 10));
		}
		
		List<Person> allPersons = new ArrayList<Person>();
		allPersons.addAll(persons);
		allPersons.addAll(students);
		allPersons.addAll(handwerker);
		
		printAll(allPersons, out);
		out.println();
		for(Student student : students) {
			out.println(student);
		}
		out.println();
		students.get(0).studienfachWechseln("Biologie");
		students.get(1).studienfachWechseln("Physik");
		for(Student student : students) {
			out.println(student);
		}
		out.println();
	}
	
	public static void printAll(List<Person> allPersons, PrintWriter out) {
		for (int person = 0; person < allPersons.size(); person++) {
			out.println(allPersons.get(person).toString());
		}
	}
	
	public static void testRechnungen(PrintWriter out) {

		List<Handwerker> handwerker = HandwerkerFactory.createTestHandwerker();
		double sum = 0;
		List<Integer> arbeitszeiten = new ArrayList<Integer>(List.of(7, 11, 17));
		for (int person = 0; person < 3; person++) {
			sum += handwerker.get(person).rechnung(arbeitszeiten.get(person));
		}
		out.println("Die Gesamtrechnung für 3 Handwerker beträgt: " + sum);
	}
}
