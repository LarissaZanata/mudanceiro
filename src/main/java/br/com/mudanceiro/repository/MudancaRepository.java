package br.com.mudanceiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;

public interface MudancaRepository extends JpaRepository<Mudanca, Long>{

	List<Mudanca> findByStatusMudanca(StatusMudanca status);

}
