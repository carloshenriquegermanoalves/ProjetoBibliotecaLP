package models;

import java.util.Objects;

public class Autor {

	private String nome;
	private String sexo;
	private String paisDeNascenca;

	public Autor(String nome, String sexo, String paisDeNascenca) {
		this.nome = nome;
		this.sexo = sexo;
		this.paisDeNascenca = paisDeNascenca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPaisDeNascenca() {
		return paisDeNascenca;
	}

	public void setPaisDeNascenca(String paisDeNascenca) {
		this.paisDeNascenca = paisDeNascenca;
	}

	@Override
	public String toString() {
		return "O nome do autor é: " + nome + "\nO sexo do autor é: " + sexo + "\nO país de nascença do autor é: "
				+ paisDeNascenca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, paisDeNascenca, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(paisDeNascenca, other.paisDeNascenca)
				&& Objects.equals(sexo, other.sexo);
	}
	
}
