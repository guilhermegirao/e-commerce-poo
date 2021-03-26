 package ecommerce;

public class Produto {
	private String nome;
	private int idCategoria;
	private double valor;
	
	public Produto (String nome, int idCategoria, double valor) {
		setNome(nome);
		setIdCategoria(idCategoria);
		setValor(valor);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(int categoria) {
		this.idCategoria = categoria;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

}
