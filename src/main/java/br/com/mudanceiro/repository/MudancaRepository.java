package br.com.mudanceiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mudanceiro.model.Mudanca;
import br.com.mudanceiro.model.Usuario;

public interface MudancaRepository extends JpaRepository<Mudanca, Long>{

	public static final String MUDANCAS_PEDENTES = "select * from Mudanca where STATUS_MUDANCA = 'PENDENTE'";
	
	public static final String MUDANCAS_MUDANCEIRO = "select * from Mudanca where MUDANCEIRO_ID = :idUser";

	@Query(value = MUDANCAS_PEDENTES, nativeQuery = true)
	Optional<List<Mudanca>> findAllPendentes();
	
	@Query(value = MUDANCAS_MUDANCEIRO, nativeQuery = true)
	Optional<List<Mudanca>> findAllMudanceiro(@Param("idUser") Long mudanceiro);

}
