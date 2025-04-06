package pr1.a09;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
	public static ArrayList<Person> createTestPersons(){
		Person person1 = new Person("Timo", "Schönke", 1990);
		Person person2 = new Person("Tim", "Walz", 1991);
		Person person3 = new Person("Hans", "Stein", 1992);
		Person person4 = new Person("Micha", "Daebel", 1993);
		Person person5 = new Person("Andi", "Schramm", 1994);
		Person person6 = new Person("Kilian", "Feher", 1995);
		Person person7 = new Person("Boris", "Batora", 1996);
		Person person8 = new Person("Justus", "Basnet", 1997);
		Person person9 = new Person("Markus", "Achterberg", 1998);
		Person person10 = new Person("Tobias", "Benz", 1999);
		ArrayList<Person> personList = new ArrayList<>(List.of(
				person1,
				person2,
				person3,
				person4,
				person5,
				person6,
				person7,
				person8,
				person9,
				person10
				));
		
		return personList;
	}
}
