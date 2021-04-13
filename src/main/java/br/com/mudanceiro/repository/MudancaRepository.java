package br.com.mudanceiro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.StatusMudanca;

public interface MudancaRepository extends JpaRepository<Mudanca, Long>{

	Page<Mudanca> findByStatusMudanca(StatusMudanca status, Pageable paginacao);

}
