package br.com.mudanceiro.controller.dto;

import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.TipoServico;

public class MudanceiroDTO {
	private Long id;
	private TipoServico tipoServico;
	private UsuarioDTO usuario;
	
	public MudanceiroDTO(Long id, TipoServico tipoServico, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.tipoServico = tipoServico;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public static MudanceiroDTO converte(Mudanceiro mudanceiro) {
		UsuarioDTO usuarioMudanceiro = UsuarioDTO.converte(mudanceiro.getUsuario());
		MudanceiroDTO mudanceiroDTO = new MudanceiroDTO(mudanceiro.getId(),
														mudanceiro.getTipoServico(), 
														usuarioMudanceiro);
		return mudanceiroDTO;
	}
	
	
	
}
