package pr2.a10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmileyPersons {
	protected List<SmileyPerson> personList;
	
	public SmileyPersons() {
		
	}
	
	public void readFrom(String filename) {
		Scanner in = new Scanner(filename);
		while(in.hasNextLine()) {
			personList.add(new SmileyPerson(SmileyPerson.readFrom(in.nextLine())));
		}
	}
	
	public void writeTo(String filename) {
		
	}
	
	public List<SmileyPerson> getPersonListCopy(){
		List<SmileyPerson> personListCopy = new ArrayList<>(personList);
		return personListCopy;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SmileyPerson person : personList) {
			sb.append(person.toString()).append(System.lineSeparator());
		}
		return sb.toString();
	}
}
