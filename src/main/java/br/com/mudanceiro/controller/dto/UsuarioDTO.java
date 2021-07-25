package br.com.mudanceiro.controller.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import br.com.mudanceiro.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
	public UsuarioDTO(Long id, String nome, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static UsuarioDTO converte(Usuario usuario) {
		UsuarioDTO usuarioDto = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
		return usuarioDto;
	}
	
	public static List<UsuarioDTO> convertAll(List<Usuario> usuarios){
		
		
		if(CollectionUtils.isEmpty(usuarios)) {
			return Collections.emptyList();
		}
		
		List<UsuarioDTO> allUsuarios = usuarios.stream()
													.map(m -> UsuarioDTO.converte(m))
													.collect(Collectors.toList());
		return allUsuarios;
	}
}
