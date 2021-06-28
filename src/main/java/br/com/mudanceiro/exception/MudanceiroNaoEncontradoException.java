package br.com.mudanceiro.exception;

public class MudanceiroNaoEncontradoException extends RuntimeException{

	public MudanceiroNaoEncontradoException() {
		super("Mudanceiro não encontrado.");
	}
	
	public MudanceiroNaoEncontradoException(Long id) {
		super("Mudanceiro não encontrado para o id: " + id);
	}
}
