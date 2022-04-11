package com.gerenciador.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
