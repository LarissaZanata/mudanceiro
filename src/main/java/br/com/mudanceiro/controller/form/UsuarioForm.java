package br.com.mudanceiro.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.mudanceiro.model.TipoServico;

public class UsuarioForm {
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{campo.email.obrigatorio}")
	private String email;
	
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone;
	
	private boolean mudanceiro = false;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public boolean isMudanceiro() {
		return mudanceiro;
	}
	public void setMudanceiro(boolean mudanceiro) {
		this.mudanceiro = mudanceiro;
	}
}
