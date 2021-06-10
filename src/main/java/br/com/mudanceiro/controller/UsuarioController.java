package br.com.mudanceiro.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mudanceiro.controller.dto.UsuarioDTO;
import br.com.mudanceiro.model.Usuario;
import br.com.mudanceiro.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("{id}")
	public UsuarioDTO getUsuarioById(@PathVariable Long id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		
		return UsuarioDTO.converte(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario save(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Usuario usuario) {
		usuarioService.update(id, usuario);
	}
	
	@GetMapping
	public List<Usuario> find(Usuario filtro){
		return usuarioService.find(filtro);
	}
}
