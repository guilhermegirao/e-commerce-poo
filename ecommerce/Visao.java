package ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import storage.Categorias;
import storage.Pedidos;
import storage.Produtos;
import storage.Usuarios;

import utils.UsuarioComparator;
import utils.ProdutoComparator;
import utils.CategoriaComparator;
import utils.PedidosComparator;

public class Visao {
	
	//Mostra o saldo do cliente selecionado
	public String showSaldoByCliente(Cliente cliente) {
		String ret = "\nEsse cliente n�o existe!";
		
		if (cliente != null) {
			if (Usuarios.exists(cliente.getEmail())) {
				return "Saldo: R$ " + cliente.getSaldo();
			}
		}
		
		return ret;
	}
	
	//Mostra todas as categorias
	public String showCategoriasAll() {
		Collections.sort(Categorias.getCategorias(), Comparator.nullsFirst(new CategoriaComparator()));
		
		String ret = "";
		
		for (Categoria c : Categorias.getCategorias()) {
			if (c != null)
				ret += c.getNome() + "\n";
		}
		
		return ret.equals("")? "\nN�o h� categorias cadastradas" : ret;
	}
	
	//Mostra as categorias com base na busca
	public String showCategoriasSearch(String pattern) {
		ArrayList<Categoria> search = Categorias.search(pattern);
		
		Collections.sort(search, Comparator.nullsFirst(new CategoriaComparator()));
		
		String ret = "";
		
		for (Categoria c : search) {
			if (c != null)
				ret += c.getNome() + "\n";
		}
		
		return ret.equals("")? "\nN�o h� categorias correspondentes a pesquisa" : ret;
	}
	
	//Mostra todos os usu�rios
	public String showUsuariosAll() {
		Collections.sort(Usuarios.getUsuarios(), Comparator.nullsFirst(new UsuarioComparator()));
		
		String ret = "";
		
		for (Usuario u : Usuarios.getUsuarios()) {
			if (u != null) {
				if (u instanceof Cliente) {
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nEndere�o: " + ((Cliente) u).getEndereco()  + "\nSaldo: " + ((Cliente) u).getSaldo() + "\nPermiss�o: Cliente\n";
				} else {					
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nPermiss�o: Administrador\n";
				}
			}
		}
		
		return ret.equals("")? "\nN�o h� usu�rios cadastrados" : ret;
	}
	
	//Mostra os usu�rios com base na busca
	public String showUsuariosSearch(String pattern) {
		ArrayList<Usuario> search = Usuarios.search(pattern);
		
		Collections.sort(search, Comparator.nullsFirst(new UsuarioComparator()));
		
		String ret = "";
		
		for (Usuario u : search) {
			if (u != null) {
				if (u instanceof Cliente) {
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nEndere�o: " + ((Cliente) u).getEndereco()  + "\nSaldo: " + ((Cliente) u).getSaldo() + "\nPermiss�o: Cliente\n";
				} else {					
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nPermiss�o: Administrador\n";
				}
			}
		}
		
		return ret.equals("")? "\nN�o h� usu�rios correspondentes a pesquisa" : ret;
	}
	
	//Motra todos os produtos
	public String showProdutosAll() {
		Collections.sort(Produtos.getProdutos(), Comparator.nullsFirst(new ProdutoComparator()));
		
		String ret = "";
		
		for (Produto p : Produtos.getProdutos()) {
			if (p != null) {
				Categoria categoria = Categorias.getById(p.getIdCategoria());
				String cat = "Sem categoria";
				
				if (categoria != null) cat = categoria.getNome();
				
				ret += "Nome: " + p.getNome() + "\nPre�o Unt.: R$ " + p.getValor() + "\nCategoria: " + cat +  "\n";
			}
		}
		
		return ret.equals("")? "\nN�o h� produtos cadastrados" : ret;
	}
	
