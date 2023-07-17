package models;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class GravadorDeLivros {

	private String arquivoDeLivros = "Books.txt";
	private GravadorDeDados gravador;

	public GravadorDeLivros() {
		this.gravador = new GravadorDeDados();
	}

	public void guardaLivros(List<Livro> listaDeLivros) throws IOException {
		List<String> textoAGravar = new ArrayList<>();
		for (Livro livro : listaDeLivros) {
			String linha = livro.getTitulo() + "###" + livro.getAutor().getNome() + "###" + livro.getAutor().getSexo()
					+ "###" + livro.getAutor().getPaisDeNascenca() + "###" + livro.getGenero() + "###"
					+ livro.getQuantidadeDePaginas() + "###" + livro.getLivroLido() + "###" + livro.getAnoDeLeitura();
			textoAGravar.add(linha);
		}
		this.gravador.GravaDadosNoArquivo(textoAGravar, arquivoDeLivros);
	}

	public List<Livro> recuperaLivros() throws IOException {
		List<String> dadosDosLivros = this.gravador.RecuperaDadosDoArquivo(this.arquivoDeLivros);
		List<Livro> listaDeLivros = new ArrayList<>();
		for (String string : dadosDosLivros) {
			String[] dados = string.split("###");
			Autor autores = new Autor(dados[1], dados[2], dados[3]);
			Livro livro = new Livro(dados[0], autores, dados[4], Integer.parseInt(dados[5]), dados[6], Integer.parseInt(dados[7]));
			listaDeLivros.add(livro);
		}
		return listaDeLivros;
	}
}
