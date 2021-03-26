package ecommerce;

import java.util.ArrayList;
import java.util.Scanner;

import storage.Categorias;
import storage.Pedidos;
import storage.Usuarios;
import storage.Produtos;
import storage.Session;
import storage.Status;

public class Controle {
	/*
	 * Categorias:
	 * createCategoria: Cria uma nova categoria [Administrador]
	 * updateCategoria: Atualiza os dados de uma categoria [Administrador]
	 * deleteCategoria: Remove uma categoria [Administrador]
	 */

	public void createCategoria(Categoria categoria) {
		if (!Categorias.exists(categoria)) {
			Categorias.getCategorias().add(categoria);
			
			System.out.println("\nCategoria " + categoria.getNome() + " adicionada com sucesso!");
		} else {
			System.out.println("\nFAIL: J� existe uma categoria com esse nome!");
		}
	}
	
	public void updateCategoria(String nome) {
		Categoria categoria = Categorias.getByNome(nome);
		
		if (categoria != null) {
			Scanner sc = new Scanner(System.in);
	        boolean end = false;

	        while (!end) {
		        System.out.println("\n====== Menu de edi��o da Categoria " + categoria.getNome() + ": ======");
		        System.out.println("\nLista de Comandos:\n"
		        					+ "$nome _nome_: Atualiza o nome da categoria \n"
		        					+ "$voltar: Sair do menu de edi��o.");
		        System.out.println("\n====================================\n");
		        
		        System.out.println("Digite um comando da lista:");
				System.out.print("$");
		
	            String line = sc.nextLine();
	            String[] cmd = line.split(" ");
	            String comando = cmd[0];
	
	            switch (comando) {
	            	case "nome":
	            		if (!Categorias.exists(cmd[1])) {
	            			categoria.setNome(cmd[1]);
	            			
	            			System.out.println("\nNome da categoria alterada com sucesso!");
	            		} else {
	            			System.out.println("\nFAIL: J� existe uma categoria com esse nome!");
	            		}
	            		break;
	            		
	           		case "voltar":
	           			System.out.println("\nSaindo do menu de edi��o...");
	           			end = true;
	                    break;
	                    
	                default:
	                	System.out.println("\nFAIL: Esse comando n�o existe!");
	                	break;
	            }
	        }
		} else {
			System.out.println("\nFAIL: N�o existe uma categoria com esse nome!");
		}
	}
	
	public void deleteCategoria(String nome) {
		int id = Categorias.getId(nome);
		
		if (id > -1) {
			Categorias.getCategorias().set(id, null);
			
			System.out.println("\nCategoria removida com sucesso!");
		} else {
			System.out.println("\nFAIL: N�o existe uma categoria com esse nome!");
		}
	}
	
	/*
	 * Usu�rios:
	 * createUsuario: Cria um novo usu�rio admin ou cliente [Administrador/Cliente]
	 * updateUsuario: Atualiza os dados de um usu�rio [Administrador/Cliente]
	 * deleteUsuario: Remove um usu�rio [Administrador]
	 * addSaldo: Adiciona saldo para um cleinte [Administrador/Cliente]
	 */

	public void createUsuario(Usuario usuario) {
		if (!Usuarios.exists(usuario)) {
			Usuarios.getUsuarios().add(usuario);
			
			if (usuario instanceof Administrador) {
				System.out.println("\nAdministrador " + usuario.getNome() + " adicionado com sucesso!");
			} else {
				if (((Cliente) usuario).getSaldo() >= 0) {
					System.out.println("\nCliente " + usuario.getNome() + " adicionado com sucesso!");
				} else {
					System.out.println("\nFAIL: Saldo n�o pode ser negativo!");
				}
			}
		} else {
			System.out.println("\nFAIL: J� existe um usu�rio com esse e-mail!");
		}
	}
	
