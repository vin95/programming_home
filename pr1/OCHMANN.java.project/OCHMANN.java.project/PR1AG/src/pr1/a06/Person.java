package pr1.a06;

public class Person {
	protected String vorname;
	protected String nachname;
	protected int geburtsjahr;
	
	public Person(String vorname, String nachname, int geburtsjahr ) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsjahr = geburtsjahr;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public int getGeburtsjahr() {
		return geburtsjahr;
	}
	
	public String toString() {
		String personAsString = String.format("%s %s %d" , vorname, nachname, geburtsjahr);
		return personAsString;
	}
}
