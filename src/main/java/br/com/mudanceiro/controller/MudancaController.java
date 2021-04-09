package br.com.mudanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mudanceiro.controller.dto.MudancaDto;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;
import br.com.mudanceiro.repository.MudancaRepository;

@RestController
public class MudancaController {
	
	@Autowired
	private MudancaRepository mudancaRepository;

	@RequestMapping("/mudancas")
	public List<MudancaDto> lista(StatusMudanca statusMudanca) {
		if(statusMudanca == null) {
			List<Mudanca> mudancas = mudancaRepository.findAll();
			return MudancaDto.converter(mudancas);
		} else {
			List<Mudanca> mudancas = mudancaRepository.findByStatusMudanca(statusMudanca);
			return MudancaDto.converter(mudancas);
		}	
	}
}
