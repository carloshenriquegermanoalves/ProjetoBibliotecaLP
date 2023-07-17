package models;

import java.awt.HeadlessException;
import java.io.*;
import javax.swing.*;

public class SistemaPrincipal {

	public static void main(String[] args) {
		SistemaBibliotecaInterface sistema = new SistemaBiblioteca();
		GravadorDeLivros gravador = new GravadorDeLivros();

		try {
			sistema.setListaDeLivros(gravador.recuperaLivros());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		boolean continuar = true;
		while (continuar) {
			String opcaoMenuPrincipal = JOptionPane
					.showInputDialog("1.Área de Exibição\n2.Área de Cadastro\n3.Área de Busca\n4.Sair do Sistema");
			switch (opcaoMenuPrincipal) {
			case "1":
				String opcaoMenuDeExibicaoPrincipal = JOptionPane.showInputDialog(
						"1.Ordem de Aquisição\n2.Ordem Alfabética\n3.Lista de Gêneros\n4.Lista de Autores.\n"
								+ "5.Ordenados Alfabeticamente por Gêneros");
				switch (opcaoMenuDeExibicaoPrincipal) {
				case "1":
					if (sistema.getListaDeLivros().size() > 0) {
						JOptionPane.showMessageDialog(null, "Todos os livros cadastrados na biblioteca são: ");
						sistema.exibeTodosOsLivros();
					} else {
						JOptionPane.showMessageDialog(null,
								"Ainda não há livros cadastrados na biblioteca! Tente cadastrar algum!");
					}
					break;
				case "2":
					if (sistema.getListaDeLivros().size() > 0) {
						JOptionPane.showMessageDialog(null,
								"Os livros cadastrados na biblioteca, em ordem alfabética, são: ");
						sistema.exibeListaDeLivros(sistema.organizaLivrosAlfabeticamente());
					} else {
						JOptionPane.showMessageDialog(null,
								"Ainda não há livros cadastrados na biblioteca! Tente cadastrar algum!");
					}
					break;
				case "3":
					if (sistema.getListaDeLivros().size() > 0) {
						JOptionPane.showMessageDialog(null, "Os gêneros literários cadastrados na biblioteca são: ");
						sistema.exibeTodosOsGêneros();
					} else {
						JOptionPane.showMessageDialog(null,
								"Ainda não há gêneros cadastrados na biblioteca! Tente cadastrar algum!");
					}
					break;
				case "4":
					String opcaoMenuExibicaoDeAutores = JOptionPane.showInputDialog(
							"1.Exibir todos os autores\n2.Exibir autores masculinos\n3.Exibir autores femininos\n4.Exibir autores de outros gêneros\n"
									+ "5.Quantidade de livros por autores masculinos\n6.Quantidade de livros por autores femininos\n7.Quantidade de livros por autores de outros gêneros\n"
									+ "8.Quantidade total de páginas");
					switch (opcaoMenuExibicaoDeAutores) {
					case "1":
						if (sistema.getListaDeLivros().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os autores cadastrados na biblioteca são: ");
							sistema.exibeListaDeAutores(sistema.listaDeAutores());
						} else {
							JOptionPane.showMessageDialog(null,
									"Ainda não há autores cadastrados na biblioteca! Tente cadastrar algum!");
						}
						break;
					case "2":
						if (sistema.listaDeAutoresMasculinos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os autores do sexo masculino são: ");
							sistema.exibeListaDeAutores(sistema.listaDeAutoresMasculinos());
						} else {
							JOptionPane.showMessageDialog(null, "Ainda não há autores do sexo masculino cadastrados!");
						}
						break;
					case "3":
						if (sistema.listaDeAutoresFemininos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os autores do sexo feminino são: ");
							sistema.exibeListaDeAutores(sistema.listaDeAutoresFemininos());
						} else {
							JOptionPane.showMessageDialog(null, "Ainda não há autores do sexo feminino cadastrados!");
						}
						break;
					case "4":
						if (sistema.listaDeAutoresDeOutrosSexos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os autores de outros sexos são: ");
							sistema.exibeListaDeAutores(sistema.listaDeAutoresDeOutrosSexos());
						} else {
							JOptionPane.showMessageDialog(null,
									"Não há autores cadastrados com sexo diferente de masculino e feminino!");
						}
						break;
					case "5":
						if (sistema.livrosEncontradosPorSexoDeAutor("Masculino").size() > 0) {
							JOptionPane.showMessageDialog(null, "A quantidade de livros escritos por autores masculinos é: " + sistema.livrosEncontradosPorSexoDeAutor("Masculino").size());
						} else {
							JOptionPane.showMessageDialog(null, "Ainda não há livros escritos por autores masculinos!");
						}
						break;
					case "6":
						if (sistema.livrosEncontradosPorSexoDeAutor("Feminino").size() > 0) {
							JOptionPane.showMessageDialog(null, "A quantidade de livros escritos por autores femininos é: " + sistema.livrosEncontradosPorSexoDeAutor("Feminino").size());
						} else {
							JOptionPane.showMessageDialog(null, "Ainda não há livros escritos por autores femininos!");
						}
						break;
					case "7":
						if (sistema.livrosEncontradosPorSexoDiferenteDeAutor().size() > 0) {
							JOptionPane.showMessageDialog(null, "A quantidade de livros escritos por autores não masculinos ou femininos é: " + sistema.livrosEncontradosPorSexoDiferenteDeAutor().size());
						} else {
							JOptionPane.showMessageDialog(null, "Ainda não há livros escritos por autores de sexo não masculino ou feminino!");
						}
						break;
					case "8":
						JOptionPane.showMessageDialog(null, "A quantidade total de páginas na biblioteca é: " + sistema.contadorDeTodasAsPaginas());
						break;
					default:
						JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
						break;
					}
					break;
				case "5":
					if (sistema.getListaDeLivros().size() > 0) {
						JOptionPane.showMessageDialog(null,
								"Os livros, ordenados por gênero e autores alfabeticamente, são: ");
						sistema.exibeListaDeLivros(sistema.organizaLivrosPorGeneroEAutoresAlfabeticamente());
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
				}
				break;
			case "2":
				String tituloDoLivro = JOptionPane.showInputDialog("Informe o título do livro: ");
				String nomeDoAutor = JOptionPane.showInputDialog("Informe o nome do autor(es) do livro: ");
				String sexoDoAutor = JOptionPane.showInputDialog("Digite o sexo do autor(es) do livro: ");
				String paisDoAutor = "";
				String sabeANacionalidade = JOptionPane
						.showInputDialog("Você sabe a nacionalidade do autor(es):\n1.Sim\n2.Não");
				if (sabeANacionalidade.equals("1")) {
					paisDoAutor = JOptionPane.showInputDialog("Informe o país de nascimento do autor: ");
				} else if (sabeANacionalidade.equals("2")) {
					paisDoAutor = "Desconhecido";
				} else {
					JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
				}
				String generoDoLivro = JOptionPane.showInputDialog("Informe o gênero do livro: ");
				int quantidadeDePaginas = 0;
				boolean quantidadeDePaginasENumerico = false;
				while (quantidadeDePaginasENumerico == false) {
					try {
						String quantidadeDePaginasString = JOptionPane
								.showInputDialog("Digite a quantidade de páginas do livro:");
						quantidadeDePaginas = Integer.parseInt(quantidadeDePaginasString);
						quantidadeDePaginasENumerico = true;
					} catch (NumberFormatException excecao) {
						JOptionPane.showMessageDialog(null,
								"Digite apenas valores numéricos para a quantidade de páginas.");
					}
				}
				String livroLido = JOptionPane.showInputDialog("Você já leu o livro?\n1.Sim\n2.Não");
				int anoDeLeitura = 0;
				if (livroLido.equals("1")) {
					livroLido = "Sim";
					boolean anoDeLeituraENumerico = false;
					while (anoDeLeituraENumerico == false) {
						try {
							String anoDeLeituraString = JOptionPane.showInputDialog("Digite o ano da leitura: ");
							anoDeLeitura = Integer.parseInt(anoDeLeituraString);
							anoDeLeituraENumerico = true;
						} catch (NumberFormatException excecao) {
							JOptionPane.showMessageDialog(null,
									"Digite apenas valores numéricos para o ano de leitura");
						}
					}
				} else if (livroLido.equals("2")) {
					livroLido = "Não";
				} else {
					JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
				}
				Autor autor = new Autor(nomeDoAutor, sexoDoAutor, paisDoAutor);
				Livro livro = new Livro(tituloDoLivro, autor, generoDoLivro, quantidadeDePaginas, livroLido,
						anoDeLeitura);
				sistema.cadastrarLivroNaLista(livro);
				JOptionPane.showMessageDialog(null, livro.getTitulo());
				try {
					gravador.guardaLivros(sistema.getListaDeLivros());
					JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "3":
				String opcaoMenuDeBusca = JOptionPane
						.showInputDialog("1.Buscar Livro\n2.Buscar Autor\n3.Buscar por Data");
				switch (opcaoMenuDeBusca) {
				case "1":
					String opcaoMenuDeBuscaPorLivro = JOptionPane.showInputDialog(
							"1.Buscar por Título do Livro\n2.Buscar por Gênero do Livro\n3.Buscar por Quantidade de Páginas");
					if (opcaoMenuDeBuscaPorLivro.equals("1")) {
						String tituloDoLivroParaBusca = JOptionPane
								.showInputDialog("Informe o título do livro para busca: ");
						try {
							if (sistema.localizaLivroNaLista(tituloDoLivroParaBusca) != null) {
								JOptionPane.showMessageDialog(null,
										sistema.localizaLivroNaLista(tituloDoLivroParaBusca).toString());
							} else {
								JOptionPane.showMessageDialog(null,
										"O livro " + tituloDoLivroParaBusca + " não foi encontrado!");
							}
						} catch (HeadlessException e) {
							e.printStackTrace();
						} catch (LivroNaoExisteException e) {
							e.printStackTrace();
						}
					} else if (opcaoMenuDeBuscaPorLivro.equals("2")) {
						String generoParaBusca = JOptionPane.showInputDialog("Informe o gênero para busca: ");
						if (sistema.livrosEncontradosPorGenero(generoParaBusca).size() > 0) {
							JOptionPane.showMessageDialog(null, "Os livros do gênero " + generoParaBusca + " são: ");
							sistema.exibeListaDeLivros(sistema.livrosEncontradosPorGenero(generoParaBusca));
						} else {
							JOptionPane.showMessageDialog(null,
									"Não há livros do gênero " + generoParaBusca + " cadastrados na biblioteca!");
						}
					} else if (opcaoMenuDeBuscaPorLivro.equals("3")) {
						String opcaoMenuDeBuscaPorQuantidadeDePaginas = JOptionPane.showInputDialog(
								"1.Buscar livros com a quantidade de páginas maior ou igual ao informado\n"
										+ "2.Buscar livros com a quantidade de páginas menor ou igual ao informado");
						if (opcaoMenuDeBuscaPorQuantidadeDePaginas.equals("1")
								|| opcaoMenuDeBuscaPorQuantidadeDePaginas.equals("2")) {
							int quantidadeDePaginasParaBusca = 0;
							quantidadeDePaginasENumerico = false;
							while (quantidadeDePaginasENumerico == false) {
								try {
									String quantidadeDePaginasString = JOptionPane
											.showInputDialog("Digite a quantidade de páginas para busca:");
									quantidadeDePaginasParaBusca = Integer.parseInt(quantidadeDePaginasString);
									quantidadeDePaginasENumerico = true;
								} catch (NumberFormatException excecao) {
									JOptionPane.showMessageDialog(null,
											"Digite apenas valores numéricos para a quantidade de páginas.");
								}
							}
							if (opcaoMenuDeBuscaPorQuantidadeDePaginas.equals("1")) {
								if (sistema.livrosEncontradosPorQuantidadeDePaginas(quantidadeDePaginasParaBusca, 1)
										.size() > 0) {
									JOptionPane.showMessageDialog(null,
											"Os livros com ou mais de " + quantidadeDePaginasParaBusca + " são: ");
									sistema.exibeListaDeLivros(sistema
											.livrosEncontradosPorQuantidadeDePaginas(quantidadeDePaginasParaBusca, 1));
								} else {
									JOptionPane.showMessageDialog(null, "Não há livros com mais de "
											+ quantidadeDePaginasParaBusca + " páginas na biblioteca!");
								}
							} else if (opcaoMenuDeBuscaPorQuantidadeDePaginas.equals("2")) {
								if (sistema.livrosEncontradosPorQuantidadeDePaginas(quantidadeDePaginasParaBusca, 2)
										.size() > 0) {
									JOptionPane.showMessageDialog(null,
											"Os livros com ou menos de " + quantidadeDePaginasParaBusca + " são: ");
									sistema.exibeListaDeLivros(sistema
											.livrosEncontradosPorQuantidadeDePaginas(quantidadeDePaginasParaBusca, 2));
								} else {
									JOptionPane.showMessageDialog(null, "Não há livros com menos de "
											+ quantidadeDePaginasParaBusca + " páginas na biblioteca!");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
						}
						break;
					}
					break;
				case "2":
					String opcaoMenuDeBuscaPorAutor = JOptionPane
							.showInputDialog("1.Livros de Autores Masculinos\n2.Livros de Autores Femininos\n"
									+ "3.Busca por Nome do Autor\n4.Busca por Nacionalidade do Autor");
					if (opcaoMenuDeBuscaPorAutor.equals("1")) {
						if (sistema.listaDeAutoresMasculinos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os livros escritos por autores masculinos são: ");
							sistema.exibeListaDeLivros(sistema.livrosEncontradosPorSexoDeAutor("Masculino"));
						} else {
							JOptionPane.showMessageDialog(null, "Não há livros escritos por autores masculinos!");
						}
					} else if (opcaoMenuDeBuscaPorAutor.equals("2")) {
						if (sistema.listaDeAutoresFemininos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os livros escritos por autores femininos são: ");
							sistema.exibeListaDeLivros(sistema.livrosEncontradosPorSexoDeAutor("Feminino"));
						} else {
							JOptionPane.showMessageDialog(null, "Não há livros escritos por autores femininos!");
						}
					} else if (opcaoMenuDeBuscaPorAutor.equals("3")) {
						String nomeDoAutorParaBusca = JOptionPane
								.showInputDialog("Informe o nome do autor para busca: ");
						if (sistema.livrosEncontradosPorNomeDeAutor(nomeDoAutorParaBusca).size() > 0) {
							JOptionPane.showMessageDialog(null,
									"Os livros escritos por " + nomeDoAutorParaBusca + " são: ");
							sistema.exibeListaDeLivros(sistema.livrosEncontradosPorNomeDeAutor(nomeDoAutorParaBusca));
						} else {
							JOptionPane.showMessageDialog(null,
									"Não foram encontrados livros escritos por " + nomeDoAutorParaBusca);
						}
					} else if (opcaoMenuDeBuscaPorAutor.equals("4")) {
						String nacionalidadeDoAutorParaBusca = JOptionPane
								.showInputDialog("Informe o país para busca: ");
						if (sistema.livrosEncontradosPorPaisesDeAutor(nacionalidadeDoAutorParaBusca).size() > 0) {
							JOptionPane.showMessageDialog(null, "Os livros escritos por autores do país "
									+ nacionalidadeDoAutorParaBusca + " são: ");
							sistema.exibeListaDeLivros(
									sistema.livrosEncontradosPorPaisesDeAutor(nacionalidadeDoAutorParaBusca));
						} else {
							JOptionPane.showMessageDialog(null,
									"Não há livros escritos por autores do país " + nacionalidadeDoAutorParaBusca);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
					}
					break;
				case "3":
					String opcaoMenuDeBuscaPorData = JOptionPane.showInputDialog(
							"1.Buscar livros lidos em um ano informado\n2.Buscar livros que não foram lidos");
					if (opcaoMenuDeBuscaPorData.equals("1")) {
						boolean anoDeLeituraENumerico = false;
						int anoDeLeituraParaBusca = 0;
						while (anoDeLeituraENumerico == false) {
							try {
								String anoDeLeituraString = JOptionPane.showInputDialog("Digite o ano da leitura: ");
								anoDeLeituraParaBusca = Integer.parseInt(anoDeLeituraString);
								anoDeLeituraENumerico = true;
							} catch (NumberFormatException excecao) {
								JOptionPane.showMessageDialog(null,
										"Digite apenas valores numéricos para o ano de leitura");
							}
						}
						if (sistema.livrosEncontradosPorAnoDeLeitura(anoDeLeituraParaBusca).size() > 0) {
							JOptionPane.showMessageDialog(null,
									"Os livros lidos em " + anoDeLeituraParaBusca + " são: ");
							sistema.exibeListaDeLivros(sistema.livrosEncontradosPorAnoDeLeitura(anoDeLeituraParaBusca));
						} else {
							JOptionPane.showMessageDialog(null, "Não houve livros lidos em " + anoDeLeituraParaBusca);
						}
					} else if (opcaoMenuDeBuscaPorData.equals("2")) {
						if (sistema.livrosNaoLidos().size() > 0) {
							JOptionPane.showMessageDialog(null, "Os livros que ainda não foram lidos são: ");
							sistema.exibeListaDeLivros(sistema.livrosNaoLidos());
						} else {
							JOptionPane.showMessageDialog(null,
									"Não há livros não lidos! Parabéns! Você é um ávido leitor!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis!");
					break;
				}
				break;
			case "4":
				continuar = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Digite apenas as opções disponíveis no menu principal!");
				break;
			}
		}

	}
}