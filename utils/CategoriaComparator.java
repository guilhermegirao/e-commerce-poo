package utils;

import java.util.Comparator;

import ecommerce.Categoria;

public class CategoriaComparator implements Comparator<Categoria> {
	@Override
	public int compare(Categoria o1, Categoria o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
}
