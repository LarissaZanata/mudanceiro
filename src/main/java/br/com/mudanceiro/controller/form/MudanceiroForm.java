package br.com.mudanceiro.controller.form;

import br.com.mudanceiro.model.TipoServico;

public class MudanceiroForm {

	private TipoServico tipoServico;
	private Long idUsuario;
	
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
