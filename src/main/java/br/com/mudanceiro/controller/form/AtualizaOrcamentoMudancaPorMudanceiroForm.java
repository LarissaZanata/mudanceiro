package br.com.mudanceiro.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import br.com.mudanceiro.model.StatusMudanca;

public class AtualizaOrcamentoMudancaPorMudanceiroForm {

	@NotEmpty(message = "{campo.valorOrcamento.obrigatorio}")
	private BigDecimal valorOrcamento;

	public BigDecimal getValorOrcamento() {
		return valorOrcamento;
	}

	public void setValorOrcamento(BigDecimal valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}
	
	
}
