package com.farmaciaimpopular.farmaciaImpopular.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmaciaimpopular.farmaciaImpopular.models.Produto;
import com.farmaciaimpopular.farmaciaImpopular.repositories.ProdutoRepository;

@Service
public class ProdutoServices {
	@Autowired
	private ProdutoRepository repositoryP;
	
	public Optional<Produto> cadastrarProduto(Produto novoProduto){
		Optional<Produto> produtoExistente = repositoryP.findByNomeProduto(novoProduto.getNomeProduto());
		if(produtoExistente.isPresent()) {
			return Optional.empty();
		}else {
			return Optional.ofNullable(repositoryP.save(novoProduto));
		}
	}
	
	public Optional<Produto> alterarProduto(Produto produto){
		Optional<Produto> produtoExistente = repositoryP.findById(produto.getIdProduto());
		if(produtoExistente.isPresent()) {
			produtoExistente.get().setPrecoProduto(produto.getPrecoProduto());
			produtoExistente.get().setCategoriaCadastrada(produto.getCategoriaCadastrada());
			produtoExistente.get().setDescricaoProduto(produto.getDescricaoProduto());
			return Optional.ofNullable(repositoryP.save(produtoExistente.get()));
		}else {
			return Optional.empty();
		}
	}
	
	public ResponseEntity<String> removerCategoria(Long idProduto){
		Optional<Produto> produtoExistente = repositoryP.findById(idProduto);
		if(produtoExistente.isPresent()) {
			repositoryP.deleteById(idProduto);
			return ResponseEntity.status(200).body("Item removido");
		}else {
			return ResponseEntity.status(400).build();
		}
	}
}
