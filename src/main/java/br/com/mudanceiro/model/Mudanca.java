package br.com.mudanceiro.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.mudanceiro.controller.form.MudancaForm;;

@Entity
public class Mudanca {
	
	private static final String FORMATO_DATA = "dd/MM/uuuu";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int cepOrigen;
	private int cepDestino;
	
	@ManyToOne  //um cliente pode ter varias mudanças
	private Usuario cliente;
	
	@ManyToOne  //um mudanceiro pode ter varias mudanças
	private Usuario mudanceiro;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private LocalDateTime dataMudanca;
	
	@Enumerated(EnumType.STRING)
	private TipoImovel imovelOrigem = TipoImovel.CASA;
	
	@Enumerated(EnumType.STRING)
	private TipoImovel imovelDestino = TipoImovel.CASA;
	
	private String mobilia;
	
	@Enumerated(EnumType.STRING)
	private StatusMudanca statusMudanca;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mudanca")
	private List<OrcamentoMudanca> valoresOrcamento;
	
	@OneToOne
	private OrcamentoMudanca orcamentoEscolhido;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_mudanca")
	private List<MudancaImagens> mobiliaImagens;
	
	//private byte[] mobiliaImagem;
	
	public Mudanca() {
	}
	
	public Mudanca(int cepOrigen, int cepDestino, LocalDateTime dataMudanca, TipoImovel imovelOrigem,
			TipoImovel imovelDestino, String mobilia, List<MudancaImagens> imagens) {
		this.cepOrigen = cepOrigen;
		this.cepDestino = cepDestino;
		this.dataMudanca = dataMudanca;
		this.imovelOrigem = imovelOrigem;
		this.imovelDestino = imovelDestino;
		this.mobilia = mobilia;
		this.statusMudanca = StatusMudanca.PENDENTE;
		this.mobiliaImagens = imagens;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mudanca other = (Mudanca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public Usuario getMudanceiro() {
		return mudanceiro;
	}

	public void setMudanceiro(Usuario mudanceiro) {
		this.mudanceiro = mudanceiro;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
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

	public TipoImovel getImovelDestino() {
		return imovelDestino;
	}
	
	public List<OrcamentoMudanca> getValoresOrcamento() {
		return valoresOrcamento;
	}

	public void setValoresOrcamento(List<OrcamentoMudanca> valoresOrcamento) {
		this.valoresOrcamento = valoresOrcamento;
	}

	public List<MudancaImagens> getMobiliaImagens() {
		return mobiliaImagens;
	}

	public void setMobiliaImagens(List<MudancaImagens> mobiliaImagens) {
		this.mobiliaImagens = mobiliaImagens;
	}	
	
	public OrcamentoMudanca getOrcamentoEscolhido() {
		return orcamentoEscolhido;
	}

	public void setOrcamentoEscolhido(OrcamentoMudanca orcamentoEscolhido) {
		this.orcamentoEscolhido = orcamentoEscolhido;
	}

	public static Mudanca converte(MudancaForm form) {
		List<MudancaImagens> imagens = convertImagens(form.getMobiliaImagem());
		
		Mudanca mudanca = new Mudanca(form.getCepOrigen(), form.getCepDestino(), StringToDate(form.getDataMudanca()), form.getImovelOrigem(),
				form.getImovelDestino(), form.getMobilia(), imagens);
		
		return mudanca;
	}
	
	private static List<MudancaImagens> convertImagens(List<byte[]> imagens) {
		List<MudancaImagens> mudancasImagens = new ArrayList<MudancaImagens>();
		
		if(!imagens.isEmpty() || !imagens.equals(null)) {
			for (byte[] bs : imagens) {
				MudancaImagens mudancaImagem = new MudancaImagens();
				mudancaImagem.setImagem(bs);
				mudancasImagens.add(mudancaImagem);
			}
		}
		
		return mudancasImagens;
	}
	
	private static LocalDateTime StringToDate(String data) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern(FORMATO_DATA);
		LocalDateTime dateTime = LocalDate.parse(data, parser).atStartOfDay();
		return dateTime;
	}

}
