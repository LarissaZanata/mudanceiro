package br.com.mudanceiro.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.TipoImovel;
import br.com.mudanceiro.repository.MudancaRepository;

public class MudancaForm {
	
	private int cepOrigen;

	private int cepDestino;
	
	private LocalDateTime dataMudanca;
	
	private TipoImovel imovelOrigem;
	
	private TipoImovel imovelDestino;
	
	private String mobilia;
	private byte[] mobiliaImagem;
	
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
	public LocalDateTime getDataMudanca() {
		return dataMudanca;
	}
	public void setDataMudanca(LocalDateTime dataMudanca) {
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
	public byte[] getMobiliaImagem() {
		return mobiliaImagem;
	}
	public void setMobiliaImagem(byte[] mobiliaImagem) {
		this.mobiliaImagem = mobiliaImagem;
	}
	
	public Mudanca novaMudanca() {
		return new Mudanca(cepOrigen, cepDestino, dataMudanca, imovelOrigem,
				imovelDestino, mobilia, mobiliaImagem);
	}

	public Mudanca atualizar(Long id, MudancaRepository mudancaRepository) {
		Mudanca mudanca = mudancaRepository.getOne(id);
		mudanca.setCepOrigen(this.cepOrigen);
		mudanca.setCepDestino(this.cepDestino);
		mudanca.setDataMudanca(this.dataMudanca);
		mudanca.setImovelOrigem(this.imovelOrigem);
		mudanca.setImovelDestino(this.imovelDestino);
		mudanca.setMobilia(this.mobilia);
		mudanca.setMobiliaImagem(this.mobiliaImagem);
		return mudanca;
	}
}
