package utils;

import java.util.Comparator;

import ecommerce.Produto;

public class ProdutoComparator implements Comparator<Produto> {
	@Override
	public int compare(Produto o1, Produto o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
}
