package ecommerce;

import storage.Produtos;

public class Item {
	private int produto;
	private int quantidade;
	
	public Item (int produto, int quantidade) {
		setProduto(produto);
		setQuantidade(quantidade);
	}
	
	public double getValor() {
		return Produtos.getProdutos().get(produto).getValor() * quantidade;
	}
	
	public int getProduto() {
		return produto;
	}

	public void setProduto(int produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
