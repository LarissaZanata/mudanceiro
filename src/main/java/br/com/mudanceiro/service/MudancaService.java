package br.com.mudanceiro.service;

import java.util.Optional;

import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;

public interface MudancaService {

	Mudanca salvar(MudancaForm form);
	Mudanca obterMudancaCompleta(Long id);
}
