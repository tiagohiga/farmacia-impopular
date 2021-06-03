package com.farmaciaimpopular.farmaciaImpopular.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmaciaimpopular.farmaciaImpopular.models.Categoria;
import com.farmaciaimpopular.farmaciaImpopular.repositories.CategoriaRepository;

@Service
public class CategoriaServices {
	@Autowired
	private CategoriaRepository repositoryC;
	
	public Optional<Categoria> cadastrarProduto(Categoria novaCategoria){
		Optional<Categoria> categoriaExistente = repositoryC.findByNomeCategoria(novaCategoria.getNomeCategoria());
		if(categoriaExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryC.save(novaCategoria));
		}
	}
	
	public Optional<Object> alterarCadastro(Long idCategoria, Categoria atualizaCategoria){
		Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria);
		if(categoriaExistente.isPresent()) {
			categoriaExistente.get().setDescricaoCategoria(atualizaCategoria.getDescricaoCategoria());
			return Optional.ofNullable(repositoryC.save(categoriaExistente.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public ResponseEntity<String> removerCategoria(Long idCategoria){
		Optional<Categoria> categoriaExistente = repositoryC.findById(idCategoria);
		if(categoriaExistente.isPresent()) {
			repositoryC.deleteById(idCategoria);
			return ResponseEntity.status(200).body("Item removido");
		}else {
			return ResponseEntity.status(400).build();
		}
	}
}
