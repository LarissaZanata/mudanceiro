package br.com.mudanceiro.controller.dto;

import br.com.mudanceiro.model.Usuario;

public class DadosUsuarioParaMudanceiroDTO {

	private Long id;
	private String nome;

	
	public DadosUsuarioParaMudanceiroDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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


	public static DadosUsuarioParaMudanceiroDTO converte(Usuario usuario) {
		DadosUsuarioParaMudanceiroDTO usuarioDto = new DadosUsuarioParaMudanceiroDTO(usuario.getId(), usuario.getNome());
		return usuarioDto;
	}
}
