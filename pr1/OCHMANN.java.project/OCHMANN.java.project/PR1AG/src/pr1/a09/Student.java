package pr1.a09;

public class Student extends Person{
	protected String studienfach;
	protected int matrikelNr;
	
	public Student(String vorname, String nachname, int geburtsjahr, String studienfach, Integer matrikelNr) {
		super(vorname, nachname, geburtsjahr);
		this.studienfach = studienfach;
		this.matrikelNr = matrikelNr;
	}
	
	public Student(Person person, String studienfach, Integer matrikelNr){
		this(person.vorname, person.nachname, person.geburtsjahr, studienfach, matrikelNr);
	}
	
	public String getStudienfach() {
		return studienfach;
	}
	
	public int getMatrikelNr() {
		return matrikelNr;
	}
	
	public void studienfachWechseln(String neuesFach) {
		studienfach = neuesFach;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %d" , vorname, nachname, studienfach, matrikelNr);
	}
	
	public String toStringReadable() {
		return String.format("%s %s, %s, %d" , vorname, nachname, studienfach, matrikelNr);
	}
}
