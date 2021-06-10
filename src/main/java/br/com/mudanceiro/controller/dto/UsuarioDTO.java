package br.com.mudanceiro.controller.dto;

import br.com.mudanceiro.model.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	
	public UsuarioDTO(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
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
	
	public static UsuarioDTO converte(Usuario usuario) {
		UsuarioDTO usuarioDto = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
		return usuarioDto;
	}
}
