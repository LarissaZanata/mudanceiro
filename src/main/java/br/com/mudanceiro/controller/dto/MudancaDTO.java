package br.com.mudanceiro.controller.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.model.TipoImovel;


public class MudancaDTO {
	
	private UsuarioDTO cliente;
	private int cepOrigem;
	private int cepDestino;
	private TipoImovel imovelOrigem;
	private TipoImovel imovelDestino;
	private String mobilia;
	private StatusMudanca statusMudanca;
	private byte[] mobiliaImagem;
	
	public MudancaDTO() {
		
	}
	
	public MudancaDTO(UsuarioDTO cliente, int cepOrigem, int cepDestino, TipoImovel imovelOrigem,
			TipoImovel imovelDestino, String mobilia, StatusMudanca statusMudanca, byte[] mobiliaImagem) {
		super();
		this.cliente = cliente;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.imovelOrigem = imovelOrigem;
		this.imovelDestino = imovelDestino;
		this.mobilia = mobilia;
		this.statusMudanca = statusMudanca;
		this.mobiliaImagem = mobiliaImagem;
	}

	public UsuarioDTO getCliente() {
		return cliente;
	}

	public void setCliente(UsuarioDTO cliente) {
		this.cliente = cliente;
	}

	public int getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(int cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public int getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(int cepDestino) {
		this.cepDestino = cepDestino;
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

	public StatusMudanca getStatusMudanca() {
		return statusMudanca;
	}

	public void setStatusMudanca(StatusMudanca statusMudanca) {
		this.statusMudanca = statusMudanca;
	}

	public byte[] getMobiliaImagem() {
		return mobiliaImagem;
	}

	public void setMobiliaImagem(byte[] mobiliaImagem) {
		this.mobiliaImagem = mobiliaImagem;
	}
	
	public static MudancaDTO converte(Mudanca mudanca) {
		UsuarioDTO cliente = UsuarioDTO.converte(mudanca.getCliente());
		MudancaDTO mudancaDTO = new MudancaDTO();
		mudancaDTO.setCliente(cliente);
		mudancaDTO.setCepOrigem(mudanca.getCepOrigen());
		mudancaDTO.setCepDestino(mudanca.getCepDestino());
		mudancaDTO.setImovelOrigem(mudanca.getImovelOrigem());
		mudancaDTO.setImovelDestino(mudanca.getImovelDestino());
		mudancaDTO.setMobilia(mudanca.getMobilia());
		mudancaDTO.setStatusMudanca(mudanca.getStatusMudanca());
		mudancaDTO.setMobiliaImagem(mudanca.getMobiliaImagem());
		return mudancaDTO;
		
	}
	
	public List<MudancaDTO> convertAll(List<Mudanca> mudancas){
		if(CollectionUtils.isEmpty(mudancas)) {
			return Collections.emptyList();
		}
		
		List<MudancaDTO> allMudancas = mudancas.stream()
														.map(m -> MudancaDTO.converte(m))
														.collect(Collectors.toList());
		return allMudancas;
	}
}
