package pr2.a04;

public class IllegalDateException extends Exception{
	private static final long serialVersionUID = 1L;
	Date date;
	String message = getMessage();
	
	public IllegalDateException(Date date, String message) {
		super(message);
		this.date = date;
	}
	
	public String toString() {
		return (message + " " + date.toString());
	}
}
