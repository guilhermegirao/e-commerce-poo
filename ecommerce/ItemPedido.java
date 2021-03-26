package ecommerce;

public class ItemPedido {
	
	private String produto;
	private String categoria;
	private int quantidade;
	private double preco;
	
	public ItemPedido(String produto, String categoria, int quantidade, double preco) {
		this.produto = produto;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getProduto() {
		return produto;
	}
	
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	

}
