package br.com.mudanceiro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mudanceiro.controller.dto.MudancaDto;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.repository.MudancaRepository;

@RestController
@RequestMapping("/mudancas")
public class MudancaController {
	
	@Autowired
	private MudancaRepository mudancaRepository;

	@RequestMapping
	public Page<MudancaDto> lista(@RequestParam(required = false) StatusMudanca statusMudanca, @PageableDefault(sort = "id",
			direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		if(statusMudanca == null) {
			Page<Mudanca> mudancas = mudancaRepository.findAll(paginacao);
			return MudancaDto.converter(mudancas);
		} else {
			Page<Mudanca> mudancas = mudancaRepository.findByStatusMudanca(statusMudanca, paginacao);
			return MudancaDto.converter(mudancas);
		}	
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MudancaDto> cadastrar(@RequestBody @Valid MudancaForm form, UriComponentsBuilder uriBuilder) {
		Mudanca mudanca  = form.novaMudanca();
		mudancaRepository.save(mudanca);
		
		URI uri = uriBuilder.path("/mudancas/{id}").buildAndExpand(mudanca.getId()).toUri();
		return ResponseEntity.created(uri).body(new MudancaDto(mudanca));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MudancaDto> atualizar(@PathVariable Long id, @RequestBody @Valid MudancaForm form) {
		Optional<Mudanca> optional = mudancaRepository.findById(id);
		if(optional.isPresent()) {
			Mudanca mudanca = form.atualizar(id, mudancaRepository);
			return ResponseEntity.ok(new MudancaDto(mudanca));
		}
		return ResponseEntity.notFound().build();
	}
}
