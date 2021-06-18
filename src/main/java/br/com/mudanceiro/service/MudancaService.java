package br.com.mudanceiro.service;

import java.util.List;
import java.util.Optional;

import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;

public interface MudancaService {

	Mudanca salvar(MudancaForm form);
	Mudanca obterMudancaCompleta(Long id);
	void atualizaOrcamento(Long id, AtualizaOrcamentoMudancaPorMudanceiroForm form); 
	void atualizaStatusMudanca(Long id, AtualizaStatusMudancaPorClienteForm form);
	List<Mudanca> buscaMudancasPendentes();
}
