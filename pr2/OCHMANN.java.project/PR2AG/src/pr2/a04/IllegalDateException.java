package pr2.a04;

public class IllegalDateException extends Exception{
	private static final long serialVersionUID = 1L;
	protected Date date;
	
	public IllegalDateException(Date date, String message) {
		super(message);
		this.date = date;
	}
	
	public String toString() {
		return (getMessage());
	}
}
