package pr1.a06;

import java.io.PrintWriter;
import java.util.ArrayList;

import schimkat.berlin.lernhilfe2024ws.io.FunnyFirstFileWriter;

public class PersonTest {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		test(out);
		PrintWriter out2 = new PrintWriter(System.out);
		printFactoryPersons(out2);
	}
	
	public static void printPersons(PrintWriter out, ArrayList<Person> persons) {
		for(int i = 0; i < persons.size(); i++) {
			out.println(persons.get(i).toString());
		}
		out.flush();
	}
	
	public static void test(PrintWriter out) {
		ArrayList<Person> personList = PersonFactory.createTestPersons();
		
		for (int i = 0; i < 3; i++) {
			personList.remove(personList.size() - i - 1);
		}
		for (int i = 0; i < personList.size(); i++) {
			out.println(personList.get(i) + " " + personList.get(i).toString());
		}
		out.flush();
		printPersons(out, personList);
		
		PrintWriter fout = new PrintWriter(new FunnyFirstFileWriter("./data/factory_persons.txt"));
		
		printPersons(fout, personList);
		fout.close();
	}
	
	public static void printFactoryPersons(PrintWriter out) {
		ArrayList<Person> personList = PersonFactory.createTestPersons();
		
		for (int i = 0; i < personList.size(); i++) {
			out.println(personList.get(i).toString());
		}
		out.flush();
	}
}
