package storage;

import java.util.ArrayList;

import ecommerce.Carrinho;
import ecommerce.Categoria;
import ecommerce.Cliente;
import ecommerce.Item;
import ecommerce.ItemPedido;
import ecommerce.Pedido;
import ecommerce.Produto;

public class Pedidos {
	private static int nextNumber = 0;
	private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

	public static ArrayList<Pedido> getPedidos () {
		return pedidos;
	}
	
	public static int getNextNumber() {
		return nextNumber;
	}

	public static void setNextNumber() {
		Pedidos.nextNumber += 1;
	}
	
	public static boolean exists(int id) {
		if (id < pedidos.size() && id > -1) {
			return true;
		}
		
		return false;
	}

	public static void addPedidos(Cliente cliente) {
		Carrinho car = cliente.getCarrinho();
		ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();

		boolean isValid = false;
		
		for (Item it : car.getItens()) {
			if (it != null) {
				if (Produtos.exists(it.getProduto())) {
					isValid = true;

					Produto p = Produtos.getById(it.getProduto());
					
					Categoria categoria = Categorias.getById(p.getIdCategoria());
					String cat = "Sem categoria";
					
					if (categoria != null) cat = categoria.getNome();
					
					itens.add(new ItemPedido(p.getNome(), cat, it.getQuantidade(), it.getValor()));
				}
			}
		}
		
		if (isValid) pedidos.add(new Pedido(Usuarios.getId(cliente.getEmail()), cliente.getNome(), cliente.getEmail(), cliente.getEndereco(), itens));
	}
	
}
