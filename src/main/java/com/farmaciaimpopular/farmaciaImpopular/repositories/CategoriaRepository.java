package com.farmaciaimpopular.farmaciaImpopular.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciaimpopular.farmaciaImpopular.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNomeCategoria(String nomeCategoria);
	Optional<Categoria> findByDescricaoCategoria (String descricaoCategoria);	
}
