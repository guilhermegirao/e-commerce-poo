package ecommerce;

public abstract class Usuario {
	private String email, senha, nome;
	
	public Usuario (String nome, String email, String senha) {
		setEmail(email);
		setSenha(senha);
		setNome(nome);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
