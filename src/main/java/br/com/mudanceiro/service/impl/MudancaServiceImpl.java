package br.com.mudanceiro.service.impl;


import java.math.BigDecimal;
import java.text.Normalizer.Form;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mudanceiro.repository.MudancaRepository;
import br.com.mudanceiro.repository.MudanceiroRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudancaService;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.exception.RegraNegocioException;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.TipoImovel;
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
		mudanca.setImovelDEstino(form.getImovelDestino());
		mudanca.setMobilia(form.getMobilia());
		mudanca.setMobiliaImagem(form.getMobiliaImagem());
		
		return mudancaRepository.save(mudanca);
	}

	@Override
	public Mudanca obterMudancaCompleta(Long id) {	
		return mudancaRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Código de mudanca inválido."));
	}



	
}
