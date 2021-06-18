package br.com.mudanceiro.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.mudanceiro.repository.MudancaRepository;
import br.com.mudanceiro.repository.MudanceiroRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudancaService;
import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.Usuario;

@Service
public class MudancaServiceImpl implements MudancaService{
	
	private MudancaRepository mudancaRepository;
	private UsuarioRepository usuarioRepository;
	private MudanceiroRepository mudanceiroRepository;
	
	public MudancaServiceImpl(MudancaRepository mudancaRepository, 
							  UsuarioRepository usuarioRepository, 
							  MudanceiroRepository mudanceiroRepository) {
		this.mudancaRepository = mudancaRepository;
		this.usuarioRepository = usuarioRepository;
		this.mudanceiroRepository = mudanceiroRepository;
	}

	@Override
	@Transactional
	public Mudanca salvar(MudancaForm form) {
		Long idUsuario = form.getUsuario();
		Usuario cliente = usuarioRepository.findById(idUsuario)
		.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));
		
		Mudanca mudanca = new Mudanca();
		mudanca.setCepOrigen(form.getCepOrigen());
		mudanca.setCepDestino(form.getCepDestino());
		mudanca.setCliente(cliente);
		mudanca.setDataMudanca(form.getDataMudanca());
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
				.orElseThrow(() -> new RegraNegocioException("Código de mudanca inválido."));
	}

	@Override
	@Transactional
	public void atualizaOrcamento(Long id, AtualizaOrcamentoMudancaPorMudanceiroForm form) {	
		Mudanca mudanca = new Mudanca();
		mudanca.setValorOrcamento(form.getValorOrcamento());

		mudancaRepository.findById(id)
							.map(mudancaExistente -> {
								mudanca.setMudanceiro(populaMudanceiro(mudancaExistente.getCliente()));			
								mudanca.setId(mudancaExistente.getId());
								mudancaRepository.save(mudanca);
								return mudancaExistente;	
							}).orElseThrow(() -> 
							new ResponseStatusException(HttpStatus.NOT_FOUND, "Mudanca não encontrada para o id " + id));
		
	}
	
	private Mudanceiro populaMudanceiro(Usuario usuarioMudanca) {
		return mudanceiroRepository.findByUsuario(usuarioMudanca)
							.map(mudanceiroExistente -> {
								return mudanceiroExistente;
							}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
					"Mudanceiro não encontrado para usuario com o id " + usuarioMudanca.getId()));
	}
	
	@Override
	@Transactional
	public void atualizaStatusMudanca(Long id, AtualizaStatusMudancaPorClienteForm form) {
		Mudanca mudanca = new Mudanca();
		mudanca.setStatusMudanca(form.getStatusMudanca());
		
		mudancaRepository.findById(id)
						.map(mudancaExistente -> {
							mudanca.setId(mudancaExistente.getId());
							mudancaRepository.save(mudanca);
							return mudancaExistente;
						}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
								"Mudanca não encontada para o id " + id));
	}

	@Override
	public List<Mudanca> buscaMudancasPendentes() {
		
		return null;
	}
	
	
	//fazer um pra all mudancas por id mudanceiro
	
}
