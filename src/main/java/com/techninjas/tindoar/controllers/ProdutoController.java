package com.techninjas.tindoar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.techninjas.tindoar.DTOs.ProdutoDTO;
import com.techninjas.tindoar.models.Produto;
import com.techninjas.tindoar.models.Usuario;
import com.techninjas.tindoar.repositories.UsuarioRepository;
import com.techninjas.tindoar.services.ProdutoService;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping 
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<Produto> list = service.findAll();
		List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{idProduto}")
	public ResponseEntity<Produto> findById(@PathVariable Integer idProduto) {
		Produto obj = service.findById(idProduto);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Produto> create(@RequestParam(value = "user", defaultValue="0") String username_doador, 
			@Valid @RequestBody Produto obj) {
		Usuario user = usuarioRepository.findByUsername(username_doador).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username_doador));
		obj = service.create(username_doador, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProduto}").buildAndExpand(obj.getIdProduto()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{idProduto}")
	public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Integer idProduto, @RequestBody Produto obj) {
		Produto newObj = service.update(idProduto, obj);
		return ResponseEntity.ok().body(new ProdutoDTO(newObj));
	}
	
	@DeleteMapping(value="{/idProduto}")
	public ResponseEntity<Void> delete(@PathVariable Integer idProduto) {
		service.delete(idProduto);
		return ResponseEntity.noContent().build();
	}
}
