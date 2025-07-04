package pr2.a10;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class SmileyPersons {
	protected List<SmileyPerson> personList;
	final static String PATH_TO_FILE = "personenData.txt";
	public SmileyPersons() {
		this.personList = new ArrayList<SmileyPerson>();
	}
	
	public void readFrom(String filename) {
		try {
			readFromImpl(filename);
		} catch (Exception e){
			System.out.println(filename);
			System.err.println("FEHLER - readFrom");
		}
	}
	
	private void readFromImpl(String filename) throws Exception{
		Scanner in = new Scanner(new File(filename));
		while(in.hasNextLine()) {
			personList.add(new SmileyPerson(SmileyPerson.readFrom(in.nextLine())));
		}
		in.close();
	}
	
	public void writeTo(String filename) {
		try {
			writeToImpl(filename);
		} catch (Exception e) {
			System.err.println("FEHLER - writeTo");			
		}
	}
	
	private void writeToImpl(String filename) throws Exception{
		PrintWriter fout = new PrintWriter(new FileWriter(filename));
		fout.println(toString());
		fout.close();
	}
	
	public List<SmileyPerson> getPersonListCopy(){
		try {
			return getPersonListCopyImpl();
		} catch (Exception e) {
			System.err.println("FEHLER - getPersonListCopy");			
			return new ArrayList<>(); // oder return null nach catchblock?
		}
	}
	
	private List<SmileyPerson> getPersonListCopyImpl() throws Exception{
		List<SmileyPerson> personListCopy = new ArrayList<>(personList);
		return personListCopy;
	}
	
	public String toString() {
		try {
			return toStringImpl();
		} catch (Exception e) {
			System.err.println("FEHLER - toString");
			return "";
		}
	}
	
	private String toStringImpl() throws Exception{
		StringBuilder sb = new StringBuilder();
		for (SmileyPerson person : personList) {
			sb.append(person.toString()).append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public static List<SmileyPerson> createTestSmileyPesons(){
		SmileyPersons personen = new SmileyPersons();
		personen.readFrom(PATH_TO_FILE);
		return personen.personList;
	}
	
	public static void testStreams() {
		Predicate<SmileyPerson> isYoung = SmileyPerson::isYoung;
		List<SmileyPerson> personList = createTestSmileyPesons();
		//h1
		personList.stream()
			.map(SmileyPerson::getLastname)
//			.sorted(SmileyPerson::compareByName)
			.sorted(String::compareTo)
			.forEach(System.out::println);
		//h2
		personList.stream().sorted(SmileyPerson::compareByName).filter(isYoung).forEach(System.out::println);
	}
	
	public static void main (String[] args) {
		testStreams();
	}
}
