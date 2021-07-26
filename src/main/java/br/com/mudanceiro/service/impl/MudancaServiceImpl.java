package br.com.mudanceiro.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.mudanceiro.repository.MudancaRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudancaService;
import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.exception.MudancaNaoEncontradaException;
import br.com.mudanceiro.exception.MudanceiroNaoEncontradoException;
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.Usuario;

@Service
public class MudancaServiceImpl implements MudancaService{
	
	
	
	private MudancaRepository mudancaRepository;
	private UsuarioRepository usuarioRepository;
	
	public MudancaServiceImpl(MudancaRepository mudancaRepository, 
							  UsuarioRepository usuarioRepository) {
		this.mudancaRepository = mudancaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional
	public Mudanca salvar(Long idUsuario, Mudanca mudanca) {

		Usuario cliente = usuarioRepository.findById(idUsuario)
		.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
		
		mudanca.setCliente(cliente);
		
		return mudancaRepository.save(mudanca);
	}

	@Override
	public Mudanca obterMudancaCompleta(Long id) {	
		return mudancaRepository.findById(id)
				.orElseThrow(() -> new MudancaNaoEncontradaException());
	}

	@Override
	@Transactional
	public void atualizaOrcamento(Long id, Long idMudanceiro, AtualizaOrcamentoMudancaPorMudanceiroForm form) {	
		
		Optional<Usuario> mudanceiro = usuarioRepository.findById(idMudanceiro);

		if(mudanceiro.isPresent()) {
			mudancaRepository.findById(id)
					.map(mudancaExistente -> {
						mudancaExistente.setValorOrcamento(form.getValorOrcamento());
						mudancaExistente.setMudanceiro(mudanceiro.get());
						mudancaRepository.save(mudancaExistente);
						return mudancaExistente;
					}).orElseThrow(() -> new MudancaNaoEncontradaException());
		} else {
			throw new MudanceiroNaoEncontradoException(idMudanceiro);
		}
	}
	

	
	@Override
	@Transactional
	public void atualizaStatusMudanca(Long id, AtualizaStatusMudancaPorClienteForm form) {
		mudancaRepository.findById(id)
						.map(mudancaExistente -> {
							mudancaExistente.setStatusMudanca(form.getStatusMudanca());
							mudancaRepository.save(mudancaExistente);
							return mudancaExistente;
						}).orElseThrow(() -> new MudancaNaoEncontradaException());
	}

	@Override
	public List<Mudanca> buscaMudancasPendentes() {
		
		Optional<List<Mudanca>> mudancasPendentes = mudancaRepository.findAllPendentes();
		
		if(mudancasPendentes.isPresent()) {
			return mudancasPendentes.get();
		}else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Mudanca> buscaMudancasPorIdMudanceiro(Long idMudanceiro) {

			Optional<List<Mudanca>> mudancas = mudancaRepository.findAllMudanceiro(idMudanceiro);
			
			if(mudancas.isPresent()) {
				return mudancas.get();
			}else {
				return Collections.emptyList();
			}
	}

}
