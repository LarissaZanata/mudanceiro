package br.com.mudanceiro.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrcamentoMudanca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private BigDecimal valor;
	
	@ManyToOne
	private Usuario mudanceiro;

	public OrcamentoMudanca(BigDecimal valor, Usuario mudanceiro) {
		super();
		this.valor = valor;
		this.mudanceiro = mudanceiro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Usuario getMudanceiro() {
		return mudanceiro;
	}

	public void setMudanceiro(Usuario mudanceiro) {
		this.mudanceiro = mudanceiro;
	}
}
