package br.com.mudanceiro.controller.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.util.CollectionUtils;

import br.com.mudanceiro.model.Mudanceiro;
import br.com.mudanceiro.model.TipoServico;

public class MudanceiroDTO {
	private Long id;
	private TipoServico tipoServico;
	private UsuarioDTO usuario;
	
	public MudanceiroDTO(Long id, TipoServico tipoServico, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.tipoServico = tipoServico;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public static MudanceiroDTO converte(Mudanceiro mudanceiro) {
		UsuarioDTO usuarioMudanceiro = UsuarioDTO.converte(mudanceiro.getUsuario());
		MudanceiroDTO mudanceiroDTO = new MudanceiroDTO(mudanceiro.getId(),
														mudanceiro.getTipoServico(), 
														usuarioMudanceiro);
		return mudanceiroDTO;
	}
	
	public static List<MudanceiroDTO> convertAll(List<Mudanceiro> mudanceiros){
		/*List<MudanceiroDTO> allMudanceiros = new ArrayList<MudanceiroDTO>();
		
		for (Mudanceiro mudanceiro : mudanceiros) {
			allMudanceiros.add(converte(mudanceiro));
		}
		
		return allMudanceiros;*/
		if(CollectionUtils.isEmpty(mudanceiros)) {
			return Collections.emptyList();
		}
		
		List<MudanceiroDTO> allMudanceiros = mudanceiros.stream()
														.map(m -> MudanceiroDTO.converte(m))
														.collect(Collectors.toList());
		return allMudanceiros;
	}

	
	
	
}
