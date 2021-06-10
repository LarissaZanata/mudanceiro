package br.com.mudanceiro.service;

import java.util.List;

import br.com.mudanceiro.controller.form.MudanceiroForm;
import br.com.mudanceiro.model.Mudanceiro;

public interface MudanceiroService {
	
	public Mudanceiro getMudanceiroById(Long id);
	public Mudanceiro save(MudanceiroForm form);
	public void delete(Long id);
	public void update(Long id, MudanceiroForm mudanceiroForm);
	public List<Mudanceiro> find(Mudanceiro filtro);

}
