package utils;

public class EmailIsAlreadyUsedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Esse e-mail j� est� em uso.";
	}
}
