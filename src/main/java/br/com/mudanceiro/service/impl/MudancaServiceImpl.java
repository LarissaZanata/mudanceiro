package br.com.mudanceiro.service.impl;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mudanceiro.controller.dto.MudancaDto;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.repository.MudancaRepository;
import br.com.mudanceiro.service.MudancaService;

@Service
public class MudancaServiceImpl implements MudancaService{
	
	private MudancaRepository mudancaRepository;
	
	public MudancaServiceImpl(MudancaRepository mudancaRepository) {
		this.mudancaRepository = mudancaRepository;
	}

	@Override
	public Page<MudancaDto> lista(StatusMudanca statusMudanca, Pageable paginacao) {
		if(statusMudanca == null) {
			Page<Mudanca> mudancas = mudancaRepository.findAll(paginacao);
			return MudancaDto.converter(mudancas);
		} else {
			Page<Mudanca> mudancas = mudancaRepository.findByStatusMudanca(statusMudanca, paginacao);
			return MudancaDto.converter(mudancas);
		}	
	}

	@Override
	@Transactional
	public ResponseEntity<MudancaDto> cadastrar(MudancaForm form, UriComponentsBuilder uriBuilder) {
		Mudanca mudanca  = form.novaMudanca();
		mudancaRepository.save(mudanca);
		
		URI uri = uriBuilder.path("/mudancas/{id}").buildAndExpand(mudanca.getId()).toUri();
		return ResponseEntity.created(uri).body(new MudancaDto(mudanca));
	}

	@Override
	@Transactional
	public ResponseEntity<MudancaDto> atualizar(Long id, MudancaForm form) {
		Optional<Mudanca> optional = mudancaRepository.findById(id);
		if(optional.isPresent()) {
			Mudanca mudanca = form.atualizar(id, mudancaRepository);
			return ResponseEntity.ok(new MudancaDto(mudanca));
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	@Transactional
	public ResponseEntity<MudancaDto> alterarStatusMudanca(Long id, MudancaForm form) {
		Optional<Mudanca> optional = mudancaRepository.findById(id);
		if(optional.isPresent()) {
			Mudanca mudanca = form.atualizarStatus(id, mudancaRepository);
			return ResponseEntity.ok(new MudancaDto(mudanca));
		}
		return ResponseEntity.notFound().build();
	}

}
