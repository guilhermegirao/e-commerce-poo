package utils;

import java.util.Comparator;

import ecommerce.Usuario;

public class UsuarioComparator implements Comparator<Usuario> {
	@Override
	public int compare(Usuario o1, Usuario o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
}
