package br.com.mudanceiro.controller.dto;

import org.aspectj.weaver.tools.ISupportsMessageContext;

import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.TipoServico;
import br.com.mudanceiro.model.Usuario;

public class UsuarioMudanceiroDTO {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private TipoServico tipoServico;
	
	public UsuarioMudanceiroDTO(Long id, String nome, String email, String telefone, TipoServico tipoServico) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.tipoServico = tipoServico;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
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

	public static UsuarioMudanceiroDTO converte(Usuario usuario) {
		UsuarioMudanceiroDTO usuarioMudanceiroDTO = new UsuarioMudanceiroDTO(usuario.getId(), usuario.getNome(), 
				usuario.getEmail(), usuario.getTelefone(), usuario.getTipoServico());
		return usuarioMudanceiroDTO;
	}
}
