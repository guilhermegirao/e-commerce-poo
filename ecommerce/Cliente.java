package ecommerce;

public class Cliente extends Usuario {
	private Carrinho carrinho;
	private String endereco;
	private double saldo;

	public Cliente(String nome, String email, String senha, String endereco) {
		super(nome, email, senha);
		setEndereco(endereco);
		setSaldo(0.0);
		setCarrinho(new Carrinho());
	}
	
	public Cliente(String nome, String email, String senha, double saldo, String endereco) {
		super(nome, email, senha);
		setEndereco(endereco);
		setSaldo(saldo);
		setCarrinho(new Carrinho());
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}
