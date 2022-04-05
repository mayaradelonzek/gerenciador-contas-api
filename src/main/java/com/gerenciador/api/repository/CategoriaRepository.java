package com.gerenciador.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
