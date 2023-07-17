package models;

import java.io.IOException;

public class LivroNaoExisteException extends IOException {
	public LivroNaoExisteException(String mensagem) {
		super(mensagem);
	}
}