	public void updateUsuario(String email) {
		Usuario usuario = Usuarios.getByEmail(email);
		
		if (usuario != null) {
			Scanner sc = new Scanner(System.in);
	        boolean end = false;
	        
	        String endereco = (usuario instanceof Cliente)? "$endereco _endereco_ Atualiza o endere�o do usu�rio \n" : "";
	        String saldo = (usuario instanceof Cliente)? "$saldo _valor_ Atualiza o saldo do usu�rio \n" : "";

	        while (!end) {
		        System.out.println("\n====== Menu de edi��o de usu�rio " + usuario.getNome() + ": ======");
		        System.out.println("\nLista de Comandos:\n"
		        					+ "$nome _nome_: Atualiza o nome do usu�rio \n"
		        					+ "$email _email_: Atualiza o e-mail do usu�rio \n"
		        					+ "$senha _senha_: Atualiza o senha do usu�rio \n"
		        					+ saldo
		        					+ endereco
		        					+ "$voltar: Sair do menu de edi��o.");
		        System.out.println("\n====================================\n");
		        
		        System.out.println("Digite um comando da lista:");
				System.out.print("$");
		
	            String line = sc.nextLine();
	            String[] cmd = line.split(" ");
	            String comando = cmd[0];
	
	            switch (comando) {
	            	case "email":
	            		if (!Usuarios.exists(cmd[1])) {
	            			if (!usuario.getEmail().equals("admin@admin.com")) {
		            			usuario.setEmail(cmd[1]);
		            			
		            			System.out.println("\nE-mail do usu�rio alterado com sucesso!");
	            			} else {
	            				System.out.println("\nVoc� n�o pode editar o e-mail do administrador geral!");
	            			}
	            		} else {
	            			System.out.println("\nFAIL: J� existe uma categoria com esse nome!");
	            		}
	            		break;
	            		
	            	case "nome":
	            		usuario.setNome(cmd[1]);
	            		System.out.println("\nNome do usu�rio alterado com sucesso!");
	            		
	            		break;
	            		
	            	case "senha":
	            		usuario.setSenha(cmd[1]);
	            		System.out.println("\nSenha do usu�rio alterada com sucesso!");
	            		
	            		break;
	            		
	            	case "saldo":
	            		if (usuario instanceof Cliente) {
	            			double valor = 0;
	            			
	            			try {
		            			valor = Double.parseDouble(cmd[1]);
			            	} catch (Exception ex) {
			            		System.out.println("\nFAIL: Saldo deve ser um n�mero!");
			            	} finally {
			            		if (valor >= 0) {
			            			((Cliente) usuario).setSaldo(valor);
			            			
			            			System.out.println("\nSaldo do usu�rio alterado com sucesso!");
			            		} else {
			            			System.out.println("\nFAIL: Saldo n�o pode ser negativo!");
			            		}
			            	}
	            		} else {
	            			System.out.println("\nFAIL: Essa informa��o n�o existe para administradores!");
	            		}
	            		
	            		break;
	            		
	            	case "endereco":
	            		if (usuario instanceof Cliente) {
	            			String enderecoEdit = "";
	            			for (int i = 1; i < cmd.length; i++) enderecoEdit += cmd[i] + " ";
	            			
		            		((Cliente) usuario).setEndereco(enderecoEdit);
		            		System.out.println("\nEndere�o do usu�rio alterado com sucesso!");
	            		} else {
	            			System.out.println("\nFAIL: Essa informa��o n�o existe para administradores!");
	            		}
	            		
	            		break;
	            		
	           		case "voltar":
	           			System.out.println("\nSaindo do menu de edi��o...");
	           			end = true;
	                    break;
	                    
	                default:
	                	System.out.println("\nFAIL: Esse comando n�o existe!");
	                	break;
	            }
	        }
		} else {
			System.out.println("\nFAIL: N�o existe um usu�rio com esse e-mail!");
		}
	}

