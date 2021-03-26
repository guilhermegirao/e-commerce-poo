package ecommerce;

import java.util.ArrayList;

import storage.Pedidos;
import storage.Status;
import utils.DateUtil;

public class Pedido {
	private int number;
	private ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();
	private int clienteId;
	private String clienteNome;
	private String clienteEmail;
	private int status;
	private String dataCriacao, dataEnvio;
	private String endereco;

	public Pedido (int clienteId, String clienteNome, String clienteEmail, String endereco, ArrayList<ItemPedido> itens) {
		setNumber(Pedidos.getNextNumber());
		Pedidos.setNextNumber();
		
		setClienteId(clienteId);
		setClienteNome(clienteNome);
		setClienteEmail(clienteEmail);
		setItens(itens);
		setStatus(0);
		setDataCriacao(DateUtil.getDate());
		setDataEnvio(null);
		setEndereco(endereco);
	}

	public ArrayList<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemPedido> itens) {
		this.itens = itens;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (status == 2) {
			setDataEnvio(DateUtil.getDate());
		}
		
		this.status = status;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteEmail() {
		return clienteEmail;
	}

	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getSubtotal() {
		double subtotal = 0;
		
		for (ItemPedido ip : itens) {
			subtotal += ip.getPreco();
		}
		
		return subtotal;
	}
	
	public String toString() {
		String dataEnvioString = "";
		String produtos = "";
		
		if (dataEnvio != null) {
			dataEnvioString = "\n Enviado em: " + getDataEnvio();
		}
		
		for (ItemPedido ip : itens) {
			produtos += "\n  " + ip.getQuantidade() + "x " + ip.getProduto() + " (" + ip.getCategoria() + ")" + " - R$ " + ip.getPreco() + "\n";
		}
		
		return "\nPedido #" + getNumber() + ":\n Para: " + getClienteNome()  + "\n Status: " + Status.getStatus(this.getStatus()) + "\n Pedido em: " + getDataCriacao() + dataEnvioString + "\n Subtotal: " + getSubtotal() + "\n Produtos:" + produtos;
	}
	
}
