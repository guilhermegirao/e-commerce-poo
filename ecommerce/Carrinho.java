package ecommerce;

import java.util.ArrayList;

import storage.Produtos;

public class Carrinho {
	private ArrayList<Item> itens = new ArrayList<Item>();
	
	public ArrayList<Item> getItens () {
		return itens;
	}
	
	public void setItens (ArrayList<Item> itens) {
		this.itens = itens;
	}
	
	public double getSubtotal () {
		double valor = 0;
		
		for (Item i : itens) {
			if (i != null) {
				if (Produtos.exists(i.getProduto())) {
					valor += i.getValor();
				}
			}
		}
		
		return valor;
	}
	
	public int getId(int produto) {
		int pos = -1;
		int i = 0;
		
		for (Item it : itens) {
			if (it != null) {
				if (Produtos.exists(it.getProduto())) {
					if (it.getProduto() == produto) {
						pos = i;
						
						break;
					}
				}
			}
			
			i++;
		}
		
		return pos;
	}
	
	public int getSize() {
		int i = 0;
		
		for (Item it : itens) {
			if (it != null) {
				if (Produtos.exists(it.getProduto()))
					++i;
			}
		}
		
		return i;
	}
	
	public void addItem(Item i) {
		this.itens.add(i);
	}
	
	public void removeItem(int id) {
		this.itens.set(id, null);
	}
	
	public boolean exists(int produto) {
		return (getId(produto) > -1);
	}
	
}
