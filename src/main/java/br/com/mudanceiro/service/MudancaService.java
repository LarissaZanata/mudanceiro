package br.com.mudanceiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;

public interface MudancaService {

	Mudanca salvar(Long idUsuario, Mudanca mudanca);
	Mudanca obterMudancaCompleta(Long id);
	void atualizaOrcamento(Long id, Long idMudanceiro, AtualizaOrcamentoMudancaPorMudanceiroForm form); 
	void atualizaStatusMudanca(Long id, AtualizaStatusMudancaPorClienteForm form);
	List<Mudanca> buscaMudancasPendentes();
	List<Mudanca> buscaMudancasPorIdMudanceiro(Long idMudanceiro);
	Mudanca salvar(String dto, List<MultipartFile> file);
}
