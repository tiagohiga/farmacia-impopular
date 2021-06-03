package com.farmaciaimpopular.farmaciaImpopular.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmaciaimpopular.farmaciaImpopular.models.Produto;
import com.farmaciaimpopular.farmaciaImpopular.repositories.ProdutoRepository;
import com.farmaciaimpopular.farmaciaImpopular.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ProdutoServices services;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Produto>> findAllProduto(){
		List<Produto> listaProdutos = repository.findAll();
		if(listaProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(201).body(listaProdutos);
		}
	}
	
	@GetMapping("/id/{id_produto}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable(value = "id_produto") Long idProduto){
		return repository.findById(idProduto)
				.map(idExistente -> ResponseEntity.status(200).body(idExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> findByDescricaoProduto(@RequestParam(defaultValue = "") String descricaoProduto){
		return repository.findByDescricaoProdutoContaining(descricaoProduto)
				.map(descricaoExistente -> ResponseEntity.status(200).body(descricaoExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@RequestBody Produto novoProduto){
		return services.cadastrarProduto(novoProduto)
				.map(produtoCadastrado -> ResponseEntity.status(201).body(produtoCadastrado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Produto> putProduto(@RequestBody Produto alteraProduto){
		return services.alterarProduto(alteraProduto)
				.map(produtoAlterado -> ResponseEntity.status(201).body(produtoAlterado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@DeleteMapping("/remover/{id_produto}")
	public ResponseEntity<String> deleteProduto(@PathVariable(value = "id_produto") Long idProduto){
		return services.removerCategoria(idProduto);
	}
}
