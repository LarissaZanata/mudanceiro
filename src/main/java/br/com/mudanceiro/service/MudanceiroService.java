package br.com.mudanceiro.service;

import java.util.List;

import br.com.mudanceiro.controller.form.UpdateUsuarioMudanceiroForm;
import br.com.mudanceiro.controller.form.UsuarioMudanceiroForm;
import br.com.mudanceiro.model.Mudanceiro;

public interface MudanceiroService {
	
	public Mudanceiro getMudanceiroById(Long id);
	public Mudanceiro save(UsuarioMudanceiroForm form);
	public void delete(Long id);
	public void update(Long id, UpdateUsuarioMudanceiroForm mudanceiroForm);
	public List<Mudanceiro> getAllMudanceiro();

}
