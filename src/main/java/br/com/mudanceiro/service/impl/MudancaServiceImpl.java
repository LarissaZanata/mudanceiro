package br.com.mudanceiro.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.mudanceiro.repository.MudancaRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudancaImagensService;
import br.com.mudanceiro.service.MudancaService;
import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.exception.MudancaNaoEncontradaException;
import br.com.mudanceiro.exception.MudanceiroNaoEncontradoException;
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.MudancaImagens;
import br.com.mudanceiro.model.OrcamentoMudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.Usuario;

@Service
public class MudancaServiceImpl implements MudancaService{
	
	
	
	private MudancaRepository mudancaRepository;
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MudancaImagensService mudancaImagensService;
	
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
			
			OrcamentoMudanca valorOrcamento = new OrcamentoMudanca(form.getValorOrcamento(), mudanceiro.get());
			
			mudancaRepository.findById(id)
					.map(mudancaExistente -> {
						List<OrcamentoMudanca> valores = mudancaExistente.getValoresOrcamento();
						valores.add(valorOrcamento);
						mudancaExistente.setValoresOrcamento(valores);
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

	@Override
	public Mudanca salvar(String dto, List<MultipartFile> file) {
		
		Mudanca mudanca = new Mudanca();
		
		List<MudancaImagens> imagens = mudancaImagensService.converteMudancaImagens(file);
		
		
		Gson gson = new Gson();

		Mudanca car = gson.fromJson(dto, Mudanca.class);
		
		return mudanca;
	}

}
