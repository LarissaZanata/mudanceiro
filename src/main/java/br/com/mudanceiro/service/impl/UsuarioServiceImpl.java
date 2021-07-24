package br.com.mudanceiro.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.mudanceiro.model.Usuario;
import br.com.mudanceiro.repository.UsuarioRepository;
import br.com.mudanceiro.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario getUsuarioById(Long id) {
		return usuarioRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		if(usuario.isPresent()) {
			try {
				usuarioRepository.delete(usuario.get());
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");	
			}
			
		}
		
	}

	@Override
	public void update(Long id, Usuario usuario) {
		usuarioRepository
						.findById(id)
						.map(usuarioExistente -> {
							usuario.setId(usuarioExistente.getId());
							usuarioRepository.save(usuario);
							return usuarioExistente;
						}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
	}

	@Override
	public List<Usuario> find(Usuario filtro) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example example = Example.of(filtro, matcher);
		return usuarioRepository.findAll(example);
	}
	
	
	
	
}
