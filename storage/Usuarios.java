package storage;

import java.util.ArrayList;
import java.util.Arrays;

import ecommerce.Administrador;
import ecommerce.Cliente;
import ecommerce.Usuario;

public class Usuarios {
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>(Arrays.asList(new Administrador("Adminstrador", "admin@admin.com", "admin"), new Cliente("Cliente", "cliente@cliente.com", "cliente", 0, "Meu endereço")));

	public static ArrayList<Usuario> getUsuarios () {
		return usuarios;
	}
	
	public static Usuario getByEmailSenha (String email, String senha) {
		for (Usuario u : usuarios) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
				return u;
			}
		}
		
		return null;
	}
	
	public static int getId(String email) {
		int pos = -1;
		int i = 0;
		
		for (Usuario u : usuarios) {
			if (u != null) {
				if (u.getEmail().equalsIgnoreCase(email)) {
					pos = i;
					break;
				}
			}
			
			i++;
		}
		
		return pos;
	}
	
	public static Usuario getByEmail(String email) {
		int id = getId(email);
		
		return id > -1 ? getUsuarios().get(id) : null;
	}
	
	public static boolean exists(Usuario usuario) {
		return (getId(usuario.getEmail()) > -1);
	}

	public static boolean exists(String email) {
		return (getId(email) > - 1);
	}
	
}
