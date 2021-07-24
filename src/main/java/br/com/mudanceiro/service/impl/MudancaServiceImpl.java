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
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.Usuario;

@Service
public class MudancaServiceImpl implements MudancaService{
	
	private static final String FORMATO_DATA = "dd/MM/uuuu";
	
	private MudancaRepository mudancaRepository;
	private UsuarioRepository usuarioRepository;
	
	public MudancaServiceImpl(MudancaRepository mudancaRepository, 
							  UsuarioRepository usuarioRepository) {
		this.mudancaRepository = mudancaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional
	public Mudanca salvar(MudancaForm form) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern(FORMATO_DATA);
		
		
		Long idUsuario = form.getUsuario();
		Usuario cliente = usuarioRepository.findById(idUsuario)
		.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
		
		Mudanca mudanca = new Mudanca();
		mudanca.setCepOrigen(form.getCepOrigen());
		mudanca.setCepDestino(form.getCepDestino());
		mudanca.setCliente(cliente);
		mudanca.setDataMudanca(LocalDate.parse(form.getDataMudanca(), parser).atStartOfDay());
		mudanca.setImovelOrigem(form.getImovelOrigem());
		mudanca.setImovelDestino(form.getImovelDestino());
		mudanca.setMobilia(form.getMobilia());
		mudanca.setMobiliaImagem(form.getMobiliaImagem());
		mudanca.setStatusMudanca(StatusMudanca.PENDENTE);
		mudanca.setValorOrcamento(BigDecimal.valueOf(0));
		
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
		Mudanca mudanca = new Mudanca();
		mudanca.setValorOrcamento(form.getValorOrcamento());
		
		Optional<Usuario> mudanceiro = usuarioRepository.findById(idMudanceiro);
		
		if(mudanceiro.isPresent()) {
			
			mudancaRepository.findById(id)
								.map(mudancaExistente -> {
									mudanca.setId(mudancaExistente.getId()); //verificar outra maneira de atualizar sem precisar setar td de novo
									mudanca.setMudanceiro(mudanceiro.get());
									
									mudanca.setCepDestino(mudancaExistente.getCepDestino());
									mudanca.setCepOrigen(mudancaExistente.getCepOrigen());
									mudanca.setCliente(mudancaExistente.getCliente());
									mudanca.setDataCriacao(mudanca.getDataCriacao());
									mudanca.setDataMudanca(mudancaExistente.getDataMudanca());
									mudanca.setImovelDestino(mudancaExistente.getImovelDestino());
									mudanca.setImovelOrigem(mudancaExistente.getImovelOrigem());
									mudanca.setMobilia(mudancaExistente.getMobilia());
									mudanca.setMobiliaImagem(mudancaExistente.getMobiliaImagem());
									mudanca.setStatusMudanca(mudancaExistente.getStatusMudanca());
									mudancaRepository.save(mudanca);
									return mudancaExistente;
								}).orElseThrow(() -> new MudancaNaoEncontradaException());
		}
	}
	

	
	@Override
	@Transactional
	public void atualizaStatusMudanca(Long id, AtualizaStatusMudancaPorClienteForm form) {
		Mudanca mudanca = new Mudanca();
		mudanca.setStatusMudanca(form.getStatusMudanca());
		
		mudancaRepository.findById(id)
						.map(mudancaExistente -> {
							mudanca.setId(mudancaExistente.getId());
							mudanca.setCepDestino(mudancaExistente.getCepDestino());
							mudanca.setCepOrigen(mudancaExistente.getCepOrigen());
							mudanca.setCliente(mudancaExistente.getCliente());
							mudanca.setDataCriacao(mudanca.getDataCriacao());
							mudanca.setDataMudanca(mudancaExistente.getDataMudanca());
							mudanca.setImovelDestino(mudancaExistente.getImovelDestino());
							mudanca.setImovelOrigem(mudancaExistente.getImovelOrigem());
							mudanca.setMobilia(mudancaExistente.getMobilia());
							mudanca.setMobiliaImagem(mudancaExistente.getMobiliaImagem());
							mudanca.setMudanceiro(mudancaExistente.getMudanceiro());
							mudanca.setValorOrcamento(mudancaExistente.getValorOrcamento());
							mudancaRepository.save(mudanca);
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
