package com.farmaciaimpopular.farmaciaImpopular.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.farmaciaimpopular.farmaciaImpopular.models.Categoria;
import com.farmaciaimpopular.farmaciaImpopular.repositories.CategoriaRepository;
import com.farmaciaimpopular.farmaciaImpopular.services.CategoriaServices;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaServices services;
	
	@GetMapping("/todes")
	public ResponseEntity<List<Categoria>> findAllCategoria(){
		List<Categoria> listaProduto = repository.findAll();
		if(!listaProduto.isEmpty()) {
			return ResponseEntity.status(201).body(listaProduto);
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/id/{id_categoria}")
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable(value = "id_categoria") Long idCategoria){
		return repository.findById(idCategoria)
				.map(categoriaExistente -> ResponseEntity.status(200).body(categoriaExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/descricao/{descricao_categoria}")
	public ResponseEntity<Categoria> findByDescricaoCategoria(@Valid @RequestBody Categoria descricaoCategoria){
		return repository.findByDescricaoCategoria(descricaoCategoria.getDescricaoCategoria())
				.map(descricaoExistente -> ResponseEntity.status(200).body(descricaoExistente))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria novaCategoria){
		return services.cadastrarProduto(novaCategoria)
				.map(categoriaNova -> ResponseEntity.status(201).body(categoriaNova))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PutMapping("/alterar/{id_categoria}")
	public ResponseEntity<Object> putCategoria(@PathVariable(value = "id_categoria") Long idCategoria,
			@Valid @RequestBody Categoria alteraCategoria){
		return services.alterarCadastro(idCategoria, alteraCategoria)
				.map(categoriaAlterada -> ResponseEntity.status(200).body(categoriaAlterada))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<String> deleteCategoria(@PathVariable(value = "id_categoria") Long idCategoria){
		return services.removerCategoria(idCategoria);
	}
}
