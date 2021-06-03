package com.farmaciaimpopular.farmaciaImpopular.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciaimpopular.farmaciaImpopular.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Optional<Produto> findByNomeProduto(String nomeProduto);
	Optional<Object> findByDescricaoProdutoContaining(String descricaoProduto);
}
