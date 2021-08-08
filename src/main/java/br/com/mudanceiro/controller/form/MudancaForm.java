package br.com.mudanceiro.controller.form;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.TipoImovel;
import br.com.mudanceiro.repository.MudancaRepository;

public class MudancaForm {
	
	@NotEmpty(message = "{campo.idUsuario.obrigatorio}")
	private Long usuario;
	
	@NotEmpty(message = "{campo.cepOrigem.obrigatorio}")
	private int cepOrigen;

	@NotEmpty(message = "{campo.cepDestino.obrigatorio}")
	private int cepDestino;
	
	@NotEmpty(message = "{campo.dataMudanca.obrigatorio}")
	private String dataMudanca;
	
	@NotEmpty(message = "{campo.imovelOrigem.obrigatorio}")
	private TipoImovel imovelOrigem;
	
	@NotEmpty(message = "{campo.imovelDestino.obrigatorio}")
	private TipoImovel imovelDestino;
	
	@NotEmpty(message = "{campo.mobilia.obrigatorio}")
	private String mobilia;
	
	private List<byte[]> mobiliaImagem;
	
	
	
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	public int getCepOrigen() {
		return cepOrigen;
	}
	public void setCepOrigen(int cepOrigen) {
		this.cepOrigen = cepOrigen;
	}
	public int getCepDestino() {
		return cepDestino;
	}
	public void setCepDestino(int cepDestino) {
		this.cepDestino = cepDestino;
	}
	public String getDataMudanca() {
		return dataMudanca;
	}
	public void setDataMudanca(String dataMudanca) {
		this.dataMudanca = dataMudanca;
	}
	public TipoImovel getImovelOrigem() {
		return imovelOrigem;
	}
	public void setImovelOrigem(TipoImovel imovelOrigem) {
		this.imovelOrigem = imovelOrigem;
	}
	public TipoImovel getImovelDestino() {
		return imovelDestino;
	}
	public void setImovelDestino(TipoImovel imovelDestino) {
		this.imovelDestino = imovelDestino;
	}
	public String getMobilia() {
		return mobilia;
	}
	public void setMobilia(String mobilia) {
		this.mobilia = mobilia;
	}
	public List<byte[]> getMobiliaImagem() {
		return mobiliaImagem;
	}
	public void setMobiliaImagem(List<byte[]> mobiliaImagem) {
		this.mobiliaImagem = mobiliaImagem;
	}

}
