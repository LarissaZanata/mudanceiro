package br.com.mudanceiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.mudanceiro.model.Mudanca;

public interface MudancaRepository extends JpaRepository<Mudanca, Long>{

	public static final String MUDANCAS_PEDENTES = "select * from Mudanca where STATUS_MUDANCA = 'PENDENTE'";
	
	@Query(value = MUDANCAS_PEDENTES, nativeQuery = true)
	Optional<List<Mudanca>> findAllPendentes();

}
