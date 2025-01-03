package pr1.a09;

public class Handwerker extends Person{
	protected String gewerk;
	protected double stundenlohn;
	
	public Handwerker(String vorname, String nachname, int geburtsjahr, String gewerk, double stundenlohn) {
		super(vorname, nachname, geburtsjahr);
		this.gewerk = gewerk;
		this.stundenlohn = stundenlohn;
	}
	
	public Handwerker(Person person, String gewerk, double stundenlohn) {
		this(person.vorname, person.nachname, person.geburtsjahr, gewerk, stundenlohn);
	}
	
	public String getGewerk() {
		return gewerk;
	}
	
	public double getStundenlohn() {
		return stundenlohn;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %4.2f" , vorname, nachname, gewerk, stundenlohn);
	}
	
	public String toStringReadable() {
		return String.format("%s %s, %s, %4.2f" , vorname, nachname, gewerk, stundenlohn);
	}
	
	public double rechnung(double arbeitszeit) {
		return stundenlohn * arbeitszeit + 72.00;
	}
}
