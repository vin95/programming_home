package pr1.a10;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		createNumbers();
		List<Integer> manyNumbers = getNumbersAsList();
		System.out.print(manyNumbers.size());
	}
	
	public static void createNumbers() {
		DataProvider.writeIntNumberFile("manyNumbers", 75000);
	}
	
	public static List<Integer> getNumbersAsList(){
		List<Integer> manyNumbers = DataProvider.integerListFrom("manyNumbers");
		return manyNumbers;
	}
}
