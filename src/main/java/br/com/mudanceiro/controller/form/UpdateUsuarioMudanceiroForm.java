package br.com.mudanceiro.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.mudanceiro.model.TipoServico;

public class UpdateUsuarioMudanceiroForm {
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotEmpty(message = "{campo.email.obrigatorio}")
	private String email;

	
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone;

	@NotNull(message = "{campo.tipoServico.obrigatorio}")
	private TipoServico tipoServico;
	

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

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
}
