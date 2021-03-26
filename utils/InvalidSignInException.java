package utils;

public class InvalidSignInException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "E-mail ou senha inválidos!";
	}
}
