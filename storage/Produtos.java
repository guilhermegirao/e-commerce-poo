package storage;

import java.util.ArrayList;
import java.util.Arrays;

import ecommerce.Produto;

public class Produtos {
	private static ArrayList<Produto> produtos = new ArrayList<Produto>(Arrays.asList(new Produto("ProdutoTeste", 0, 10.0)));

	public static ArrayList<Produto> getProdutos () {
		return produtos;
	}
	
	public static int getId(String nome) {
		int pos = -1;
		int i = 0;
		

		for (Produto c : produtos) {
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

	public static Produto getByNome(String nome) {
		int id = getId(nome);
		
		return id > -1 ? getProdutos().get(id) : null;
	}
	
	public static Produto getById(int id) {
		return exists(id) ? getProdutos().get(id) : null;
	}
	
	public static boolean exists(Produto produto) {
		return (getId(produto.getNome()) > -1);
	}

	public static boolean exists(String nome) {
		return (getId(nome) > - 1);
	}
	
	public static boolean exists(int id) {
		if (id < produtos.size() && id > -1) {
			if (getProdutos().get(id) != null) return true;
		}
		
		return false;
	}
	
}
