package utils;

public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public String setMessage() {
		return "Parâmetro inválido! Consulte os argumentos do comando na lista de comandos.";
	}
}
