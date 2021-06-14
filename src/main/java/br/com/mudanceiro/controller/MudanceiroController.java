package br.com.mudanceiro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mudanceiro.controller.dto.MudanceiroDTO;
import br.com.mudanceiro.controller.form.MudanceiroForm;
import br.com.mudanceiro.service.MudanceiroService;
import br.com.mudanceiro.model.Mudanceiro;

@RestController
@RequestMapping("/mudanceiros")
public class MudanceiroController {

	private MudanceiroService mudanceiroService;
	
	public MudanceiroController(MudanceiroService mudanceiroService) {
		this.mudanceiroService = mudanceiroService;
	}
	
	@GetMapping("{id}")
	public MudanceiroDTO getUsuarioById(@PathVariable Long id) {
		Mudanceiro mudanceiro = mudanceiroService.getMudanceiroById(id);
		
		return MudanceiroDTO.converte(mudanceiro);
	}
	
	@GetMapping
	public List<MudanceiroDTO> getAllUsuarioById() {
		List<Mudanceiro> mudanceiros = mudanceiroService.getAllMudanceiro();
		
		return MudanceiroDTO.convertAll(mudanceiros);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MudanceiroDTO save(@RequestBody @Valid  MudanceiroForm mudanceiroForm) {
		Mudanceiro MudanceiroSalvo = mudanceiroService.save(mudanceiroForm);
		return MudanceiroDTO.converte(MudanceiroSalvo);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		mudanceiroService.delete(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody MudanceiroForm mudanceiro) {
		
		mudanceiroService.update(id, mudanceiro);
	}
	
}
