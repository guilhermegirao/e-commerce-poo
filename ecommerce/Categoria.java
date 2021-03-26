package ecommerce;

public class Categoria {
	private String nome;
	
	public Categoria() {};
	
	public Categoria (String nome) {
		cadastrar(nome);
	}
	
	public boolean cadastrar(String nome) {
		setNome(nome);
		
		return true;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return getNome();
 	}
	
}
