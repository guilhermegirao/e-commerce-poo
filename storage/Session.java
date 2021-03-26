package storage;

import ecommerce.Administrador;
import ecommerce.Usuario;
import ecommerce.Cliente;
import utils.EmailIsAlreadyUsedException;
import utils.InvalidSignInException;

public class Session {
	private static Usuario usuario = null;

	public static Usuario getUsuario() {
		return usuario;
	}

	private static void setUsuario(Usuario usuario) {
		Session.usuario = usuario;
	}
	
	public static boolean signIn(String email, String senha) throws InvalidSignInException {
		Usuario user = Usuarios.getByEmailSenha(email, senha);
		
		if (user != null) {
			setUsuario(user);
			
			return true;
		} else {
			throw new InvalidSignInException();
		}
	}
	
	public static boolean signUp(Cliente cliente) throws EmailIsAlreadyUsedException {
		if (Usuarios.getByEmail(cliente.getEmail()) != null) {
			throw new EmailIsAlreadyUsedException();
		}
		
		Usuarios.getUsuarios().add(cliente);
		return true;
	}
	
	public static void signOut() {
		setUsuario(null);
	}
	
	public static boolean isAdmin() {
		if (usuario instanceof Administrador) {
			return true;
		}
		
		return false;
	}
}
