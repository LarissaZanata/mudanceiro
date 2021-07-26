package br.com.mudanceiro.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mudanceiro.controller.dto.InformacoesMudancaDTO;
import br.com.mudanceiro.controller.dto.MudancaDTO;
import br.com.mudanceiro.controller.form.AtualizaOrcamentoMudancaPorMudanceiroForm;
import br.com.mudanceiro.controller.form.AtualizaStatusMudancaPorClienteForm;
import br.com.mudanceiro.controller.form.MudancaForm;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.service.MudancaService;

@RestController
@RequestMapping("/mudancas")
public class MudancaController {
	
	private MudancaService mudancaService;
	
	public MudancaController(MudancaService mudancaService) {
		this.mudancaService = mudancaService;
	}
	
	@PostMapping("save/{idUsuario}")
	@ResponseStatus(HttpStatus.CREATED) //ok testado
	public Long save(@PathVariable Long idUsuario, @RequestBody MudancaForm form) {
		Mudanca mudanca = mudancaService.salvar(idUsuario, Mudanca.converte(form));
		
		return mudanca.getId();
	}
	
	@GetMapping("{idMudanca}") //ok testado
	public InformacoesMudancaDTO getMudanca(@PathVariable Long idMudanca) {
		Mudanca mudanca = mudancaService.obterMudancaCompleta(idMudanca);
		return InformacoesMudancaDTO.converte(mudanca);					
	}
	
	@PutMapping("/orcamento/{idMudanca}/{idMudanceiro}") //ok testado
	public void InformarOrcamento(@PathVariable Long idMudanca, @PathVariable Long idMudanceiro,  
			@RequestBody AtualizaOrcamentoMudancaPorMudanceiroForm form) {
		mudancaService.atualizaOrcamento(idMudanca, idMudanceiro, form);
	}
	
	
	@PutMapping("/status/{idMudanca}") //para o cliente cancelar ou aceitar orçamento. //ok TESTADO
	public void AtualizarStatus(@PathVariable Long idMudanca, @RequestBody AtualizaStatusMudancaPorClienteForm form) {
		mudancaService.atualizaStatusMudanca(idMudanca, form);
	}
	
	@GetMapping("/pendentes")
	public List<MudancaDTO> listaMudancasPendentes(){ //ok testado
		List<Mudanca> mudancasPendentes = mudancaService.buscaMudancasPendentes();
		return MudancaDTO.convertAll(mudancasPendentes);
	}
	
	@GetMapping("mudancasMudanceiro/{idMudanceiro}") //ok testado
	public List<MudancaDTO> listaMudancasPorIdMudanceiro(@PathVariable Long idMudanceiro){
		List<Mudanca> mudancas = mudancaService.buscaMudancasPorIdMudanceiro(idMudanceiro);
		return MudancaDTO.convertAll(mudancas);
	}
}