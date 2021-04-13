package br.com.mudanceiro.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.TipoImovel;

public class MudancaDto {

	private Long id;
	private int cepOrigen;
	private int cepDestino;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataMudanca;
	private TipoImovel imovelOrigem;
	private TipoImovel imovelDestino;
	private String mobilia;
	private byte[] mobiliaImagem;
	private Mudanceiro mudanceiro;
	
	public MudancaDto(Mudanca mudanca) {
		this.id = mudanca.getId();
		this.cepOrigen = mudanca.getCepOrigen();
		this.cepDestino = mudanca.getCepDestino();
		this.dataCriacao = mudanca.getDataCriacao();
		this.dataMudanca = mudanca.getDataMudanca();
		this.imovelOrigem = mudanca.getImovelOrigem();
		this.imovelDestino = mudanca.getImovelDestino();
		this.mobilia = mudanca.getMobilia();
		this.mobiliaImagem = mudanca.getMobiliaImagem();
		if(mudanca.getStatusMudanca() == StatusMudanca.ABERTA) {
			this.mudanceiro = mudanca.getMudanceiro();
		}
	}
	
	public Long getId() {
		return id;
	}
	public int getCepOrigen() {
		return cepOrigen;
	}
	public int getCepDestino() {
		return cepDestino;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public LocalDateTime getDataMudanca() {
		return dataMudanca;
	}
	public TipoImovel getImovelOrigem() {
		return imovelOrigem;
	}
	public TipoImovel getImovelDestino() {
		return imovelDestino;
	}
	public String getMobilia() {
		return mobilia;
	}
	public byte[] getMobiliaImagem() {
		return mobiliaImagem;
	}
	public Mudanceiro getMudanceiro() {
		return mudanceiro;
	}
	public static Page<MudancaDto> converter(Page<Mudanca> mudancas) {
		return (mudancas).map(MudancaDto::new);
	}
}
