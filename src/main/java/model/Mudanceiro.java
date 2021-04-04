package model;

public class Mudanceiro {

	private Long id;
	private String nome;
	private String telefone;
	private TipoServico tipoServico = TipoServico.MUDANCEIRO;
	
	public Mudanceiro() {
	}
	
	public Mudanceiro(String nome, String telefone, TipoServico tipoServico) {
		this.nome = nome;
		this.telefone = telefone;
		this.tipoServico = tipoServico;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
}
