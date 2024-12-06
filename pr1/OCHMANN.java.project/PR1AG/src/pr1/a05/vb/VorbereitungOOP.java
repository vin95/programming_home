package pr1.a05.vb;

import java.io.PrintWriter;
import java.util.Scanner;

import schimkat.berlin.lernhilfe2024ws.objectPlay.Adresse;
import schimkat.berlin.lernhilfe2024ws.objectPlay.AdresseList;
import schimkat.berlin.lernhilfe2024ws.objectPlay.Factory;
import schimkat.berlin.lernhilfe2024ws.objectPlay.Person;
import schimkat.berlin.lernhilfe2024ws.objectPlay.PersonList;

public class VorbereitungOOP {
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		test(out);
		out.flush();
	}
	
	public static void test(PrintWriter out) {
		PersonList list = Factory.createTestPersonliste();
		PersonList myPersonList = new PersonList();
		
		for (int i=0; i<5; i++) {
			Person a = list.get(i);
			out.println(a);
			myPersonList.add(a);
		}
		
		out.println();
		
		Adresse adresse1 = new Adresse(12163, "Berlin", "Berliner Strasse", 59);
		Adresse adresse2 = new Adresse(12163, "Berlin", "Talweg", 7);
		Adresse adresse3 = new Adresse(12163, "Berlin", "Hansaplatz", 13);
		Adresse adresse4 = new Adresse(adresse1, 60);
		Adresse adresse5 = new Adresse(adresse2, 1);

		AdresseList myAdresseList = AdresseList.of(adresse5, adresse1, adresse2, adresse3, adresse4, adresse5);
		
		out.println(adresse1);
		out.println(adresse2);
		out.println(adresse3);
		out.println(adresse4);
		out.println(adresse5);
		
		out.println();
		
		out.println("Ausgabe per Schleife:");
		
		for(Person person : myPersonList) {
			out.println(person);
		}
		
		out.println();
		
		for(Adresse adresse : myAdresseList) {
			out.println(adresse);
		}
		
	}
}
