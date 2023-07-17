package models;

import java.io.*;
import java.util.*;

public class GravadorDeDados {

	public void GravaDadosNoArquivo(List<String> dados, String nomeArquivo) throws IOException {
		PrintWriter gravador = null;
		try {
			gravador = new PrintWriter(nomeArquivo);
			for (String string : dados) {
				gravador.println(string);
			}
		} catch (IOException excecao) {
			System.out.println("Erro ao gravar dados no arquivo: " + excecao.getMessage());
		} finally {
			gravador.close();
		}
	}

	public List<String> RecuperaDadosDoArquivo(String nomeArquivo) throws IOException {
		List<String> textoLido = new ArrayList<>();
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String texto = null;
			do {
				texto = leitor.readLine();
				if (texto != null) {
					textoLido.add(texto);
				}
			} while (texto != null);
		} catch (IOException e) {
			System.out.println("Erro ao recuperar dados do arquivo: ");
		} finally {
			if (leitor != null) {
				leitor.close();
			}
		}
		return textoLido;
	}
}
