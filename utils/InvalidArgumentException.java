package utils;

public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public String setMessage() {
		return "Par�metro inv�lido! Consulte os argumentos do comando na lista de comandos.";
	}
}
