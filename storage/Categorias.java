package storage;

import java.util.ArrayList;
import java.util.Arrays;

import ecommerce.Categoria;

public class Categorias {
	private static ArrayList<Categoria> categorias = new ArrayList<Categoria>(Arrays.asList(new Categoria("CategoriaTeste1")));

	public static ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	
	public static int getId(String nome) {
		int pos = -1;
		int i = 0;

		for (Categoria c : categorias) {
			if (c != null) {
				if (c.getNome().equalsIgnoreCase(nome)) {
					pos = i;
					break;
				}
			}
			
			i++;
		}
		
		return pos;
	}
	
	public static ArrayList<Categoria> search(String pattern) {
		ArrayList<Categoria> cat = new ArrayList<Categoria>();
		
		for (Categoria c : categorias) {
			if (c != null) {
				if (c.getNome().toLowerCase().contains(pattern.toLowerCase())) {
					cat.add(c);
				}
			}
		}
		
		return cat;
	}

	public static Categoria getByNome(String nome) {
		int id = getId(nome);
		
		return id > -1 ? getCategorias().get(id) : null;
	}
	
	public static Categoria getById(int id) {
		return exists(id) ? getCategorias().get(id) : null;
	}
	
	public static boolean exists(Categoria categoria) {
		return (getId(categoria.getNome()) > -1);
	}

	public static boolean exists(String nome) {
		return (getId(nome) > - 1);
	}
	
	public static boolean exists(int id) {
		if (id < categorias.size() && id > -1) {
			if (getCategorias().get(id) != null) return true;
		}
		
		return false;
	}
	
}
