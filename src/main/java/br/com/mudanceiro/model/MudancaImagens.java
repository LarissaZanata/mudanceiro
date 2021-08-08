package br.com.mudanceiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MudancaImagens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private byte[] imagem;
	
	//@ManyToOne(fetch = FetchType.LAZY, targetEntity = Mudanca.class)
	//@JoinColumn(name = "id_mudanca", nullable = false)
	//private Mudanca mudanca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	
}
