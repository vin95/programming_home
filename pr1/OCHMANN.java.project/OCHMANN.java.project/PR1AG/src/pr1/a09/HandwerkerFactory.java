package pr1.a09;

import java.util.ArrayList;
import java.util.List;

public class HandwerkerFactory {
	public static List<Handwerker> createTestHandwerker(){
		Handwerker handwerker1 = new Handwerker("Timo", "Schönke", 1990, "Maurer", 10.0);
		Handwerker handwerker2 = new Handwerker("Tim", "Walz", 1991, "Architekt", 20.0);
		Handwerker handwerker3 = new Handwerker("Hans", "Stein", 1992, "Betonmischer", 25.0);
		Handwerker handwerker4 = new Handwerker("Micha", "Daebel", 1993, "Tischler", 20.0);
		Handwerker handwerker5 = new Handwerker("Andi", "Schramm", 1994, "Bildhauer", 15.0);
		List<Handwerker> handwerkerList = new ArrayList<>(List.of(
				handwerker1,
				handwerker2,
				handwerker3,
				handwerker4,
				handwerker5
				));
		
		return handwerkerList;
	}
}
