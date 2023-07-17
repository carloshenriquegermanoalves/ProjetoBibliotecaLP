package models;

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class SistemaBiblioteca implements SistemaBibliotecaInterface {

	private List<Livro> listaDeLivros;
	private GravadorDeLivros gravador;

	public SistemaBiblioteca() {
		this.listaDeLivros = new ArrayList<>();
		this.gravador = new GravadorDeLivros();
	}

	@Override
	public List<Livro> getListaDeLivros() {
		return this.listaDeLivros;
	}
	
	public void setListaDeLivros(List<Livro> listaDeLivros) {
		this.listaDeLivros = listaDeLivros;
	}
	
	@Override
	public List<Livro> carregaTodosOsLivros() {
		try {
			this.listaDeLivros = gravador.recuperaLivros();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.listaDeLivros;
	}
	
	@Override
	public Livro localizaLivroNaLista(String tituloParaBusca) throws LivroNaoExisteException {
		Livro livroEncontrado = null;
		for (Livro livro : listaDeLivros) {
			if (livro.getTitulo().equalsIgnoreCase(tituloParaBusca)) {
				livroEncontrado = livro;
				return livroEncontrado;
			}
		}
		return livroEncontrado;
	}
	
	@Override
	public boolean cadastrarLivroNaLista(Livro livro) {
		if (this.listaDeLivros.add(livro)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removerLivroDaLista(String titulo) {
		for (Livro livro : listaDeLivros) {
			if (titulo.equalsIgnoreCase(livro.getTitulo())) {
				if (this.listaDeLivros.remove(livro)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Livro> organizaLivrosAlfabeticamente() {
		List<Livro> listaOrdenada = new ArrayList<>(listaDeLivros);
		Collections.sort(listaOrdenada, new Comparator<Livro>() {
			@Override
			public int compare(Livro livro1, Livro livro2) {
				return livro1.getTitulo().compareToIgnoreCase(livro2.getTitulo());
			}
		});
		return listaOrdenada;
	}

	@Override
	public List<Livro> organizaLivrosPorAutoresAlfabeticamente() {
		List<Livro> listaOrdenada = new ArrayList<>(listaDeLivros);
		Collections.sort(listaOrdenada, new Comparator<Livro>() {
			@Override
			public int compare(Livro livro1, Livro livro2) {
				return livro1.getAutor().getNome().compareToIgnoreCase(livro2.getAutor().getNome());
			}
		});
		return listaOrdenada;
	}

	@Override
	public List<Livro> organizaLivrosPorGeneroAlfabeticamente() {
		List<Livro> listaOrdenada = new ArrayList<>(listaDeLivros);
		Collections.sort(listaOrdenada, new Comparator<Livro>() {
			@Override
			public int compare(Livro livro1, Livro livro2) {
				return livro1.getGenero().compareToIgnoreCase(livro2.getGenero());
			}
		});
		return listaOrdenada;
	}

	@Override
	public List<Livro> organizaLivrosPorGeneroEAutoresAlfabeticamente() {
	    // Organizar os livros por gênero
	    Collections.sort(listaDeLivros, Comparator.comparing(Livro::getGenero));

	    // Criar uma nova lista para armazenar os livros organizados corretamente
	    List<Livro> listaDeLivrosOrganizados = new ArrayList<>();

	    // Organizar os livros por autor dentro de cada gênero
	    for (String genero : listaDeGeneros()) {
	        List<Livro> livrosPorGenero = new ArrayList<>();
	        for (Livro livro : listaDeLivros) {
	            if (livro.getGenero().equals(genero)) {
	                livrosPorGenero.add(livro);
	            }
	        }
	        Collections.sort(livrosPorGenero, Comparator.comparing(livro -> livro.getAutor().getNome()));

	        // Organizar os livros por título dentro de cada autor
	        for (Autor autor : listaDeAutores()) {
	            List<Livro> livrosPorAutor = new ArrayList<>();
	            for (Livro livro : livrosPorGenero) {
	                if (livro.getAutor().equals(autor)) {
	                    livrosPorAutor.add(livro);
	                }
	            }
	            Collections.sort(livrosPorAutor, Comparator.comparing(Livro::getTitulo));

	            // Adicionar os livros organizados à lista principal
	            listaDeLivrosOrganizados.addAll(livrosPorAutor);
	        }
	    }

	    return listaDeLivrosOrganizados;
	}


	@Override
	public List<Livro> livrosEncontradosPorNomeDeAutor(String nomeParaPesquisa) {
		List<Livro> livrosDoAutorPesquisado = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getAutor().getNome().equalsIgnoreCase(nomeParaPesquisa)) {
				livrosDoAutorPesquisado.add(livro);
			}
		}
		return livrosDoAutorPesquisado;
	}

	@Override
	public List<Livro> livrosEncontradosPorSexoDeAutor(String sexoParaPesquisa) {
		List<Livro> livrosDoSexoPesquisado = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getAutor().getSexo().equalsIgnoreCase(sexoParaPesquisa)) {
				livrosDoSexoPesquisado.add(livro);
			}
		}
		return livrosDoSexoPesquisado;
	}

	@Override
	public List<Livro> livrosEncontradosPorSexoDiferenteDeAutor() {
		List<Livro> livrosDeDiferentesSexos = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (!livro.getAutor().getSexo().equalsIgnoreCase("Masculino") || !livro.getAutor().getSexo().equalsIgnoreCase("Feminino")) {
				livrosDeDiferentesSexos.add(livro);
			}
		}
		return livrosDeDiferentesSexos;
	}
	
	@Override
	public List<Livro> livrosEncontradosPorPaisesDeAutor(String paisParaPesquisa) {
		List<Livro> livrosDoPaisPesquisado = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getAutor().getPaisDeNascenca().equalsIgnoreCase(paisParaPesquisa)) {
				livrosDoPaisPesquisado.add(livro);
			}
		}
		return livrosDoPaisPesquisado;
	}

	@Override
	public List<Livro> livrosEncontradosPorGenero(String generoParaPesquisa) {
		List<Livro> livrosDoGeneroPesquisado = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getGenero().equalsIgnoreCase(generoParaPesquisa)) {
				livrosDoGeneroPesquisado.add(livro);
			}
		}
		return livrosDoGeneroPesquisado;
	}

	@Override
	public List<Livro> livrosEncontradosPorQuantidadeDePaginas(int quantidadeDePaginas, int tipoDeBusca) {
		if (tipoDeBusca == 1) {
			List<Livro> livrosComMaisPaginasDoQueOInformado = new ArrayList<>();
			for (Livro livro : listaDeLivros) {
				if (livro.getQuantidadeDePaginas() > quantidadeDePaginas) {
					livrosComMaisPaginasDoQueOInformado.add(livro);
				}
			}
			return livrosComMaisPaginasDoQueOInformado;
		} else { // Os possíveis resultados para o tipo de busca serão sempre 1 ou 2 conforme é
					// estabelecido na classe SistemaPrincipal
			List<Livro> livrosComMenosPaginasDoQueOInformado = new ArrayList<>();
			for (Livro livro : listaDeLivros) {
				if (livro.getQuantidadeDePaginas() < quantidadeDePaginas) {
					livrosComMenosPaginasDoQueOInformado.add(livro);
				}
			}
			return livrosComMenosPaginasDoQueOInformado;
		}
	}
	
	@Override
	public List<Livro> livrosEncontradosPorAnoDeLeitura(int anoDeLeitura) {
		List<Livro> livrosEncontradosPorAnoDeLeitura = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getAnoDeLeitura() == anoDeLeitura) {
				livrosEncontradosPorAnoDeLeitura.add(livro);
			}
		}
		return livrosEncontradosPorAnoDeLeitura;
	}
	
	@Override
	public List<Livro> livrosNaoLidos() {
		List<Livro> livrosNãoLidos = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (livro.getLivroLido().equalsIgnoreCase("Não")) {
				livrosNãoLidos.add(livro);
			}
		}
		return livrosNãoLidos;
	}

	@Override
	public List<Autor> listaDeAutores() {
		List<Autor> listaDeAutores = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (!listaDeAutores.contains(livro.getAutor())) {
				listaDeAutores.add(livro.getAutor());
			}
		}
		return listaDeAutores;
	}

	@Override
	public List<Autor> listaDeAutoresFemininos() {
		List<Autor> autoresFemininos = new ArrayList<>();
		List<Autor> todosOsAutores = listaDeAutores();
		for (Autor autor : todosOsAutores) {
			if (autor.getSexo().equalsIgnoreCase("feminino")) {
				if (!autoresFemininos.contains(autor)) {
					autoresFemininos.add(autor);
				}
			}
		}
		return autoresFemininos;
	}

	@Override
	public List<Autor> listaDeAutoresMasculinos() {
		List<Autor> autoresMasculinos = new ArrayList<>();
		List<Autor> todosOsAutores = listaDeAutores();
		for (Autor autor : todosOsAutores) {
			if (autor.getSexo().equalsIgnoreCase("masculino")) {
				if (!autoresMasculinos.contains(autor)) {
					autoresMasculinos.add(autor);
				}
			}
		}
		return autoresMasculinos;
	}

	@Override
	public List<Autor> listaDeAutoresDeOutrosSexos() {
		List<Autor> autoresDeOutrosSexos = new ArrayList<>();
		List<Autor> todosOsAutores = listaDeAutores();
		for (Autor autor : todosOsAutores) {
			if (!autor.getSexo().equalsIgnoreCase("masculino") && !autor.getSexo().equalsIgnoreCase("feminino")) {
				if (!autoresDeOutrosSexos.contains(autor)) {
					autoresDeOutrosSexos.add(autor);
				}
			}
		}
		return autoresDeOutrosSexos;
	}

	@Override
	public List<String> listaDeGeneros() {
		List<String> listaDeGeneros = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			if (!listaDeGeneros.contains(livro.getGenero())) {
				listaDeGeneros.add(livro.getGenero());
			}
		}
		return listaDeGeneros;
	}

	@Override
	public int contadorDeTodasAsPaginas() {
		int quantidadeTotalDePaginas = 0;
		for (Livro livro : listaDeLivros) {
			quantidadeTotalDePaginas += livro.getQuantidadeDePaginas();
		}
		return quantidadeTotalDePaginas;
	}

	@Override
	public void exibeTodosOsLivros() {
		for (Livro livro : getListaDeLivros()) {
			JOptionPane.showMessageDialog(null, livro.getTitulo());
		}
	}

	@Override
	public void exibeListaDeLivros(List<Livro> listaDeLivrosAExibir) {
		for (Livro livro : listaDeLivrosAExibir) {
			JOptionPane.showMessageDialog(null, livro.getTitulo());
		}
	}

	@Override
	public void exibeTodosOsAutores() {
		List<Autor> todosOsAutores = listaDeAutores();
		for (Autor autor : todosOsAutores) {
			JOptionPane.showMessageDialog(null, autor.getNome());
		}
	}

	@Override
	public void exibeListaDeAutores(List<Autor> listaDeAutores) {
		for (Autor autor : listaDeAutores) {
			JOptionPane.showMessageDialog(null, autor.getNome());
		}
	}

	@Override
	public void exibeTodosOsGêneros() {
		List<String> todosOsGeneros = listaDeGeneros();
		for (String genero : todosOsGeneros) {
			JOptionPane.showMessageDialog(null, genero);
		}
	}

}