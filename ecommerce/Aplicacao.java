package ecommerce;

import java.util.Scanner;

import storage.Session;
import utils.InvalidSignInException;

public class Aplicacao {
	Controle controle = new Controle();
	Visao visao = new Visao();
	
	public void start() {
		this.auth();
	}
	
	public void menuCliente() {
		Scanner sc = new Scanner(System.in);
        boolean end = false;

        comandosCliente();
        
        while (!end) {
	        System.out.println("Digite um comando da lista:");
			System.out.print("$");
	
            String line = sc.nextLine();
            String[] cmd = line.split(" ");
            String comando = cmd[0];

            switch (comando) {
            	case "comandos":
            		comandosCliente();
            	break;
            	
            	case "saldo":
            		System.out.println("\nSeu " + visao.showSaldoByCliente((Cliente) Session.getUsuario()));
            		break;
        		
            	case "addSaldo":
            		try {
            			controle.addSaldo(Session.getUsuario(), Double.parseDouble(cmd[1]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "produtos":
            		try {
            			if (cmd.length == 1) {
                			System.out.println("Produtos:\n");
                    		System.out.println(visao.showProdutosAll());
                		} else {
                			System.out.println(visao.showProdutosByCategoria(cmd[1]));
                		}
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;

            	case "buscarProduto":
            		try {
	            		System.out.println("Resultados de produtos para " + cmd[1] + ":");
	            		System.out.println(visao.showProdutosSearch(cmd[1]));
		            } catch (Exception ex) {
		    			System.out.println("\nFAIL: Parâmetro inválido!");
		    		}
            		
            		break;
            		
            	case "categorias":
            		System.out.println("Categorias:");
            		System.out.println(visao.showCategoriasAll());
            		
            		break;
            		
            	case "buscarCategoria":
            		try {
	            		System.out.println("Resultados de categorias para " + cmd[1] + ":");
	            		System.out.println(visao.showCategoriasSearch(cmd[1]));
		            } catch (Exception ex) {
		    			System.out.println("\nFAIL: Parâmetro inválido!");
		    		}
            		
            		break;
            		
            	case "carrinho":
            		System.out.println("Meu Carrinho:");
            		System.out.println(visao.showCarrinhoByCliente((Cliente) Session.getUsuario()));
            		
            		break;
            		
            	case "addCarrinho":
            		try {
            			controle.addCarrinho((Cliente) Session.getUsuario(), cmd[1], Integer.parseInt(cmd[2]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "removerCarrinho":
            		try {
            			controle.deleteCarrinho((Cliente) Session.getUsuario(), cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "fecharPedido":
            		controle.fecharPedido((Cliente) Session.getUsuario());
            		
            		break;
            		
            	case "pedidos":
            		System.out.println("Meus Pedidos:");
            		System.out.println(visao.showPedidosByCliente(Session.getUsuario().getEmail()));
            		
            		break;
            		
           		case "sair":
           			System.out.println("\nSaindo da aplicação...");
           			end = true;
                    break;
                    
                default:
                	System.out.println("\nFAIL: Esse comando não existe!");
                	break;
            }
        }
	}
	
	public void menuAdmin() {
		Scanner sc = new Scanner(System.in);
        boolean end = false;

        comandosAdmin();
        
        while (!end) {
	        System.out.println("Digite um comando da lista:");
			System.out.print("$");
	
            String line = sc.nextLine();
            String[] cmd = line.split(" ");
            String comando = cmd[0];

            switch (comando) {
        		case "comandos":
        			comandosAdmin();
        		break;
        		
            	case "listarUsuarios":
            		System.out.println("Usuários: ");
            		System.out.println(visao.showUsuariosAll());
            		
            		break;
        		
            	case "addCliente":
            		try {
            			String endereco = "";
            			for (int i = 5; i < cmd.length; i++) endereco += cmd[i] + " ";
            			
            			controle.createUsuario(new Cliente(cmd[1], cmd[2], cmd[3], Double.parseDouble(cmd[4]), endereco));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "addAdmin":
            		try {			
            			controle.createUsuario(new Administrador(cmd[1], cmd[2], cmd[3]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "editarUsuario":
            		try {
            			controle.updateUsuario(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "deletarUsuario":
            		try {
            			controle.deleteUsuario(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "buscarUsuario":
            		try {
	            		System.out.println("Resultados de usuários para " + cmd[1] + ":");
	            		System.out.println(visao.showUsuariosSearch(cmd[1]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "listarCategorias":
            		System.out.println("Categorias:");
            		System.out.println(visao.showCategoriasAll());
            		
            		break;
            		
            	case "addCategoria":
            		try {
            			controle.createCategoria(new Categoria(cmd[1]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "editarCategoria":
            		try {
            			controle.updateCategoria(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "deletarCategoria":
            		try {
            			controle.deleteCategoria(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "buscarCategoria":
            		try {
	            		System.out.println("Resultados de categorias para " + cmd[1] + ":");
	            		System.out.println(visao.showCategoriasSearch(cmd[1]));
		            } catch (Exception ex) {
		    			System.out.println("\nFAIL: Parâmetro inválido!");
		    		}
            		
            		break;
            		
            	case "listarProdutos":
            		System.out.println("Produtos:");
            		System.out.println(visao.showProdutosAll());
            		
            		break;
            		
            	case "addProduto":
            		try {
            			controle.createProduto(cmd[1], cmd[2], Double.parseDouble(cmd[3]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "editarProduto":
            		try {
            			controle.updateProduto(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "deletarProduto":
            		try {
            			controle.deleteProduto(cmd[1]);
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "buscarProduto":
            		try {
	            		System.out.println("Resultados de produtos para " + cmd[1] + ":");
	            		System.out.println(visao.showProdutosSearch(cmd[1]));
		            } catch (Exception ex) {
		    			System.out.println("\nFAIL: Parâmetro inválido!");
		    		}
            		
            		break;
            		
            	case "listarPedidos":
            		try {
            			System.out.println("Pedidos:");
            			
            			if (cmd.length == 1) {
                    		System.out.println(visao.showPedidosAll());
                		} else {
                			System.out.println(visao.showPedidosByCliente(cmd[1]));
                		}
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;
            		
            	case "statusPedido":
            		try {
            			controle.updateStatusPedido(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
            		} catch (Exception ex) {
            			System.out.println("\nFAIL: Parâmetro inválido!");
            		}
            		
            		break;         		
   
           		case "sair":
           			System.out.println("\nSaindo da aplicação...");
           			end = true;
                    break;
                    
                default:
                	System.out.println("\nFAIL: Esse comando não existe!");
                	break;
            }
        }
	}
	
	public void auth() {
		boolean stop = false;
		Scanner scanner = new Scanner(System.in);
		
		while (!stop) {
			System.out.println("\n====== E-commerce POO ======");
			System.out.println("\nFaça Login em sua conta: \n");
			
			System.out.print("Insira seu e-mail: ");
			String email = scanner.nextLine();
			
			System.out.print("Insira sua senha: ");
			String senha = scanner.nextLine();
		
			try {
				if (Session.signIn(email, senha)) {
					System.out.println("\nLogando...");
					
					if (Session.isAdmin()) {
						this.menuAdmin();
					} else {
						this.menuCliente();
					}
				}
			} catch (InvalidSignInException e) {
				System.out.println(e);
			}
		}
	}
	
	private void comandosAdmin() {
		System.out.println("\n====== Painel Administrativo ======");
        System.out.println("\nLista de Comandos:\n"
        					+ "$comandos: Lista todos os comandos\n"
        					+ "$listarUsuarios: Lista todos os usuários\n"
        					+ "$addCliente _nome_ _email_ _senha_ _saldo_ _endereco_: Adiciona um novo Cliente \n"
        					+ "$addAdmin _nome_ _email_ _senha_: Adiciona um novo Administrador \n"
        					+ "$editarUsuario _email_: Edita o usuário selecionado \n"
        					+ "$deletarUsuario _email_: Deleta o usuário selecionado \n"
        					+ "$buscarUsuario _valor_: Busca os usuários com o nome ou e-mail do valor inserido \n"
        					+ "$listarCategorias: Lista todas as categorias \n"
        					+ "$addCategoria _nome_: Adiciona uma nova categoria \n"
        					+ "$editarCategoria _nome_: Edita a categoria selecionada \n"
        					+ "$deletarCategoria _nome_: Deleta a categoria selecionada \n"
        					+ "$buscarCategoria _nome_: Busca as categorias com o nome inserido \n"
        					+ "$listarProdutos: Lista todos os produtos \n"
        					+ "$addProduto _nome_ _categoria_ _preco_: Adiciona um novo produto \n"
        					+ "$editarProduto _nome_: Edita o produto selecionado \n"
        					+ "$deletarProduto _nome_: Deleta o produto selecionado \n"	        					
        					+ "$buscarProduto _nome_: Busca os produtos com o nome inserido \n"
							+ "$listarPedidos: Lista todos os pedidos \n"
							+ "$listarPedidos _email_: Lista todos os pedidos do cliente selecionado \n"
							+ "$statusPedido _numeroPedido_ _idStatus_: Altera o status do pedido selecionado (1: Aguardando Confirmação, 2: Em envio, 3: Entregue) \n"
        					+ "$sair: Sair da aplicação.");
        System.out.println("\n====================================\n");
	}
	
	private void comandosCliente() {
		System.out.println("\n====== E-commerce POO ======");
        System.out.println("\nLista de Comandos:\n"
        					+ "$saldo: Mostra seu saldo atual\n"
        					+ "$addSaldo _valor_: Adiciona saldo na sua conta\n"
        					+ "$produtos: Lista todos os produtos \n"
        					+ "$produtos _categoria_: Lista todos os produtos na categoria desejada\n"
        					+ "$buscarProduto _nome_: Busca os produtos com o nome inserido \n"
        					+ "$categorias: Lista todas as Categorias \n"
        					+ "$buscarCategoria _nome_: Busca as categorias com o nome inserido \n"
        					+ "$carrinho: Lista todos os itens do seu Carrinho\n"
        					+ "$addCarrinho _produto_ _quantidade_: Adiciona o produto selecionado no Carrinho\n"
        					+ "$removerCarrinho _produto_: Remove o produto selecionado do Carrinho\n"
        					+ "$fecharPedido: Faz a compra dos produtos no carrinho\n"
        					+ "$pedidos: Lista todos os seus Pedidos \n"
        					+ "$sair: Sair da aplicação.");
        System.out.println("\n====================================\n");
	}
}
