package br.com.mudanceiro.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mudanceiro.controller.dto.MudancaDto;
import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Usuario;

@RestController
public class MudancaController {

	@RequestMapping("/mudancas")
	public List<MudancaDto> lista() {
		Mudanca mudanca = new Mudanca(87030070, 87020210, new Usuario("Larissa", "larissazanata@gmail.com", "123mudar"));
		
		return MudancaDto.converter(Arrays.asList(mudanca, mudanca));
	}
}
