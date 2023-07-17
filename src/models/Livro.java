package models;

import java.util.Objects;

public class Livro {

	private String titulo;
	private Autor autor;
	private String genero;
	private int quantidadeDePaginas;
	private String livroLido;
	private int anoDeLeitura;
	
	public Livro(String titulo, Autor autor, String genero, int quantidadeDePaginas, String livroLido, int anoDeLeitura) {
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.quantidadeDePaginas = quantidadeDePaginas;
		this.livroLido = livroLido;
		this.anoDeLeitura = anoDeLeitura;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return this.autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getQuantidadeDePaginas() {
		return this.quantidadeDePaginas;
	}

	public void setQuantidadeDePaginas(int quantidadeDePaginas) {
		this.quantidadeDePaginas = quantidadeDePaginas;
	}
	

	public String getLivroLido() {
		return this.livroLido;
	}

	public void setLivroLido(String livroLido) {
		this.livroLido = livroLido;
	}

	
	public int getAnoDeLeitura() {
		return anoDeLeitura;
	}

	public void setAnoDeLeitura(int anoDeLeitura) {
		this.anoDeLeitura = anoDeLeitura;
	}

	@Override
	public String toString() {
		return "O título do Livro é: " + this.titulo + "\nO autor do livro é: " + this.autor.getNome() + "\nO gênero é: " + this.genero + "\nA quantidade de paginas é: "
				+ this.quantidadeDePaginas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, genero, quantidadeDePaginas, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(genero, other.genero)
				&& quantidadeDePaginas == other.quantidadeDePaginas && Objects.equals(titulo, other.titulo);
	}

}
