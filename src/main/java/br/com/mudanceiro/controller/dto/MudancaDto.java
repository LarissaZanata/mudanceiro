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
	
	
}
