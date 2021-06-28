package br.com.mudanceiro.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

	public UsuarioNaoEncontradoException(Long id) {
		super("Usuario não encontrado para o Id: " + id);
	}
}
