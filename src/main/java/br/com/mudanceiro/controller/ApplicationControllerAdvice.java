package br.com.mudanceiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.mudanceiro.exception.MudancaNaoEncontradaException;
import br.com.mudanceiro.exception.MudanceiroNaoEncontradoException;
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.exception.UsuarioNaoEncontradoException;
import br.com.mudanceiro.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
		String mensagem = ex.getMessage();
		return new ApiErrors(mensagem);
	}
	
	@ExceptionHandler(MudancaNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleMudancaNotFound(MudancaNaoEncontradaException ex) {
		String mensagem = ex.getMessage();
		return new ApiErrors(mensagem);
	}
	
	@ExceptionHandler(MudanceiroNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleMudanceiroNotFound(MudanceiroNaoEncontradoException ex) {
		String mensagem = ex.getMessage();
		return new ApiErrors(mensagem);
	}
	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handleUsuarioNotFounf(UsuarioNaoEncontradoException ex) {
		String mensagem = ex.getMessage();
		return new ApiErrors(mensagem);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getAllErrors().stream()
											.map(erro -> erro.getDefaultMessage())
											.collect(Collectors.toList());
		return new ApiErrors(erros);
	}
	
}
