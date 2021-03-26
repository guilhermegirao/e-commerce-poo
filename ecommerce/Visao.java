package ecommerce;

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
	public String showSaldoByCliente(Cliente cliente) {
		String ret = "\nEsse cliente não existe!";
		
		if (cliente != null) {
			if (Usuarios.exists(cliente.getEmail())) {
				return "Saldo: R$ " + cliente.getSaldo();
			}
		}
		
		return ret;
	}
	
	public String showCategoriasAll() {
		Collections.sort(Categorias.getCategorias(), Comparator.nullsFirst(new CategoriaComparator()));
		
		String ret = "";
		
		for (Categoria c : Categorias.getCategorias()) {
			if (c != null)
				ret += c.getNome() + "\n";
		}
		
		return ret.equals("")? "\nNão há categorias cadastradas" : ret;
	}
	
	public String showUsuariosAll() {
		Collections.sort(Usuarios.getUsuarios(), Comparator.nullsFirst(new UsuarioComparator()));
		
		String ret = "";
		
		for (Usuario u : Usuarios.getUsuarios()) {
			if (u != null) {
				if (u instanceof Cliente) {
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nEndereço: " + ((Cliente) u).getEndereco()  + "\nSaldo: " + ((Cliente) u).getSaldo() + "\nPermissão: Cliente\n";
				} else {					
					ret += "\nNome: " + u.getNome() + "\nE-mail: " + u.getEmail() + "\nPermissão: Administrador\n";
				}
			}
		}
		
		return ret.equals("")? "\nNão há usuários cadastradas" : ret;
	}
	
	public String showProdutosAll() {
		Collections.sort(Produtos.getProdutos(), Comparator.nullsFirst(new ProdutoComparator()));
		
		String ret = "";
		
		for (Produto p : Produtos.getProdutos()) {
			if (p != null) {
				Categoria categoria = Categorias.getById(p.getIdCategoria());
				String cat = "Sem categoria";
				
				if (categoria != null) cat = categoria.getNome();
				
				ret += "Nome: " + p.getNome() + "\nPreço Unt.: R$ " + p.getValor() + "\nCategoria: " + cat +  "\n";
			}
		}
		
		return ret.equals("")? "\nNão há produtos cadastrados" : ret;
	}
	
	public String showProdutosByCategoria(String categoriaNome) {
		int categoriaId = Categorias.getId(categoriaNome);
		
		if (categoriaId > -1) {
			Collections.sort(Produtos.getProdutos(), Comparator.nullsFirst(new ProdutoComparator()));
			
			String ret = "";
			
			for (Produto p : Produtos.getProdutos()) {
				if (p != null) {
					if (categoriaId == p.getIdCategoria())
						ret += "Nome: " + p.getNome() + "\nPreço Unt.: R$ " + p.getValor() + "\n";
				}
			}
			
			return ret.equals("")? "\nNão há produtos nessa categoria" : "Produtos na categoria " + categoriaNome + ":\n\n" + ret;
		} else {
			return "\nFAIL: Essa categoria não existe!";
		}
	}
	
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
				ret = "\nFAIL: Esse cliente não existe!";
			}
		} else {
			ret = "\nFAIL: Esse cliente não existe!";
		}
		
		return ret.equals("")? "\nNão há produtos no carrinho" : ret + "\n\n============================\nSubtotal: R$" + cliente.getCarrinho().getSubtotal() + "\n";
	}
	
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
					ret = "\nFAIL: Esse cliente não existe!";
				}
			} else {
				ret = "\nFAIL: Esse usuário não é cliente!";
			}
		} else {
			ret = "\nFAIL: Esse usuário não existe!";
		}
		
		return ret.equals("")? "\nNão há pedidos realizados" : ret;
	}
	
	public String showPedidosAll() {
		Collections.sort(Pedidos.getPedidos(), Collections.reverseOrder(new PedidosComparator()));
		
		String ret = "";
		
		for (Pedido p : Pedidos.getPedidos()) {
			if (p != null) {
				ret += p;
			}
		}
		
		return ret.equals("")? "\nNão há pedidos realizados" : ret;
	}
	
}
