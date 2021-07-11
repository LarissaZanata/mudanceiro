package br.com.mudanceiro.controller.form;

import javax.validation.constraints.NotEmpty;

import br.com.mudanceiro.model.TipoServico;

public class MudanceiroForm {

	@NotEmpty(message = "{campo.tipoServico.obrigatorio}")
	private TipoServico tipoServico;
	
	@NotEmpty(message = "{campo.idUsuario.obrigatorio}")
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