	//Mostra os produtos com base na categoria
	public String showProdutosByCategoria(String categoriaNome) {
		int categoriaId = Categorias.getId(categoriaNome);
		
		if (categoriaId > -1) {
			Collections.sort(Produtos.getProdutos(), Comparator.nullsFirst(new ProdutoComparator()));
			
			String ret = "";
			
			for (Produto p : Produtos.getProdutos()) {
				if (p != null) {
					if (categoriaId == p.getIdCategoria())
						ret += "Nome: " + p.getNome() + "\nPre�o Unt.: R$ " + p.getValor() + "\n";
				}
			}
			
			return ret.equals("")? "\nN�o h� produtos nessa categoria" : "Produtos na categoria " + categoriaNome + ":\n\n" + ret;
		} else {
			return "\nFAIL: Essa categoria n�o existe!";
		}
	}
	
	//Mostra os produtos com base na busca
	public String showProdutosSearch(String pattern) {
		ArrayList<Produto> search = Produtos.search(pattern);
		
		Collections.sort(search, Comparator.nullsFirst(new ProdutoComparator()));
		
		String ret = "";
		
		for (Produto p : search) {
			if (p != null) {
				Categoria categoria = Categorias.getById(p.getIdCategoria());
				String cat = "Sem categoria";
				
				if (categoria != null) cat = categoria.getNome();
				
				ret += "Nome: " + p.getNome() + "\nPre�o Unt.: R$ " + p.getValor() + "\nCategoria: " + cat +  "\n";
			}
		}
		
		return ret.equals("")? "\nN�o h� produtos correspondentes a pesquisa" : ret;
	}
	
	//Mostra o carrinho do cliente selecionado
	public String showCarrinhoByCliente(Cliente cliente) {
		String ret = "";
		
		if (cliente != null) {
			if (Usuarios.exists(cliente.getEmail())) {
				for (Item i : cliente.getCarrinho().getItens()) {
					if (i != null) {
						if (cliente.getCarrinho().exists(i.getProduto())) {
							ret += "\n" + i.getQuantidade() + "x " + Produtos.getById(i.getProduto()).getNome() + " - R$ " + i.getValor();
						}
					}
				}
			} else {
				ret = "\nFAIL: Esse cliente n�o existe!";
			}
		} else {
			ret = "\nFAIL: Esse cliente n�o existe!";
		}
		
		return ret.equals("")? "\nN�o h� produtos no carrinho" : ret + "\n\n============================\nSubtotal: R$" + cliente.getCarrinho().getSubtotal() + "\n";
	}
	
	//Mostra os pedidos do cliente selecionado
	public String showPedidosByCliente(String email) {
		Collections.sort(Pedidos.getPedidos(), Collections.reverseOrder(new PedidosComparator()));

		String ret = "";
		Usuario u = Usuarios.getByEmail(email);
		
		if (u != null) {
			if (u instanceof Cliente) {
				Cliente cliente  = (Cliente) u; 
					
				if (Usuarios.exists(cliente.getEmail())) {
					for (Pedido p : Pedidos.getPedidos()) {
						if (p != null) {
							if (p.getClienteId() == Usuarios.getId(cliente.getEmail())) {
								ret += p;
							}
						}
					}
				} else {
					ret = "\nFAIL: Esse cliente n�o existe!";
				}
			} else {
				ret = "\nFAIL: Esse usu�rio n�o � cliente!";
			}
		} else {
			ret = "\nFAIL: Esse usu�rio n�o existe!";
		}
		
		return ret.equals("")? "\nN�o h� pedidos realizados" : ret;
	}
	
	//Mostra todos os pedidos
	public String showPedidosAll() {
		Collections.sort(Pedidos.getPedidos(), Collections.reverseOrder(new PedidosComparator()));
		
		String ret = "";
		
		for (Pedido p : Pedidos.getPedidos()) {
			if (p != null) {
				ret += p;
			}
		}
		
		return ret.equals("")? "\nN�o h� pedidos realizados" : ret;
	}
	
}
