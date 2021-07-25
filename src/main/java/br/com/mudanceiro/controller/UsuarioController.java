package br.com.mudanceiro.controller;


import java.util.List;

import javax.validation.Valid;

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
import br.com.mudanceiro.controller.dto.UsuarioMudanceiroDTO;
import br.com.mudanceiro.controller.form.UsuarioForm;
import br.com.mudanceiro.controller.form.UsuarioMudanceiroForm;
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
	
	@PostMapping("save/usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO save(@RequestBody @Valid UsuarioForm usuarioForm) {
		Usuario usuarioSalvo = usuarioService.save(Usuario.converte(usuarioForm));
		return UsuarioDTO.converte(usuarioSalvo);
	}
	
	@PostMapping("save/mudanceiro")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioMudanceiroDTO saveUsuarioMudanceiro(@RequestBody @Valid UsuarioMudanceiroForm usuarioForm) {	
		Usuario usuarioSalvo = usuarioService.save(Usuario.converteMudanceiro(usuarioForm));
		return UsuarioMudanceiroDTO.converte(usuarioSalvo);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}
	
	@PutMapping("update/usuario/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {
		
		usuarioService.update(id, Usuario.converte(usuarioForm));
	}
	
	@PutMapping("update/mudanceiro/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateMudanceiro(@PathVariable Long id, @RequestBody UsuarioMudanceiroForm usuarioForm) {
		
		usuarioService.update(id, Usuario.converteMudanceiro(usuarioForm));
	}
	
	@GetMapping
	public List<UsuarioDTO> find(Usuario filtro){
		List<Usuario> usuarios = usuarioService.find(filtro);	
		return UsuarioDTO.convertAll(usuarios);
	}
}