	public void deleteUsuario(String email) {
		int id = Usuarios.getId(email);
		
		if (!email.equals("admin@admin.com")) {
			if (id > -1) {
				Usuarios.getUsuarios().set(id, null);
				
				System.out.println("\nUsu�rio removido com sucesso!");
			} else {
				System.out.println("\nFAIL: N�o existe um usu�rio com esse e-mail!");
			}
		} else {
			System.out.println("\nFAIL: Voc� n�o pode remover o administrador geral!");
		}
	}
	
	public void addSaldo(Usuario usuario, double valor) {
		if (Usuarios.exists(usuario)) {
			if (valor > 0) {
				Cliente c = ((Cliente) usuario);
				
				c.setSaldo(c.getSaldo() + valor);
				
				System.out.println("\nR$ " + valor + " foram adicionados a conta!");
			} else {
				System.out.println("\nFAIL: Saldo deve ser maior que zero!");
			}
		} else {
			System.out.println("\nFAIL: Esse cliente n�o existe!");
		}
	}
	
	/*
	 * Produtos:
	 * createProduto: Cria um novo produto  [Administrador]
	 * updateProduto: Atualiza os dados de um produto [Administrador]
	 * deleteProduto: Remove um produto [Administrador]
	 */

	public void createProduto(String nome, String categoria, double valor) {
		if (!Produtos.exists(nome)) {
			if (valor >= 0) {
				int idCategoria = Categorias.getId(categoria);
				
				if (idCategoria > -1) {
					Produtos.getProdutos().add(new Produto(nome, idCategoria, valor));
					
					System.out.println("\nProduto adicionado com sucesso!");
				} else {
					System.out.println("\nFAIL: N�o existe uma categoria com esse nome!");
				}
			} else {
				System.out.println("\nFAIL: Pre�o n�o pode ser negativo!");
			}
	
		} else {
			System.out.println("\nFAIL: J� existe um produto com esse nome!");
		}
	}
	
	public void updateProduto(String nome) {
		Produto produto = Produtos.getByNome(nome);
		
		if (produto != null) {
			Scanner sc = new Scanner(System.in);
	        boolean end = false;

	        while (!end) {
		        System.out.println("\n====== Menu de edi��o do Produto " + produto.getNome() + ": ======");
		        System.out.println("\nLista de Comandos:\n"
		        					+ "$nome _nome_: Atualiza o nome do Produto \n"
		        					+ "$categoria _nome_: Atualiza a categoria do Produto \n"
		        					+ "$preco _nome_: Atualiza o pre�o do Produto \n"
		        					+ "$voltar: Sair do menu de edi��o.");
		        System.out.println("\n====================================\n");
		        
		        System.out.println("Digite um comando da lista:");
				System.out.print("$");
		
	            String line = sc.nextLine();
	            String[] cmd = line.split(" ");
	            String comando = cmd[0];
	
	            switch (comando) {
	            	case "nome":
	            		if (!Produtos.exists(cmd[1])) {
	            			produto.setNome(cmd[1]);
	            			
	            			System.out.println("\nNome do produto alterado com sucesso!");
	            		} else {
	            			System.out.println("\nFAIL: J� existe um produto com esse nome!");
	            		}
	            		break;
	            		
	            	case "categoria":
	            		if (Categorias.exists(cmd[1])) {
	            			produto.setNome(cmd[1]);
	            			
	            			System.out.println("\nCategoria do produto alterada com sucesso!");
	            		} else {
	            			System.out.println("\nFAIL: N�o existe uma categoria com esse nome!");
	            		}
	            		break;
	            		
	            	case "preco":
	            		double valor = 0;
	            		
	            		try {
	            			valor = Double.parseDouble(cmd[1]);
		            	} catch (Exception ex) {
		            		System.out.println("\nFAIL: Pre�o deve ser um n�mero!");
		            	} finally {
		            		if (valor >= 0) {
		            			produto.setValor(valor);
		            		} else {
		            			System.out.println("\nFAIL: Pre�o n�o pode ser negativo!");
		            		}
		            	}
	            		break;
	            		
	           		case "voltar":
	           			System.out.println("\nSaindo do menu de edi��o...");
	           			end = true;
	                    break;
	                    
	                default:
	                	System.out.println("\nFAIL: Esse comando n�o existe!");
	                	break;
	            }
	        }
		} else {
			System.out.println("\nFAIL: N�o existe um produto com esse nome!");
		}
	}

