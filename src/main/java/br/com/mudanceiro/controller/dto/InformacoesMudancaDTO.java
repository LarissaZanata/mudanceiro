package br.com.mudanceiro.controller.dto;

import java.math.BigDecimal;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.TipoImovel;

public class InformacoesMudancaDTO {

	private Long id;
	private UsuarioDTO cliente;
	private MudanceiroDTO mudanceiro;
	private int cepOrigem;
	private int cepDestino;
	private TipoImovel imovelOrigem;
	private TipoImovel imovelDestino;
	private String mobilia;
	private BigDecimal valorOrcamento;
	private byte[] mobiliaImagem;
	
	public InformacoesMudancaDTO() {
		
	}
	
	public InformacoesMudancaDTO(Long id, UsuarioDTO cliente, MudanceiroDTO mudanceiro, int cepOrigem,
			int cepDestino, TipoImovel imovelOrigem, TipoImovel imovelDestino, String mobilia,
			BigDecimal valorOrcamento, byte[] mobiliaImagem) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.mudanceiro = mudanceiro;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.imovelOrigem = imovelOrigem;
		this.imovelDestino = imovelDestino;
		this.mobilia = mobilia;
		this.valorOrcamento = valorOrcamento;
		this.mobiliaImagem = mobiliaImagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioDTO getCliente() {
		return cliente;
	}

	public void setCliente(UsuarioDTO cliente) {
		this.cliente = cliente;
	}

	public MudanceiroDTO getMudanceiro() {
		return mudanceiro;
	}

	public void setMudanceiro(MudanceiroDTO mudanceiro) {
		this.mudanceiro = mudanceiro;
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

	public BigDecimal getValorOrcamento() {
		return valorOrcamento;
	}

	public void setValorOrcamento(BigDecimal valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}

	public byte[] getMobiliaImagem() {
		return mobiliaImagem;
	}

	public void setMobiliaImagem(byte[] mobiliaImagem) {
		this.mobiliaImagem = mobiliaImagem;
	}

	public static InformacoesMudancaDTO converte(Mudanca mudanca) {
		UsuarioDTO clienteDTO = UsuarioDTO.converte(mudanca.getCliente());
		MudanceiroDTO mudanceiroDTO = new MudanceiroDTO();
		if(mudanca.getMudanceiro() != null) {
			mudanceiroDTO = MudanceiroDTO.converte(mudanca.getMudanceiro());
		}
		
		InformacoesMudancaDTO mudancaDTO = new InformacoesMudancaDTO();
		mudancaDTO.setId(mudanca.getId());
		mudancaDTO.setCliente(clienteDTO);
		mudancaDTO.setMudanceiro(mudanceiroDTO);
		mudancaDTO.setCepOrigem(mudanca.getCepOrigen());
		mudancaDTO.setCepDestino(mudanca.getCepDestino());
		mudancaDTO.setImovelOrigem(mudanca.getImovelOrigem());
		mudancaDTO.setImovelDestino(mudanca.getImovelDestino());
		mudancaDTO.setMobilia(mudanca.getMobilia());
		mudancaDTO.setValorOrcamento(mudanca.getValorOrcamento());
		mudancaDTO.setMobiliaImagem(mudanca.getMobiliaImagem());
		
		return mudancaDTO;
	}
	
	
}
