package br.com.mudanceiro.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mudanceiro.controller.dto.MudancaDto;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.StatusMudanca;

public interface MudancaService {

	public Page<MudancaDto> lista(StatusMudanca statusMudanca, Pageable paginacao);
	public ResponseEntity<MudancaDto> cadastrar(MudancaForm form, UriComponentsBuilder uriBuilder);
	public ResponseEntity<MudancaDto> atualizar(Long id, MudancaForm form);
	public ResponseEntity<MudancaDto> alterarStatusMudanca(Long id, MudancaForm form);
}
