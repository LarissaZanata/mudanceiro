package br.com.mudanceiro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.mudanceiro.repository.MudanceiroRepository;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.MudanceiroService;
import br.com.mudanceiro.controller.form.MudanceiroForm;
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
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mudanceiro não encontrado."));
	}
	
	@Override
	@Transactional
	public Mudanceiro save(MudanceiroForm form) {
		Usuario usuario = usuarioRepository
											.findById(form.getIdUsuario())
											.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário para o id " + form.getIdUsuario()));
		Mudanceiro mudanceiro = new Mudanceiro();
		mudanceiro.setTipoServico(form.getTipoServico());
		mudanceiro.setUsuario(usuario);
		
		return mudanceiroRepository.save(mudanceiro);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) { 
		Optional<Mudanceiro> usuario =  mudanceiroRepository.findById(id);
		if(usuario.isPresent()) {
			try {
				mudanceiroRepository.delete(usuario.get());
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mudanceiro não encontrado.");	
			}
			
		}	
	}
	
	@Transactional
	public void update(Long id, MudanceiroForm mudanceiroForm) {
		
		Optional<Usuario> usuario  = usuarioRepository.findById(mudanceiroForm.getIdUsuario());
		
		Mudanceiro mudanceiro = new Mudanceiro();
		mudanceiro.setTipoServico(mudanceiroForm.getTipoServico());
		
		if(usuario.isPresent()){
			mudanceiro.setUsuario(usuario.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
		
		mudanceiroRepository
						.findById(id)
						.map(mudanceiroExistente -> {
							mudanceiro.setId(mudanceiroExistente.getId());
							mudanceiroRepository.save(mudanceiro);
							return mudanceiroExistente;
						}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mudanceiro não encontrado para o id " + id));
	}
	
	public List<Mudanceiro> getAllMudanceiro(){
		
		List<Mudanceiro> mudanceiros = mudanceiroRepository.findAll();
		return mudanceiros;
	}
	
}