	public void deleteProduto(String nome) {
		int id = Produtos.getId(nome);
		
		if (id > -1) {
			Produtos.getProdutos().set(id, null);
				
			System.out.println("\nProduto removido com sucesso!");
		} else {
			System.out.println("\nFAIL: N�o existe um produto com esse nome!");
		}
	}
	
	/*
	 * Carrinho:
	 * addCarrinho: Adiciona um produto para o carrinho de um cliente  [Administrador/Cliente]
	 * deleteCarrinho: Remove um produto do carrinho de um cliente [Administrador/Cliente]
	 */
	
	public void addCarrinho(Cliente cliente, String produto, int quantidade) {
		if (Usuarios.exists(cliente)) {
			if (quantidade > 0) {
				int p = Produtos.getId(produto);
				
				if (p > -1) {
					Item item = new Item(p, quantidade);
					
					if (!cliente.getCarrinho().exists(p)) {
						cliente.getCarrinho().addItem(item);
						
						System.out.println("\nProduto adicionado no carrinho com sucesso!");
					} else {
						System.out.println("\nFAIL: O produto j� est� no carrinho!");
					}
				} else {
					System.out.println("\nFAIL: O produto selecionado n�o existe!");
				}
			} else {
				System.out.println("\nFAIL: Quantidade deve ser maior que zero!");
			}
		} else {
			System.out.println("\nFAIL: Esse cliente n�o existe!");
		}
	}
	
	public void deleteCarrinho(Cliente cliente, String produto) {
		if (Usuarios.exists(cliente)) {
			int p = Produtos.getId(produto);
				
			if (p > -1) {
				if (cliente.getCarrinho().exists(p)) {
					cliente.getCarrinho().removeItem(p);
						
					System.out.println("\nProduto removido do carrinho com sucesso!");
				} else {
					System.out.println("\nFAIL: O produto j� est� no carrinho!");
				}
			} else {
				System.out.println("\nFAIL: O produto selecionado n�o existe!");
			}
		} else {
			System.out.println("\nFAIL: Esse cliente n�o existe!");
		}
	}

	/*
	 * Pedidos:
	 * fecharPedido: Fecha o pedido do carrinho do cliente  [Cliente]
	 * updateStatusPedido: Atualiza o status de um pedido do cliente [Administrador]
	 */
	
	public void fecharPedido(Cliente cliente) {
		if (Usuarios.exists(cliente)) {
			if (cliente.getCarrinho().getSize() > 0) {
					if (cliente.getCarrinho().getSubtotal() <= cliente.getSaldo()) {
						Pedidos.addPedidos(cliente);
						
						cliente.setCarrinho(new Carrinho());
						
						System.out.println("\nPedido realizado com sucesso!");
					} else {
						System.out.println("\nVoc� n�o tem saldo suficiente para realizar esse pedido!");
					}
				} else {
					System.out.println("\nFAIL: Seu carrinho est� vazio!");
			}
		} else {
			System.out.println("\nFAIL: Sua conta n�o existe mais!");
		}
	}
	
	public void updateStatusPedido(int numeroPedido, int status) {
		if (status >= 1 && status <= 3) {
			if (Pedidos.exists(numeroPedido)) {
				Pedidos.getPedidos().get(numeroPedido).setStatus(status);
				
				System.out.println("\nStatus do Pedido #" + numeroPedido + " atualizado para " + Status.getStatus(status) + "!");
			} else {
				System.out.println("\nFAIL: N�o existe um pedido com esse n�mero!");
			}
		} else {
			System.out.println("\nFAIL: Esse status de pedido n�o existe!");
		}
	}
	
	
}
