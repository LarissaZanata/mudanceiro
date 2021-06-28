package br.com.mudanceiro.exception;

public class MudancaNaoEncontradaException extends RuntimeException{
	
	public MudancaNaoEncontradaException() {
		super("Mudança não encontrada.");
	}
}
