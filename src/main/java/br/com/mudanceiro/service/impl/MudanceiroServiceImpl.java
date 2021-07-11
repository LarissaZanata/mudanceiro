package br.com.mudanceiro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mudanceiro.repository.MudanceiroRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudanceiroService;
import br.com.mudanceiro.controller.form.UpdateUsuarioMudanceiroForm;
import br.com.mudanceiro.controller.form.UsuarioMudanceiroForm;
import br.com.mudanceiro.exception.MudanceiroNaoEncontradoException;
import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.Usuario;

@Service
public class MudanceiroServiceImpl implements MudanceiroService{

	private MudanceiroRepository mudanceiroRepository;
	private UsuarioRepository usuarioRepository;
	
	public MudanceiroServiceImpl(MudanceiroRepository mudanceiroRepository, UsuarioRepository usuarioRepository) {
		this.mudanceiroRepository = mudanceiroRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@Override
	public Mudanceiro getMudanceiroById(Long id) {
		return mudanceiroRepository
				.findById(id)
				.orElseThrow(() -> new MudanceiroNaoEncontradoException());
	}
	
	@Override
	@Transactional
	public Mudanceiro save(UsuarioMudanceiroForm form) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(form.getNome());
		usuario.setEmail(form.getEmail());
		usuario.setSenha(form.getSenha());
		usuario.setTelefone(form.getTelefone());
		
		usuarioRepository.save(usuario);
		
		Mudanceiro mudanceiro = new Mudanceiro();
		mudanceiro.setTipoServico(form.getTipoServico());
		mudanceiro.setUsuario(usuario);
		
		return mudanceiroRepository.save(mudanceiro);
		
	}
	
	@Override
	@Transactional
	public void delete(Long id) { 
		Optional<Mudanceiro> mudanceiro =  mudanceiroRepository.findById(id);
		if(mudanceiro.isPresent()) {
			mudanceiroRepository.delete(mudanceiro.get());
		}
	}
	
	@Override
	@Transactional
	public void update(Long id, UpdateUsuarioMudanceiroForm mudanceiroForm) {
		
		Mudanceiro mudanceiro = new Mudanceiro();
		mudanceiro.setTipoServico(mudanceiroForm.getTipoServico());
		Usuario usuario = new Usuario();
		usuario.setEmail(mudanceiroForm.getEmail());
		usuario.setNome(mudanceiroForm.getNome());
		usuario.setTelefone(mudanceiroForm.getTelefone());
		
		
		mudanceiroRepository
						.findById(id)
						.map(mudanceiroExistente -> {
							mudanceiro.setId(mudanceiroExistente.getId());
							usuario.setId(mudanceiroExistente.getUsuario().getId());
							usuario.setSenha(mudanceiroExistente.getUsuario().getSenha());
							mudanceiro.setUsuario(usuario);
							mudanceiroRepository.save(mudanceiro);
							return mudanceiroExistente;
						}).orElseThrow(() -> new MudanceiroNaoEncontradoException(id));
	}
	
	@Override
	public List<Mudanceiro> getAllMudanceiro(){
		
		List<Mudanceiro> mudanceiros = mudanceiroRepository.findAll();
		return mudanceiros;
	}
	
}
