package models;

import java.util.*;

public interface SistemaBibliotecaInterface {

	List<Livro> getListaDeLivros();
	
	void setListaDeLivros(List<Livro> listaDeLivros);
	
	List<Livro> carregaTodosOsLivros();
	
	boolean cadastrarLivroNaLista(Livro livro);

	boolean removerLivroDaLista(String titulo);

	Livro localizaLivroNaLista(String tituloParaBusca) throws LivroNaoExisteException;
	
	List<Livro> organizaLivrosAlfabeticamente();

	List<Livro> organizaLivrosPorAutoresAlfabeticamente();

	List<Livro> organizaLivrosPorGeneroAlfabeticamente();
	
	List<Livro> organizaLivrosPorGeneroEAutoresAlfabeticamente();
	
	List<Livro> livrosEncontradosPorNomeDeAutor(String autorParaPesquisa);
	
	List<Livro> livrosEncontradosPorSexoDeAutor(String sexoParaPesquisa);
	
	List<Livro> livrosEncontradosPorSexoDiferenteDeAutor();
	
	List<Livro> livrosEncontradosPorPaisesDeAutor(String paisParaPesquisa);
	
	List<Livro> livrosEncontradosPorGenero(String generoParaPesquisa);
	
	List<Livro> livrosEncontradosPorQuantidadeDePaginas(int quantidadeDePaginas, int tipoDeBusca);
	
	List<Livro> livrosEncontradosPorAnoDeLeitura(int anoDeLeitura);
	
	List<Livro> livrosNaoLidos();
	
	List<Autor> listaDeAutores();
	
	List<Autor> listaDeAutoresMasculinos();
	
	List<Autor> listaDeAutoresFemininos();
	
	List<Autor> listaDeAutoresDeOutrosSexos();
	
	List<String> listaDeGeneros();
	
	int contadorDeTodasAsPaginas();
	
	void exibeTodosOsLivros();
	
	void exibeListaDeLivros(List<Livro> listaDeLivros);
	
	void exibeTodosOsAutores();
	
	void exibeListaDeAutores(List<Autor> listaDeAutores);
	
	void exibeTodosOsGêneros();

}
