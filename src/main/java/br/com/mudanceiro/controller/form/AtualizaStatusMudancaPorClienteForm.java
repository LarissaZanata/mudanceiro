package br.com.mudanceiro.controller.form;

import javax.validation.constraints.NotEmpty;

import br.com.mudanceiro.model.StatusMudanca;

public class AtualizaStatusMudancaPorClienteForm {
	
	@NotEmpty(message = "{campo.statusMudanca.obrigatorio}")
	private StatusMudanca statusMudanca;

	public StatusMudanca getStatusMudanca() {
		return statusMudanca;
	}

	public void setStatusMudanca(StatusMudanca statusMudanca) {
		this.statusMudanca = statusMudanca;
	}
	
	
}
