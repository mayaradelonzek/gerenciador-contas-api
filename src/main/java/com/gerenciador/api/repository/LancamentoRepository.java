package com.gerenciador.api.repository;

import com.gerenciador.api.model.Lancamento;
import com.gerenciador.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
